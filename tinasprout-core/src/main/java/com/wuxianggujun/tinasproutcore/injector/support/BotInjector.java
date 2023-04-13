package com.wuxianggujun.tinasproutcore.injector.support;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author WuXiangGuJun
 * @create 2023-04-13 17:48
 **/
public class BotInjector implements ObjectInjector<Bot> {
    @Override
    public Class<Bot> getClassType() {
        return Bot.class;
    }

    @Override
    public String[] getType() {
        return new String[]{"all"};
    }

    @Override
    public Bot getObject(BaseEvent event, Bot bot) {
        return bot;
    }
}
