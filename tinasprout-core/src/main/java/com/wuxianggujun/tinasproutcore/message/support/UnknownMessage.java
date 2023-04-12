package com.wuxianggujun.tinasproutcore.message.support;

import com.wuxianggujun.tinasproutcore.message.Message;
import lombok.Data;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:33
 **/
@Data
public class UnknownMessage implements Message {

    private String json;

    @Override
    public String toString() {
        return "json[" + json + "]";
    }

    @Override
    public String toMessageString() {
        return this.json;
    }
}
