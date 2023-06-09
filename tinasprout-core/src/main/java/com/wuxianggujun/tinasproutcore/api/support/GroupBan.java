package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public class GroupBan extends BaseApi {

    private final Param param;

    public GroupBan(long groupId, boolean enable) {
        this.param = new Param();
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
