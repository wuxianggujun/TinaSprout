package com.wuxianggujun.tinasproutcore.injector.support;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupMessageEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupRecallEvent;
import com.wuxianggujun.tinasproutcore.event.message.PrivateMessageEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;
import com.wuxianggujun.tinasproutcore.message.CacheMessage;
import com.wuxianggujun.tinasproutcore.message.MessageChain;
import com.wuxianggujun.tinasproutcore.message.MessageTypeHandle;

import java.util.List;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public class MessageChainInjector implements ObjectInjector<MessageChain> {
    @Override
    public Class<MessageChain> getClassType() {
        return MessageChain.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"message", "recallMessage"};
    }

    @Override
    public MessageChain getObject(BaseEvent event, Bot bot) {
        MessageChain messageChain = null;
        if (event instanceof GroupMessageEvent) {
            messageChain = new MessageChain();
            GroupMessageEvent groupMessageEvent = (GroupMessageEvent) event;
            for (int i = 0; i < groupMessageEvent.getMessage().size(); i++) {
                messageChain.add(MessageTypeHandle.getMessage(groupMessageEvent.getMessage().getJSONObject(i)));
            }
        }
        if (event instanceof PrivateMessageEvent) {
            PrivateMessageEvent privateMessageEvent = (PrivateMessageEvent) event;
            messageChain = new MessageChain();
            for (int i = 0; i < privateMessageEvent.getMessage().size(); i++) {
                messageChain.add(MessageTypeHandle.getMessage(privateMessageEvent.getMessage().getJSONObject(i)));
            }
        }
        if (event instanceof GroupRecallEvent) {
            GroupRecallEvent groupRecallEvent = (GroupRecallEvent) event;
            List<CacheMessage> groupCacheMessageChain = bot.getGroupCacheMessageChain(groupRecallEvent.getGroupId(), groupRecallEvent.getMessageId(), 1);
            if (groupCacheMessageChain.isEmpty()) {
                return null;
            }
            messageChain = groupCacheMessageChain.get(0).getMessageChain();
        }
        return messageChain;
    }
}
