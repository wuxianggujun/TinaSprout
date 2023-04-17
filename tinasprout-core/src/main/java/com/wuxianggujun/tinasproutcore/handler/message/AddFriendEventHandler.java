package com.wuxianggujun.tinasproutcore.handler.message;


import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.message.AddFriendEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddFriendEventHandler implements EventHandler {
    
    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (!AddFriendEvent.isSupport(jsonObject)) {
            return;
        }
        AddFriendEvent addFriendEvent = jsonObject.toJavaObject(AddFriendEvent.class);
        
    }
}
