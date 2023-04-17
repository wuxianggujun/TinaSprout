package com.wuxianggujun.tinasproutcore.command.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WuXiangGuJun
 * @create 2023-04-16 17:18
 **/
@Slf4j
public class CommandEventInterceptor implements EventInterceptor {
    

    @Override
    public void postHandle(EventHandler eventHandler, JSONObject jsonObject, Bot bot) {
        log.info(jsonObject.toString());
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
