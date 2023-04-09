package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 22:09
 **/
public class SendGroupMsg extends BaseApi {

    private final SendGroupMsg.Param param;

    public SendGroupMsg(long groupId, MessageChain messageChain) {
        this.param = new SendGroupMsg.Param();
        this.param.setGroupId(groupId);
        this.param.setMessage(JSON.parseArray(messageChain.toMessageString()));
        this.param.setAutoEscape(false);
    }

    public SendGroupMsg(long groupId, MessageChain messageChain, boolean autoEscape) {
        this.param = new SendGroupMsg.Param();
        this.param.setGroupId(groupId);
        this.param.setMessage(JSON.parseArray(messageChain.toMessageString()));
        this.param.setAutoEscape(autoEscape);
    }

    @Override
    public String getAction() {
        return "send_group_msg";
    }

    @Override
    public Object getParams() {
        return param;
    }

    @Override
    public boolean needSleep() {
        return true;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Param {


        @JSONField(name = "group_id")
        private long groupId;


        @JSONField(name = "message")
        private JSONArray message;

        @JSONField(name = "auto_escape")
        private boolean autoEscape;
    }
}
