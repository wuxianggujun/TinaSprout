package com.wuxianggujun.tinasproutcore.command.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WuXiangGuJun
 * @create 2023-04-15 9:46
 **/
@Slf4j
public class MessageHandlerDecorator extends HandlerDecorator {

    public MessageHandlerDecorator(EventHandler handler) {
        super(handler);
    }

    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        super.handle(jsonObject, bot);
    }
}
