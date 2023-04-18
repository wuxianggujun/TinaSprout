package com.wuxianggujun.tinasproutcore.core;

import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
@AllArgsConstructor
@Getter
@Slf4j
public class TempFriend implements Contact {

    private final long userId;
    private final Bot bot;
    
    @Override
    public int sendMessage(MessageChain messageChain) {
        return this.bot.sendPrivateMessage(this.userId, messageChain);
    }
    

}
