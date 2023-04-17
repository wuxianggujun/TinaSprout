package com.wuxianggujun.tinasproutcore.command.handler;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.command.annotion.CommandHandler;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.component.BotFactory;
import com.wuxianggujun.tinasproutcore.core.component.HandlerMethod;
import com.wuxianggujun.tinasproutcore.event.message.GroupMessageEvent;
import com.wuxianggujun.tinasproutcore.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author WuXiangGuJun
 * @create 2023-04-14 19:05
 **/

@Slf4j
public class CommandEventHandler implements EventHandler {

    @Override
    public void handle(JSONObject jsonObject, Bot bot) {
        if (!GroupMessageEvent.isSupport(jsonObject)) {
            return;
        }
        Set<HandlerMethod> handlerMethods = BotFactory.getHandlerMethodListByAnnotation(handlerMethod -> handlerMethod.getMethod().isAnnotationPresent(CommandHandler.class));

        for (HandlerMethod handlerMethod : handlerMethods) {
            Class<?>[] parameterTypes = handlerMethod.getMethod().getParameterTypes();
            Object[] objects = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                //ObjectInjector<?> objectInjector = 
                log.info("ParameterType : " + parameterType.getSimpleName());
            }

        }

    }


}
