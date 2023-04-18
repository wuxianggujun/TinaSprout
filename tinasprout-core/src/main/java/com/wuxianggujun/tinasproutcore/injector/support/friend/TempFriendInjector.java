package com.wuxianggujun.tinasproutcore.injector.support.friend;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.TempFriend;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.AddFriendEvent;
import com.wuxianggujun.tinasproutcore.event.message.PrivateMessageEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public class TempFriendInjector implements ObjectInjector<TempFriend> {
    @Override
    public Class<TempFriend> getClassType() {
        return TempFriend.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"message"};
    }

    @Override
    public TempFriend getObject(BaseEvent event, Bot bot) {
        if (event instanceof PrivateMessageEvent privateMessageEvent) {
            //消息子类型, 如果是好友则是 friend, 如果是群临时会话则是 group, 如果是在群中自身发送则是 group_self
            if ("group".equals(privateMessageEvent.getSubType())) {
                return new TempFriend(privateMessageEvent.getUserId(), bot);
            }
        } else if (event instanceof AddFriendEvent addFriendEvent) {
            return new TempFriend(addFriendEvent.getUserId(), bot);
        }
        return null;
    }
}
