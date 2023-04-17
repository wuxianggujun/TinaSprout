package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class AddFriendEvent extends MessageEvent {

    @JSONField(name = "flag")
    private String frag;

    @JSONField(name = "approve")
    private boolean approve;

    @JSONField(name = "remark")
    private String remark;

    @JSONField(name = "comment")
    private String message;


    public static boolean isSupport(JSONObject jsonObject) {

        return (("request").equals(jsonObject.getString("post_type"))
                && "friend".equals(jsonObject.getString("request_type")));


    }

    public String toString() {
        return "{" + "Frag:" + frag +
                "Approve:" +  approve +
                "Comment:" + message +
                "Remark:" + remark + "}";
    }
}