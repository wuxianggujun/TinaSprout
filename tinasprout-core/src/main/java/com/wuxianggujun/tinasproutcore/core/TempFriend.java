package com.wuxianggujun.tinasproutcore.core;

import com.wuxianggujun.tinasproutcore.message.MessageChain;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:42
 **/
public class TempFriend implements Contact {

    private final long userId;
    private final Bot bot;

    public TempFriend(long userId, Bot bot) {
        this.userId = userId;
        this.bot = bot;
    }

    public long getUserId() {
        return this.userId;
    }

    @Override
    public int sendMessage(MessageChain messageChain) {
        return this.bot.sendPrivateMessage(this.userId, messageChain);
    }

}