package com.wuxianggujun.tinasproutcore.injector.support;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupRecallEvent;
import com.wuxianggujun.tinasproutcore.event.message.MessageEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public class MessageIdInjector implements ObjectInjector<Integer> {
    @Override
    public Class<Integer> getClassType() {
        return Integer.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"message", "recallMessage"};
    }

    @Override
    public Integer getObject(BaseEvent event, Bot bot) {
        if (event instanceof MessageEvent) {
            return ((MessageEvent) event).getMessageId();
        }
        if (event instanceof GroupRecallEvent) {
            return ((GroupRecallEvent) event).getMessageId();
        }
        return null;
    }
}
