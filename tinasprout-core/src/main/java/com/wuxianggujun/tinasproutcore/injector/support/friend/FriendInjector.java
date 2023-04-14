package com.wuxianggujun.tinasproutcore.injector.support.friend;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.Friend;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.PrivateMessageEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
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
        if (event instanceof PrivateMessageEvent) {
            PrivateMessageEvent privateMessageEvent = (PrivateMessageEvent) event;
            return bot.getFriend(privateMessageEvent.getUserId());
        }
        return null;
    }
}
