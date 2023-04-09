package com.wuxianggujun.tinasproutcore.api.support;

import com.wuxianggujun.tinasproutcore.api.BaseApi;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 20:29
 **/
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
