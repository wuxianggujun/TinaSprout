package com.wuxianggujun.tinasproutrobot;

import com.wuxianggujun.tinasproutcore.annotation.AddFriendHandler;
import com.wuxianggujun.tinasproutcore.annotation.GroupMessageHandler;
import com.wuxianggujun.tinasproutcore.core.Group;
import com.wuxianggujun.tinasproutcore.core.Member;
import com.wuxianggujun.tinasproutcore.core.TempFriend;
import com.wuxianggujun.tinasproutcore.message.MessageChain;
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
        //group.sendMessage(messageChain);
    }

 /*   @FriendMessageHandler
    public void friendMessage(Friend friend) {
        friend.deleteFriend();
    }*/

    @AddFriendHandler(approve = true)
    public void FromFriendRequest(TempFriend friend) {
        log.info(friend.toString());
        log.info("请求添加好友");
    }
}
