package com.wuxianggujun.tinasproutcore.handler.message;


import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.annotation.AddFriendHandler;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.component.BotFactory;
import com.wuxianggujun.tinasproutcore.event.message.AddFriendEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import com.wuxianggujun.tinasproutcore.message.FriendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddFriendEventHandler implements EventHandler {

    private String Hash = "afdlvhka";
    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (!AddFriendEvent.isSupport(jsonObject)) {
            return;
        }
        AddFriendEvent addFriendEvent = jsonObject.toJavaObject(AddFriendEvent.class);

        System.out.println(addFriendEvent.toString());

        List<Object> resultList = BotFactory.handleMethod(bot,addFriendEvent, handlerMethod -> {
            if (!handlerMethod.getMethod().isAnnotationPresent(AddFriendHandler.class)){
                return false;
            }
            AddFriendHandler handler = handlerMethod.getMethod().getAnnotation(AddFriendHandler.class);


            if (handler.flag()==null || handler.flag().equals("") || handler.flag().trim().length() ==0) {
                return false;
            }

            if (!handler.approve()) {
                return false;
            }


            return true;
        },"addFriendMessage");


        for(Object result : resultList ) {
            System.out.println(result);
            try {
                if (result instanceof FriendMessage) {

                    bot.addFriend((FriendMessage) result);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
