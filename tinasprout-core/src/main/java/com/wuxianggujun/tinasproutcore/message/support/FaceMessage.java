package com.wuxianggujun.tinasproutcore.message.support;

import com.alibaba.fastjson.JSON;
import com.wuxianggujun.tinasproutcore.message.Message;
import lombok.Data;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:27
 **/
@Data
public class FaceMessage implements Message {

    private String id;

    public FaceMessage(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "face[" + id + "]";
    }

    @Override
    public String toMessageString() {
        return String.format("{\"type\":\"%s\",\"data\":%s}", "face", JSON.toJSONString(this));
    }

}
