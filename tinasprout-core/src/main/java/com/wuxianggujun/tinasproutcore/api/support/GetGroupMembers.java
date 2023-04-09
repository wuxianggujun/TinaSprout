package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 20:48
 **/
public class GetGroupMembers extends BaseApi {

    private final GetGroupMembers.Param param;

    public GetGroupMembers(long groupId) {
        this.param = new GetGroupMembers.Param();
        this.param.setGroupId(groupId);
    }

    @Override
    public String getAction() {
        return "get_group_member_list";
    }

    @Override
    public Object getParams() {
        return param;
    }


    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Param {

        @JSONField(name = "group_id")
        private long groupId;
    }

}
