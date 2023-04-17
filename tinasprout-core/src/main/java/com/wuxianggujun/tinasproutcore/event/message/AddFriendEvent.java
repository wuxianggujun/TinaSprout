package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AddFriendEvent extends MessageEvent {

    @JSONField(name = "flag")
    private String frag;

    @JSONField(name = "comment")
    private String message;


    public static boolean isSupport(JSONObject jsonObject) {

        return (("request").equals(jsonObject.getString("post_type"))
                &&"friend".equals(jsonObject.getString("request_type")));
    }

}
