package com.wuxianggujun.tinasproutrobot;

import com.wuxianggujun.tinasproutcore.annotation.GroupMessageHandler;
import com.wuxianggujun.tinasproutcore.command.annotion.CommandHandler;
import com.wuxianggujun.tinasproutcore.core.Group;
import com.wuxianggujun.tinasproutcore.core.Member;
import com.wuxianggujun.tinasproutcore.message.MessageChain;
import org.springframework.stereotype.Component;

/**
 * @author WuXiangGuJun
 * @create 2023-04-14 15:52
 **/
@Component
public class MessageTest {
    @GroupMessageHandler(regex = "none")
    public void test(Group group, Member member, MessageChain messageChain, String message, Integer id) {
        group.sendMessage(messageChain);
        System.out.println(group.getGroupName()+" | "+member.getNickname());
    }


    @CommandHandler(command = "add")
    public void Command() {
        System.out.println("我是命令");
    }
}
