package com.wuxianggujun.tinasproutcore.message.support;

import com.alibaba.fastjson.JSON;
import com.wuxianggujun.tinasproutcore.message.Message;
import lombok.Data;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:30
 **/
@Data
public class ShareMessage implements Message {

    private String url;

    private String title;

    private String content;

    private String image;

    @Override
    public String toString() {
        return "share[" + title + "]";
    }

    @Override
    public String toMessageString() {
        return String.format("{\"type\":\"%s\",\"data\":%s}", "share", JSON.toJSONString(this));
    }

}
