package com.wuxianggujun.tinasproutcore.network;

import com.wuxianggujun.tinasproutcore.config.BotConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WuXiangGuJun
 * @create 2023-03-04 20:00
 **/
@Slf4j
@ChannelHandler.Sharable//用于标记ChannelHandler是否可以被多个Channel共享使用，要确保线程安全，否则可能发生并发问题
@RequiredArgsConstructor//用于生成一个包含必须参数的构造方法
public class WsHandler extends SimpleChannelInboundHandler<Object> {

    private final BotConfig config;
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}