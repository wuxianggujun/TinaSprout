package com.wuxianggujun.tinasproutcore.event.message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
<<<<<<< HEAD
=======
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
>>>>>>> 远程/dev
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

<<<<<<< HEAD
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
=======
/**
 * @author WuXiangGuJun
 * @create 2023-04-17 14:14
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AddFriendEvent extends BaseEvent {

    @JSONField(name = "flag")
    private String flag;

    @JSONField(name = "request_type")
    private String request_type;

    @JSONField(name = "user_id")
    private Long userId;

    @JSONField(name = "comment")
    private String comment;

    public static boolean isSupport(JSONObject jsonObject) {
        return ("friend".equals(jsonObject.getString("request_type")) && "request".equals(jsonObject.getString("post_type")));
>>>>>>> 远程/dev
    }

}
