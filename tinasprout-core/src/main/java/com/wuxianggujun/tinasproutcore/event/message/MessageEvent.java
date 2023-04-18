package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MessageEvent extends BaseEvent {

    //消息ID
    @JSONField(name = "message_id")
    private Integer messageId;

    //消息类型
    @JSONField(name = "message_type")
    private String messageType;

    //发送者信息
    @JSONField(name = "user_id")
    private Long userId;

}
