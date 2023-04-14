package com.wuxianggujun.tinasproutcore.command.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;

/**
 * @author WuXiangGuJun
 * @create 2023-04-15 1:37
 **/
public interface Interceptor {
    boolean preHandle(JSONObject jsonObject, Bot bot);

    void postHandle(JSONObject jsonObject, Bot bot);
}
