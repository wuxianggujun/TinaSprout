package com.wuxianggujun.tinasproutcore.handler;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;

/**
 * @author WuXiangGuJun
 * @create 2023-03-06 20:38
 **/
public interface EventHandler {
    void handle(JSONObject jsonObject, Bot bot);
}
