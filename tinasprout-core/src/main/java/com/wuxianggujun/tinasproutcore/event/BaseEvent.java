package com.wuxianggujun.tinasproutcore.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class BaseEvent {

    //上报类型
    @JSONField(name = "post_type")
    private String postType;

    //收到事件的机器人 QQ 号
    @JSONField(name = "self_id")
    private Long selfId;

    //事件发生的时间戳
    @JSONField(name = "time")
    private Long time;

}
