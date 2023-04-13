package com.wuxianggujun.tinasproutcore.injector.support.group;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.Group;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupMessageEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupRecallEvent;
import com.wuxianggujun.tinasproutcore.event.message.MemberAddEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WuXiangGuJun
 * @create 2023-04-13 17:39
 **/

@Slf4j
public class GroupInjector implements ObjectInjector<Group> {
    @Override
    public Class<Group> getClassType() {
        return Group.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"message", "recallMessage", "memberAddMessage"};
    }

    @Override
    public Group getObject(BaseEvent event, Bot bot) {
        try {
            if (event instanceof GroupMessageEvent groupMessageEvent) {
                return bot.getGroup(groupMessageEvent.getGroupId());
            }
            if (event instanceof GroupRecallEvent groupRecallEvent) {
                return bot.getGroup(groupRecallEvent.getGroupId());
            }
            if (event instanceof MemberAddEvent memberAddEvent) {
                return bot.getGroup(memberAddEvent.getGroupId());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
