package com.wuxianggujun.tinasproutcore.command.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WuXiangGuJun
 * @create 2023-04-15 9:43
 **/
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public abstract class HandlerDecorator implements EventHandler {
    protected final EventHandler handler;

    public void handle(JSONObject jsonObject, Bot bot) {
        handler.handle(jsonObject, bot);
    }
    
}
