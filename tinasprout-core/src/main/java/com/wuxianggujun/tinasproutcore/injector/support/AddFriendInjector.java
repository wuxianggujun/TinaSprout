package com.wuxianggujun.tinasproutcore.injector.support;


import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.AddFriendEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;
import com.wuxianggujun.tinasproutcore.message.FriendMessage;

public class AddFriendInjector implements ObjectInjector<FriendMessage> {
    @Override
    public String[] getType() {
        return new String[]{"addFriendMessage"};
    }

    @Override
    public Class<FriendMessage> getClassType() {
        return FriendMessage.class;
    }


    @Override
    public FriendMessage getObject(BaseEvent event, Bot bot) {
        if (event instanceof AddFriendEvent) {
            AddFriendEvent addFriendEvent = (AddFriendEvent) event;

            System.out.println(addFriendEvent.toString());
            FriendMessage message = new FriendMessage();
            
            message.setFlag(addFriendEvent.getFrag());
            
            message.setRemark("robot");
            
            message.setApprove(true);
            
            return message;
        }
        
        return null;
    }
}
