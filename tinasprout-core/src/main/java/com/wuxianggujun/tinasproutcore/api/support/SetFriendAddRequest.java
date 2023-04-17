package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-18 1:01
 * 处理加好友请求
 **/
public class SetFriendAddRequest extends BaseApi {

    private final SetFriendAddRequest.Param param;

    public SetFriendAddRequest(String flag, boolean approve, String remark) {
        this.param = new SetFriendAddRequest.Param();
        this.param.setFlag(flag);
        this.param.setApprove(approve);
        this.param.setRemark(remark);
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
        private boolean approve = true;

        @JSONField(name = "remark")
        private String remark;
    }
}
