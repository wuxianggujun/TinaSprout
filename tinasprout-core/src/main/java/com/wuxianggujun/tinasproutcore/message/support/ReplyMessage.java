package com.wuxianggujun.tinasproutcore.message.support;

import com.alibaba.fastjson.JSON;
import com.wuxianggujun.tinasproutcore.message.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:31
 **/
@Data
@NoArgsConstructor
public class ReplyMessage implements Message {

    private int id;

    private String text;

    private long qq;

    private long seq;

    public ReplyMessage(int messageId) {
        this.id = messageId;
    }

    @Override
    public String toString() {
        return "reply[" + id + "]";
    }

    @Override
    public String toMessageString() {
        return String.format("{\"type\":\"%s\",\"data\":%s}", "reply", JSON.toJSONString(this));
    }

}

