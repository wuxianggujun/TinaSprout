package com.wuxianggujun.tinasproutcore.core;

import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaoxu
 * @since 2021/5/27 11:36
 */
@AllArgsConstructor
@Getter
public class Friend implements Contact {

    private final long userId;

    private final String nickname;

    private final String remark;

    private final Bot bot;

    @Override
    public int sendMessage(MessageChain messageChain) {
        return this.bot.sendPrivateMessage(this.userId, messageChain);
    }

    public void deleteFriend() {
        if (userId != 0) {
            this.bot.deleteFriend(userId);
        }
    }
}
