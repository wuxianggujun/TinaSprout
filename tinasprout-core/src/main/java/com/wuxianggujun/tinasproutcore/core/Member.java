package com.wuxianggujun.tinasproutcore.core;

import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 15:13
 **/
@AllArgsConstructor
@Getter
@Slf4j
public class Member implements Contact {

    private final long userId;

    private final long groupId;

    private final String nickname;

    private String card;

    private final int age;

    private final String area;

    private final Data joinTime;

    private final Data lastSentTime;

    private final String level;

    private final String role;

    private final boolean unfriendly;

    private final String title;

    private final Data titleExpireTime;

    private final boolean cardChangeable;

    private final Bot bot;

    public String getCard() {
        return StringUtils.isEmpty(this.card) ? this.nickname : this.card;
    }

    public void setCard(String card) {
        this.bot.setGroupCard(this.groupId, this.userId, card);
        this.card = card;
    }

    public void ban(long duration) {
        this.bot.memberBan(this.groupId, this.userId, duration);
    }

    public void pardon() {
        this.bot.memberPardon(this.groupId, this.userId);
    }

    @Override
    public int sendMessage(MessageChain messageChain) {
        try {
            if (this.bot.isFriend(this.userId)) {
                return this.bot.sendPrivateMessage(this.userId, messageChain);
            } else {
                return this.bot.sendTempMessage(this.userId, this.groupId, messageChain);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }
}
