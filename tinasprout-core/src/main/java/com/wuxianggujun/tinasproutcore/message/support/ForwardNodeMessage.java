package com.wuxianggujun.tinasproutcore.message.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wuxianggujun.tinasproutcore.message.Message;
import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 22:17
 **/
@Getter
public class ForwardNodeMessage implements Message {


    @Getter(AccessLevel.NONE)
    private String id;

    private String uin;


    private String name;

    private MessageChain content;

    public ForwardNodeMessage(String id) {
        this.id = id;
    }

    public ForwardNodeMessage(String uin, String name, MessageChain content) {
        this.uin = uin;
        this.name = name;
        this.content = content;
    }

    @Override
    public String toString() {
        return "node[" + JSON.toJSONString(this) + "]";
    }


    @Override
    public String toMessageString() {
        return String.format("{\"type\":\"%s\",\"data\":%s}", "node", JSON.toJSONString(this));
    }

    public JSONArray getContent() {
        return JSON.parseArray(content.toMessageString());
    }
}
