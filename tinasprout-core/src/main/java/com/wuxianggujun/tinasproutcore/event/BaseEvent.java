package com.wuxianggujun.tinasproutcore.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author WuXiangGuJun
 * @create 2023-04-10 20:01
 **/
@Data
public class BaseEvent {
    
    @JSONField(name = "post_type")
    private String postType;

    @JSONField(name = "self_id")
    private Long selfId;
    
    @JSONField(name = "time")
    private Long time;
}
