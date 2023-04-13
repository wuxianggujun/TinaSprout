package com.wuxianggujun.tinasproutcore.injector.support.group;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.Member;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupMessageEvent;
import com.wuxianggujun.tinasproutcore.event.message.MemberAddEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WuXiangGuJun
 * @create 2023-04-13 17:44
 **/
@Slf4j
public class MemberInjector implements ObjectInjector<Member> {
    @Override
    public Class<Member> getClassType() {
        return Member.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"message", "memberAddMessage"};
    }

    @Override
    public Member getObject(BaseEvent event, Bot bot) {
        try {
            if (event instanceof GroupMessageEvent groupMessageEvent) {
                return bot.getMember(groupMessageEvent.getGroupId(), groupMessageEvent.getUserId());
            }
            if (event instanceof MemberAddEvent memberAddEvent) {
                return bot.getMember(memberAddEvent.getGroupId(), memberAddEvent.getUserId());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
