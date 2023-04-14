package com.wuxianggujun.tinasproutcore.handler;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public interface EventHandler {

    /**
     * 预处理Handle 只有返回true才处理handle的方法
     * @return true
     */
    default boolean preHandle(JSONObject jsonObject, Bot bot) {
        return true;
    }

    void handle(JSONObject jsonObject, Bot bot);

}
