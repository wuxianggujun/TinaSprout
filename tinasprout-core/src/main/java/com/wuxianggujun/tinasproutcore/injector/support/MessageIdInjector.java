package com.wuxianggujun.tinasproutcore.injector.support;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupRecallEvent;
import com.wuxianggujun.tinasproutcore.event.message.MessageEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author WuXiangGuJun
 * @create 2023-04-13 17:46
 **/
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
        if (event instanceof MessageEvent messageEvent) {
            return messageEvent.getMessageId();
        }
        if (event instanceof GroupRecallEvent groupRecallEvent) {
            return groupRecallEvent.getMessageId();
        }
        return null;
    }
}
