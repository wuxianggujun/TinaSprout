package com.wuxianggujun.tinasproutcore.injector.support.group;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupRecallEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;
import com.wuxianggujun.tinasproutcore.injector.object.RecallMessage;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public class RecallMessageInjector implements ObjectInjector<RecallMessage> {
    @Override
    public Class<RecallMessage> getClassType() {
        return RecallMessage.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"recallMessage"};
    }

    @Override
    public RecallMessage getObject(BaseEvent event, Bot bot) {
        if (event instanceof GroupRecallEvent) {
            GroupRecallEvent groupRecallEvent = (GroupRecallEvent) event;
            RecallMessage recallMessage = new RecallMessage();
            recallMessage.setSenderId(groupRecallEvent.getUserId());
            recallMessage.setOperatorId(groupRecallEvent.getOperatorId());
            return recallMessage;
        }
        return null;
    }
}
