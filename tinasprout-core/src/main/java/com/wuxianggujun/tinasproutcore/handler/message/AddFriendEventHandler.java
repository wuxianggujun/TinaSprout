package com.wuxianggujun.tinasproutcore.handler.message;


import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.message.AddFriendEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import com.wuxianggujun.tinasproutcore.message.FriendMessage;
import com.wuxianggujun.tinasproutcore.message.MessageChain;

public class AddFriendEventHandler implements EventHandler {

    private String Hash = "afdlvhka";
    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (!AddFriendEvent.isSupport(jsonObject)) {
            return;
        }
        AddFriendEvent addFriendEvent = jsonObject.toJavaObject(AddFriendEvent.class);


        if (!addFriendEvent.getMessage().equals(Hash)) {
            System.out.println("QAQ"+ "没有权限");
            return;
        }


        FriendMessage message = new FriendMessage();
        message.setFlag(addFriendEvent.getFrag());
        message.setRemark("robot");
        message.setApprove(true);

        bot.addFriend(message);
    }
}
