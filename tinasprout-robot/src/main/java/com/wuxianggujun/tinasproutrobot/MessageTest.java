package com.wuxianggujun.tinasproutrobot;

import com.wuxianggujun.tinasproutcore.annotation.AddFriendHandler;
import com.wuxianggujun.tinasproutcore.annotation.FriendMessageHandler;
import com.wuxianggujun.tinasproutcore.annotation.GroupMessageHandler;
import com.wuxianggujun.tinasproutcore.command.CommandParams;
import com.wuxianggujun.tinasproutcore.command.annotion.CommandHandler;
import com.wuxianggujun.tinasproutcore.core.Friend;
import com.wuxianggujun.tinasproutcore.core.Group;
import com.wuxianggujun.tinasproutcore.core.Member;
import com.wuxianggujun.tinasproutcore.message.MessageChain;
import com.wuxianggujun.tinasproutcore.message.support.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author WuXiangGuJun
 * @create 2023-04-14 15:52
 **/
@Component
@Slf4j
public class MessageTest {
    @GroupMessageHandler(groupIds = {864358403})
    public void test(Group group, Member member, MessageChain messageChain, String message, Integer id) {
        group.sendMessage(messageChain);
    }


    @CommandHandler(command = "add")
    @FriendMessageHandler()
    public void Command(Friend friend, MessageChain messageChain, CommandParams commandParams) {
        MessageChain message = new MessageChain();
        message.add(new TextMessage(messageChain.toMessageString()));
        message.add(new TextMessage(commandParams.getName()));
        friend.sendMessage(message);
    }

    @AddFriendHandler()
    public void FromFriendRequest() {
        log.info("请求添加好友");
    }
}
