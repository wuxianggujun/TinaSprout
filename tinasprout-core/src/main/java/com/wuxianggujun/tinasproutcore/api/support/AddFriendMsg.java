package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import com.wuxianggujun.tinasproutcore.message.FriendMessage;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AddFriendMsg extends BaseApi {

    private final Param param;
    public AddFriendMsg(FriendMessage message) {
        param = new Param();
        this.param.flag = message.getFlag();
        this.param.approve = message.isApprove();
        this.param.remark = message.getRemark();
    }

    @Override
    public String getAction() {
        return "set_friend_add_request";
    }

    @Override
    public Object getParams() {
        return param;
    }



    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Param {

        @JSONField(name = "flag")
        private String flag;

        @JSONField(name = "approve")
        private boolean approve;

        @JSONField(name = "remark")
        private String remark;



    }



}
