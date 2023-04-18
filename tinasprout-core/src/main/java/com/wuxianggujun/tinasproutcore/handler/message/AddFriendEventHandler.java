package com.wuxianggujun.tinasproutcore.handler.message;


import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.annotation.AddFriendHandler;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.component.BotFactory;
import com.wuxianggujun.tinasproutcore.event.message.AddFriendEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddFriendEventHandler implements EventHandler {

    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (!AddFriendEvent.isSupport(jsonObject)) {
            return;
        }
        AddFriendEvent addFriendEvent = jsonObject.toJavaObject(AddFriendEvent.class);
        List<Object> resultList = BotFactory.handleMethod(bot, addFriendEvent, handlerMethod -> {
            if (!handlerMethod.getMethod().isAnnotationPresent(AddFriendHandler.class))
                return false;
            AddFriendHandler addFriendHandler = handlerMethod.getMethod().getAnnotation(AddFriendHandler.class);
            if (Boolean.TRUE.equals(addFriendHandler.approve())) {
                bot.setFriendAddRequest(addFriendEvent.getFrag(), true, addFriendEvent.getRemark());
            }
            return true;
        }, "message");

    }
}
