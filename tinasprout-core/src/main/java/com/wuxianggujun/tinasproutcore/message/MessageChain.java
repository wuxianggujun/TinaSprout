package com.wuxianggujun.tinasproutcore.message;

import com.wuxianggujun.tinasproutcore.message.support.AtMessage;
import com.wuxianggujun.tinasproutcore.message.support.TextMessage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 14:05
 **/
public class MessageChain extends ArrayList<Message> {

    @Override
    public String toString() {
        return this.stream().map(Objects::toString).collect(Collectors.joining());
    }

    public String toMessageString() {
        return this.stream().map(Message::toMessageString).collect(Collectors.joining());
    }


    public MessageChain at(long qq) {
        this.add(new AtMessage(String.valueOf(qq)));
        return this;
    }

    public MessageChain atAll() {
        this.add(new AtMessage("all"));
        return this;
    }

    public MessageChain text(String message) {
        this.add(new TextMessage(message));
        return this;
    }


}
