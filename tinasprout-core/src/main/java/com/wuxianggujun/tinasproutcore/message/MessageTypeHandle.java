package com.wuxianggujun.tinasproutcore.message;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.message.support.*;
import org.springframework.util.StringUtils;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:25
 **/
public class MessageTypeHandle {

    public static Message getMessage(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        String type = jsonObject.getString("type");
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        Class<? extends Message> messageClass;
        switch (type) {
            case "text" -> messageClass = TextMessage.class;
            case "face" -> messageClass = FaceMessage.class;
            case "record" -> messageClass = RecordMessage.class;
            case "image" -> messageClass = ImageMessage.class;
            case "at" -> messageClass = AtMessage.class;
            case "video" -> messageClass = VideoMessage.class;
            case "share" -> messageClass = ShareMessage.class;
            case "reply" -> messageClass = ReplyMessage.class;
            default -> {
                return new UnknownMessage() {{
                    setJson(jsonObject.toJSONString());
                }};
            }
        }
        return jsonObject.getObject("data", messageClass);
    }

}