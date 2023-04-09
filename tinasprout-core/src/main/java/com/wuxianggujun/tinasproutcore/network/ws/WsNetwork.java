package com.wuxianggujun.tinasproutcore.network.ws;

import com.wuxianggujun.tinasproutcore.config.BotConfig;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.component.BotDispatcher;
import com.wuxianggujun.tinasproutcore.exception.BotException;
import com.wuxianggujun.tinasproutcore.network.BotNetwork;
import com.wuxianggujun.tinasproutcore.network.WsHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author WuXiangGuJun
 * @create 2023-04-08 22:53
 **/
@Slf4j
public class WsNetwork implements BotNetwork {
    private final Bootstrap clientBootstrap = new Bootstrap();

    private BotConfig botConfig;

    private Channel channel;


    @Override
    public void init(BotConfig botConfig, Map<Long, Bot> bots, BotDispatcher botDispatcher) {
        this.botConfig = botConfig;
        WsNetwork instance = this;
        this.clientBootstrap.group(new NioEventLoopGroup())
                .channel(NioSctpChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new HttpClientCodec())
                                .addLast(new HttpObjectAggregator(1024 * 1024 * 100))
                                .addLast(new WebSocketFrameAggregator(1024 * 1024 * 100))
                                .addLast(new WsHandler(botConfig, botDispatcher, instance, bots));
                    }
                });
        this.connection();
    }

    protected Channel getChannel() {
        if (channel == null || !channel.isActive() || !channel.pipeline().get(WsHandler.class).getWebSocketClientHandshaker().isHandshakeComplete()) {
            throw new BotException(String.format("[%s]连接失败", this.botConfig.getUrl()));
        }
        return channel;
    }


    public void connection() {
        //如果Channel处于活动状态并且已连接，则返回true 。
        if (channel != null && channel.isActive()) {
            return;
        }
        URI wsUri;

        try {
            wsUri = new URI(this.botConfig.getUrl());

        } catch (URISyntaxException e) {
            throw new BotException("WebSocket url 格式错误.");
        }
        ChannelFuture channelFuture = clientBootstrap.connect(wsUri.getHost(), wsUri.getPort());
        channelFuture.addListener((ChannelFutureListener) futureListener -> {
            if (futureListener.isSuccess()) {
                channel = futureListener.channel();
            } else {
                log.error("Failed to connect to go0cqhttp used ws,try connect after 10s");
                futureListener.channel().eventLoop().schedule(this::connection, 10, TimeUnit.SECONDS);
            }
        });
    }


}