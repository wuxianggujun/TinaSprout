package com.wuxianggujun.tinasproutcore.api.support;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WuXiangGuJun
 * @create 2023-04-09 21:46
 **/
public class GetGroup extends BaseApi {
    
    private final GetGroup.Param param;
    
    public GetGroup(long groupId) {
       this.param = new GetGroup.Param();
       this.param.setGroupId(groupId);
    }

    @Override
    public String getAction() {
        return "get_group_info";
    }

    @Override
    public Object getParams() {
        return param;
    }
    
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Param{
        
        @JSONField(name = "group_id")
        private long groupId;
        
    }
    
}
