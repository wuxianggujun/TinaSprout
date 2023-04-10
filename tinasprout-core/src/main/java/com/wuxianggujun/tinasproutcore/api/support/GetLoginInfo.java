package com.wuxianggujun.tinasproutcore.api.support;

import com.wuxianggujun.tinasproutcore.api.BaseApi;

/**
 * @author WuXiangGuJun
 * @create 2023-04-10 19:22
 **/
public class GetLoginInfo extends BaseApi {
    @Override
    public String getAction() {
        return "get_login_info";
    }

    @Override
    public Object getParams() {
        return null;
    }
}
