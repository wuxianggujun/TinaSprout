package com.wuxianggujun.tinasproutcore.core;

import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Member;


/**
 * @author WuXiangGuJun
 * @create 2023-04-09 15:10
 **/
@AllArgsConstructor
@Getter
@Slf4j
public class Group implements Contact{
    
    private final long groupId;
    
    private final String groupName;
    
    private final Bot bot;
    
    
    public Member getMember(long userId){
        
    }
    
    
    
    @Override
    public int sendMessage(MessageChain messageChain) {
        return 0;
    }
}
