package com.wuxianggujun.tinasproutcore.handler.meta;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.meta.HeartbeatEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WuXiangGuJun
 * @create 2023-04-12 16:10
 **/
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HeartbeatEventHandler implements EventHandler {
    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (!HeartbeatEvent.isSupport(jsonObject)) {
            return;
        }
        bot.getBotClient().heartbeat();
        HeartbeatEvent heartbeatEvent = jsonObject.toJavaObject(HeartbeatEvent.class);
        log.debug("heartbeat-event: " + heartbeatEvent);
    }
}
