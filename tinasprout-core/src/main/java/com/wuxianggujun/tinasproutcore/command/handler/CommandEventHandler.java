package com.wuxianggujun.tinasproutcore.command.handler;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.message.GroupMessageEvent;
import com.wuxianggujun.tinasproutcore.event.message.PrivateMessageEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WuXiangGuJun
 * @create 2023-04-14 19:05
 **/
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommandEventHandler implements EventHandler {

    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
//        log.info(jsonObject.toString());
        if (GroupMessageEvent.isSupport(jsonObject)) {
            log.info("-----------群聊消息----------");
            GroupMessageEvent groupMessageEvent = jsonObject.toJavaObject(GroupMessageEvent.class);


        }
        if (PrivateMessageEvent.isSupport(jsonObject)) {
            log.info("-----------私聊消息----------");
            
        }
    } 
    
    
 /*     @Override
    public void handle(JSONObject jsonObject, Bot bot) {
//        log.info(jsonObject.toString());
        if (GroupMessageEvent.isSupport(jsonObject)) {
            log.info("-----------群聊消息----------");
            GroupMessageEventHandler groupMessageEventHandler = new GroupMessageEventHandler();
            groupMessageEventHandler.handle(jsonObject, bot);
        }
        if (PrivateMessageEvent.isSupport(jsonObject)) {
            log.info("-----------私聊消息----------");
            PrivateMessageEventHandler privateMessageEventHandler = new PrivateMessageEventHandler();
            privateMessageEventHandler.handle(jsonObject, bot);
        }
    }*/
}
