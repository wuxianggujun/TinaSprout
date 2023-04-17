package com.wuxianggujun.tinasproutcore.message;

import lombok.Data;

@Data
public class FriendMessage {


    private String flag;
    private boolean approve;
    private String remark;

    public String toString() {
        return "{" + "Flag:" + flag +
                "approve:" + approve +
                "remark" + remark + "}";
    }

}
