package com.wuxianggujun.tinasproutcore.command.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;

/**
 * @author WuXiangGuJun
 * @create 2023-04-16 14:38
 **/
public interface EventInterceptor {
    /**
     * 预处理 如果返回false直接将除了心跳包的事件都停止
     *
     * @param eventHandler 事件
     * @param jsonObject   json对象
     * @param bot          机器人
     * @return true or false
     */
    default boolean preHandle(EventHandler eventHandler, JSONObject jsonObject, Bot bot) {
        return true;
    }

    void postHandle(EventHandler eventHandler, JSONObject jsonObject, Bot bot);

}
