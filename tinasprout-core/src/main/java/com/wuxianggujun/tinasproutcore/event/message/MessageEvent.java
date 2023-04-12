package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:21
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MessageEvent extends BaseEvent {
    
    @JSONField(name = "message_id")
    private Integer messageId;

    @JSONField(name = "message_type")
    private String messageType;

    @JSONField(name = "user_id")
    private Long userId;

}
