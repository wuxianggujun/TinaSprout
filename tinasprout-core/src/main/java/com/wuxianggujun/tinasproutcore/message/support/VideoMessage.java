package com.wuxianggujun.tinasproutcore.message.support;

import com.alibaba.fastjson.JSON;
import com.wuxianggujun.tinasproutcore.message.Message;
import lombok.Data;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:30
 **/
@Data
public class VideoMessage implements Message {

    private String file;

    private String cover;

    @Override
    public String toString() {
        return "video[" + file + "]";
    }

    @Override
    public String toMessageString() {
        return String.format("{\"type\":\"%s\",\"data\":%s}", "video", JSON.toJSONString(this));
    }

}
