package com.wuxianggujun.tinasproutcore.core.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.api.ApiResult;
import com.wuxianggujun.tinasproutcore.command.interceptor.EventInterceptor;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.network.ws.WsBotClient;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import com.wuxianggujun.tinasproutcore.handler.meta.HeartbeatEventHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BotDispatcher {

    private final Map<String, EventHandler> eventHandlerMap;

    private ExecutorService executorService;

    private final List<EventInterceptor> interceptors;

    @PostConstruct
    public void init() {
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public void handle(String message) {
        try {
            JSONObject jsonObject = JSON.parseObject(message);
            if (jsonObject.containsKey("echo") && jsonObject.containsKey("status") && jsonObject.containsKey("retcode") && jsonObject.containsKey("data")) {
                try {
                    ApiResult apiResult = JSON.parseObject(message, ApiResult.class);
                    CompletableFuture<ApiResult> completableFuture = WsBotClient.getCompletableFutureMap().get(apiResult.getEcho());
                    if (completableFuture != null) {
                        completableFuture.complete(apiResult);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                return;
            }
            Bot bot = BotFactory.getBots().get(jsonObject.getLong("self_id"));
            if (bot == null) {
                return;
            }
            this.executorService.submit(() -> {
                try {
                    for (EventHandler eventHandler : eventHandlerMap.values()) {
                        //别问，问就是如果将心跳包加进去。tmd 判断事件太麻烦了
                        if (eventHandler instanceof HeartbeatEventHandler) {
                            eventHandler.handle(jsonObject, bot);
                            continue;
                        }

                        boolean continueHandle = true;

                        for (EventInterceptor interceptor : interceptors) {
                            continueHandle = interceptor.preHandle(eventHandler, jsonObject, bot);
                            if (!continueHandle) {
                                break;
                            }
                        }

                        if (continueHandle) {
                            eventHandler.handle(jsonObject, bot);
                            for (EventInterceptor interceptor : interceptors) {
                                interceptor.postHandle(eventHandler, jsonObject, bot);
                            }
                        }

                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
