package com.wuxianggujun.tinasproutcore.command.decorator;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WuXiangGuJun
 * @create 2023-04-15 19:35
 **/
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public abstract class EventDecorator implements EventHandler {
    private final EventHandler eventHandler;


    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (eventHandler != null) {
            eventHandler.handle(jsonObject, bot);
        }
    }
}
