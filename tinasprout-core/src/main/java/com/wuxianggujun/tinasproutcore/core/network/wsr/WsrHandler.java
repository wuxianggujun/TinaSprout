package com.wuxianggujun.tinasproutcore.core.network.wsr;

import com.wuxianggujun.tinasproutcore.config.BotConfig;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.BotContext;
import com.wuxianggujun.tinasproutcore.core.Group;
import com.wuxianggujun.tinasproutcore.core.component.BotDispatcher;
import com.wuxianggujun.tinasproutcore.core.component.BotFactory;
import com.wuxianggujun.tinasproutcore.core.network.BotClient;
import com.wuxianggujun.tinasproutcore.core.network.ws.WsBotClient;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author WuXiangGuJun
 * @create 2023-04-11 15:37
 **/

@Slf4j
@RequiredArgsConstructor
public class WsrHandler extends SimpleChannelInboundHandler<Object> {

    private final BotConfig botConfig;
    private final BotDispatcher botDispatcher;
    private final Map<Long, Bot> bots;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpRequest request) {
            if (!request.decoderResult().isSuccess()) {
                sendResponse(ctx, request, new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.BAD_REQUEST, ctx.alloc().buffer()));
                return;
            }
            if (!HttpMethod.GET.equals(request.method())) {
                sendResponse(ctx, request, new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.FORBIDDEN, ctx.alloc().buffer()));
                return;
            }
            String authorization = request.headers().get("Authorization");
            if (StringUtils.isEmpty(authorization)) {
                log.error("Token认证失败.");
                sendResponse(ctx, request, new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.BAD_REQUEST, ctx.alloc().buffer()));
                ctx.close();
                return;
            }
            {
                boolean isReturn = false;
                Map<String, BotContext> beans = BotFactory.getBeansByClass(BotContext.class);
                if (beans != null) {
                    long qq;
                    try {
                        qq = Long.parseLong(((FullHttpRequest) msg).headers().get("X-Self-ID"));
                    } catch (Exception ignore) {
                        return;
                    }
                    for (BotContext botContext : beans.values()) {
                        isReturn = !botContext.approve(qq, authorization.replace("Token ", ""));
                    }
                } else {
                    isReturn = !this.botConfig.getAccessToken().equals(authorization.replace("Token ", ""));
                }
                if (isReturn) {
                    return;
                }
            }
            WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(getWebSocketLocation(request), null, true, 5 * 1024 * 1024);
            WebSocketServerHandshaker handshake = factory.newHandshaker(request);
            if (handshake == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshake.handshake(ctx.channel(), request);
                BotClient botClient = new WsBotClient(ctx.channel());
                Bot bot = new Bot(this.botConfig, botClient);
                new Thread(() -> {
                    try {
                        bot.flushBotInfo();
                        log.info(String.format("[%s]Go-cqhttp connected!", bot.getBotName()));
                        if (bot.getBotId() == 0 || StringUtils.isEmpty(bot.getBotName())) {
                            log.error("ws反向连接失败.");
                            ctx.close();
                            return;
                        }
                        Map<String, BotContext> beans = BotFactory.getBeansByClass(BotContext.class);
                        if (beans != null) {
                            for (BotContext botContext : beans.values()) {
                                botContext.connected(bot);
                            }
                        }
                        bots.put(bot.getBotId(), bot);
                        try {
                            bot.flushFriends();
                            for (Group group : bot.flushGroups()) {
                                bot.flushGroupMembers(group);
                            }
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                        bot.getCompletableFuture().complete(1L);
                    } catch (Exception e) {
                        ctx.close();
                        log.error("ws反向连接失败. " + e.getMessage(), e);
                    }
                }).start();
            }
        } else if (msg instanceof WebSocketFrame frame) {
            if (frame instanceof TextWebSocketFrame textFrame) {
                this.botDispatcher.handle(textFrame.text());
            } else if (frame instanceof CloseWebSocketFrame) {
                ctx.close();
            }
        }
    }

    private String getWebSocketLocation(FullHttpRequest request) {
        String location = request.headers().get(HttpHeaderNames.HOST) + "/websocket";
        return "ws://" + location;
    }

    private void sendResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse resp) {
        HttpResponseStatus status = resp.status();
        if (status != HttpResponseStatus.OK) {
            ByteBufUtil.writeUtf8(resp.content(), status.toString());
            HttpUtil.setContentLength(req, resp.content().readableBytes());
        }
        boolean keepAlive = HttpUtil.isKeepAlive(req) && status == HttpResponseStatus.OK;
        HttpUtil.setKeepAlive(req, keepAlive);
        ChannelFuture future = ctx.write(resp);
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent idleStateEvent) {
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush(new PingWebSocketFrame());
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        for (Map.Entry<Long, Bot> botEntry : bots.entrySet()) {
            BotClient botClient = botEntry.getValue().getBotClient();
            if (!(botClient instanceof WsBotClient)) {
                continue;
            }
            if (((WsBotClient) botClient).getChannel().id().asLongText().equals(ctx.channel().id().asLongText())) {
                bots.remove(botEntry.getKey());
            }
        }
    }

}
