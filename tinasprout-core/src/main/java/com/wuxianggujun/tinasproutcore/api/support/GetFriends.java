package com.wuxianggujun.tinasproutcore.api.support;

import com.wuxianggujun.tinasproutcore.api.BaseApi;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 * 获取好友列表
 */
public class GetFriends extends BaseApi {

    public GetFriends() {

    }

    @Override
    public String getAction() {
        return "get_friend_list";
    }

    @Override
    public Object getParams() {
        return "";
    }

}
