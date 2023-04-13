package com.wuxianggujun.tinasproutcore.core.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.api.ApiResult;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.network.ws.WsBotClient;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WuXiangGuJun
 * @create 2023-03-06 20:29
 **/
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//用于在构造函数上自动注入依赖项。它表示在使用构造函数创建对象时，
// 需要自动注入使用@Autowired注释的依赖项。这样可以避免手动注入依赖项，减少代码量和错误。
public class BotDispatcher {
    private final Map<String, EventHandler> eventHandlerMap;

    private ExecutorService executorService;

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
                    CompletableFuture<ApiResult> completeFuture = WsBotClient.getCompletableFutureMap().get(apiResult.getEcho());
                    if (completeFuture != null) {
                        completeFuture.complete(apiResult);
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
                        eventHandler.handle(jsonObject, bot);
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
