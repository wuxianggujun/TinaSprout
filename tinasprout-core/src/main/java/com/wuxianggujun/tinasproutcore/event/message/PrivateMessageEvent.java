package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
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
public class PrivateMessageEvent extends MessageEvent {

    //表示消息的子类型
    @JSONField(name = "sub_type")
    private String subType;

    //一个消息链
    @JSONField(name = "message")
    private JSONArray message;

    //CQ码格式的消息
    @JSONField(name = "raw_message")
    private String rawMessage;

    //字体
    @JSONField(name = "font")
    private Integer font;

    //发送者消息
    @JSONField(name = "sender")
    private JSONObject sender;

    public static boolean isSupport(JSONObject jsonObject) {
        return ("message".equals(jsonObject.getString("post_type"))
                && "private".equals(jsonObject.getString("message_type")));
    }

}
