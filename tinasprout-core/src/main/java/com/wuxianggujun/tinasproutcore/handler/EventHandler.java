package com.wuxianggujun.tinasproutcore.handler;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public interface EventHandler {

    void handle(JSONObject jsonObject, Bot bot);

}
