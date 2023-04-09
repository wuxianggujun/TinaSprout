package com.wuxianggujun.tinasproutcore.core;

import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 14:28
 **/
@AllArgsConstructor
@Getter
public class Friend implements Contact {

    private final long userId;

    private final String nickname;
    private final String remark;

    private final Bot bot;
    
    @Override
    public int sendMessage(MessageChain messageChain) {
        return this.bot.sendPrivateMessage(this.userId,messageChain);
    }
}
