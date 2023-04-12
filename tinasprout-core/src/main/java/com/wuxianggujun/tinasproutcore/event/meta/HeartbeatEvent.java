package com.wuxianggujun.tinasproutcore.event.meta;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:12
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HeartbeatEvent extends BaseEvent {

    @JSONField(name = "meta_event_type")
    private String metaEventType;

    @JSONField(name = "interval")
    private Long interval;


    public static boolean isSupport(JSONObject jsonObject) {
        return ("meta_event".equals(jsonObject.getString("post_type"))
                && "heartbeat".equals(jsonObject.getString("meta_event_type")));
    }

}
