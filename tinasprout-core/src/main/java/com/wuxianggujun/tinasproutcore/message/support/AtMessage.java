package com.wuxianggujun.tinasproutcore.message.support;

import com.alibaba.fastjson.JSON;
import com.wuxianggujun.tinasproutcore.message.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 14:11
 **/
@Data
@NoArgsConstructor
public class AtMessage implements Message {

    private String qq;

    public AtMessage(String qq) {
        this.qq = qq;
    }


    @Override
    public String toMessageString() {
        return String.format("{\"type\":\"%s\",\"data\":%s}", "at", JSON.toJSONString(this));
    }

    @Override
    public String toString() {
        return "@" + qq + " ";
    }
}
