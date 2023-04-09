package com.wuxianggujun.tinasproutcore.message.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 22:25
 **/
public class SendGroupForwardMsg extends BaseApi {

    private final SendGroupForwardMsg.Param param;

    public SendGroupForwardMsg(long groupId, List<ForwardNodeMessage> messageList) {
        this.param = new SendGroupForwardMsg.Param();
        this.param.setGroupId(groupId);
        JSONArray messages = new JSONArray();
        messageList.forEach(v -> {
            JSONObject message = JSON.parseObject(v.toMessageString());
            messages.add(message);
        });
        this.param.setMessage(messages);
    }

    @Override
    public String getAction() {
        return "send_group_forward_msg";
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

        @JSONField(name = "messages")
        private JSONArray message;
    }
}
