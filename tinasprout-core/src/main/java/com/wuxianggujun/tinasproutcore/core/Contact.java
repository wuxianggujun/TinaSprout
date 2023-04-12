package com.wuxianggujun.tinasproutcore.core;


import com.wuxianggujun.tinasproutcore.message.Message;
import com.wuxianggujun.tinasproutcore.message.MessageChain;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 14:01
 **/
public interface Contact {

    default void sendMessage(Message message) {
        MessageChain messageChain = new MessageChain();
        messageChain.add(message);
        this.sendMessage(messageChain);
    }

    int sendMessage(MessageChain messageChain);

}
