package com.wuxianggujun.tinasproutcore.network.ws;

import com.wuxianggujun.tinasproutcore.api.ApiResult;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.network.BotClient;
import com.wuxianggujun.tinasproutcore.exception.BotException;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 13:51
 **/
public class WsBotClient implements BotClient {


    private final Lock lock = new ReentrantLock();


    private static final Map<String, CompletableFuture<ApiResult>> completableFutureMap = new ConcurrentHashMap<>();


    private final Channel channel;

    private long lastInvokeTime;

    public WsBotClient(Channel channel) {
        this.channel = channel;
    }

    public static Map<String, CompletableFuture<ApiResult>> getCompletableFutureMap() {
        return completableFutureMap;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public void heartbeat() {
        this.channel.writeAndFlush(new PingWebSocketFrame());
    }

    @Override
    public ApiResult invokeApi(BaseApi baseApi, Bot bot) {
        this.lock.lock();
        
        try {
            if (baseApi.needSleep()&& System.currentTimeMillis() - lastInvokeTime<1500){
                try {
                    Thread.sleep(1500 -(System.currentTimeMillis() - lastInvokeTime));
                } catch (InterruptedException e) {
                    throw new BotException(String.format("[%5]调用api出错: %s",bot.get))
                }
            }
        }
        
        
        return null;
    }


}
