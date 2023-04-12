package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
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
public class PrivateMessageEvent extends MessageEvent {

    @JSONField(name = "sub_type")
    private String subType;

    @JSONField(name = "message")
    private JSONArray message;

    @JSONField(name = "raw_message")
    private String rawMessage;

    @JSONField(name = "font")
    private Integer font;

    @JSONField(name = "sender")
    private JSONObject sender;

    public static boolean isSupport(JSONObject jsonObject) {
        return ("message".equals(jsonObject.getString("post_type"))
                && "private".equals(jsonObject.getString("message_type")));
    }

}
