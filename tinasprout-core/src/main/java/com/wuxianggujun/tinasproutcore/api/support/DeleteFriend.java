package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-18 0:43
 * 删除好友 wuxianggujun
 **/

public class DeleteFriend extends BaseApi {

    private final Param param;

    public DeleteFriend(long userId) {
        this.param = new Param();
        this.param.userId = userId;
    }

    @Override
    public String getAction() {
        return "delete_friend";
    }

    @Override
    public Object getParams() {
        return param;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Param {

        @JSONField(name = "user_id")
        private long userId;

    }
}
