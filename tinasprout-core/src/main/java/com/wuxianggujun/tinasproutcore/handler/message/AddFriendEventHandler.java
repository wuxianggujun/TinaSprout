package com.wuxianggujun.tinasproutcore.handler.message;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.message.AddFriendEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WuXiangGuJun
 * @create 2023-04-17 15:05
 **/
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddFriendEventHandler implements EventHandler {
    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (!AddFriendEvent.isSupport(jsonObject)) {
            return;
        }
        AddFriendEvent addFriendEvent = jsonObject.toJavaObject(AddFriendEvent.class);
        log.info("添加好友事件: " + jsonObject.toString() + "\n" + addFriendEvent.toString());

     /*   List<Object> resultList = BotFactory.handleMethod(bot,addFriendEvent,handlerMethod -> {
            
        },"");
        */
    }
}
