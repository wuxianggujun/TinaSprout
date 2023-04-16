package com.wuxianggujun.tinasproutcore.command.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;

/**
 * @author WuXiangGuJun
 * @create 2023-04-16 14:38
 **/
public interface EventInterceptor {
    default boolean preHandle(EventHandler eventHandler, JSONObject jsonObject, Bot bot) {
        return true;
    }

    void postHandle(EventHandler eventHandler, JSONObject jsonObject, Bot bot);

}
