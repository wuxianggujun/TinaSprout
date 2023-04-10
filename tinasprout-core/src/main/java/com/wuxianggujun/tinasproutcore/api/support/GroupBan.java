package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-10 19:00
 **/
public class GroupBan extends BaseApi {

    private final GroupBan.Param param;

    public GroupBan(long groupId, boolean enable) {
        this.param = new GroupBan.Param();
        this.param.setGroupId(groupId);
        this.param.setEnable(enable);
    }

    @Override
    public String getAction() {
        return "set_group_whole_ban";
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

        @JSONField(name = "enable")
        private boolean enable = true;
    }

}
