package com.wuxianggujun.tinasproutcore.command.decorator;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;

/**
 * @author WuXiangGuJun
 * @create 2023-04-15 19:38
 **/
public class DefaultEventDecorator  extends EventDecorator{
    public DefaultEventDecorator(EventHandler eventHandler) {
        super(eventHandler);
    }

    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        super.handle(jsonObject, bot);
    }
}
