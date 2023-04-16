package com.wuxianggujun.tinasproutrobot;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.command.interceptor.EventInterceptor;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author WuXiangGuJun
 * @create 2023-04-16 14:44
 **/
@Component
@Slf4j
public class TestEventInterceptor implements EventInterceptor {
    @Override
    public boolean preHandle(EventHandler eventHandler, JSONObject jsonObject, Bot bot) {
        return true;
    }

    @Override
    public void postHandle(EventHandler eventHandler, JSONObject jsonObject, Bot bot) {
        log.info("我是Test拦截链");
    }
}
