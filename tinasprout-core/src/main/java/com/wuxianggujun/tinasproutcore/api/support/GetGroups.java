package com.wuxianggujun.tinasproutcore.api.support;

import com.wuxianggujun.tinasproutcore.api.BaseApi;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 20:39
 **/
public class GetGroups extends BaseApi {
    @Override
    public String getAction() {
        return "get_group_list";
    }

    @Override
    public Object getParams() {
        return null;
    }
}
