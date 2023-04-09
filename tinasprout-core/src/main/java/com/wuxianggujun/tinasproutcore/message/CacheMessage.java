package com.wuxianggujun.tinasproutcore.message;

import lombok.Data;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 15:37
 **/
@Data
public class CacheMessage {

    private Long senderId;

    private MessageChain messageChain;
}
