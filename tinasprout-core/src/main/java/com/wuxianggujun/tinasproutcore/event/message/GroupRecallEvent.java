package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:53
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GroupRecallEvent extends BaseEvent {

    @JSONField(name = "notice_type")
    private String noticeType;

    @JSONField(name = "group_id")
    private Long groupId;

    @JSONField(name = "user_id")
    private Long userId;

    @JSONField(name = "operator_id")
    private Long operatorId;

    @JSONField(name = "message_id")
    private Integer messageId;

    public static boolean isSupport(JSONObject jsonObject) {
        return ("notice".equals(jsonObject.getString("post_type"))
                && "group_recall".equals(jsonObject.getString("notice_type")));
    }

}
