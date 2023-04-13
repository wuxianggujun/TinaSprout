package com.wuxianggujun.tinasproutcore.injector.support.friend;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.Friend;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.PrivateMessageEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author WuXiangGuJun
 * @create 2023-04-13 17:54
 **/
public class FriendInjector implements ObjectInjector<Friend> {
    @Override
    public Class<Friend> getClassType() {
        return Friend.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"message"};
    }

    @Override
    public Friend getObject(BaseEvent event, Bot bot) {
        if (event instanceof PrivateMessageEvent privateMessageEvent) {
            return bot.getFriend(privateMessageEvent.getUserId());
        }
        return null;
    }
}
