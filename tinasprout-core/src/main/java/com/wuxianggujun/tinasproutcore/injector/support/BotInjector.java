package com.wuxianggujun.tinasproutcore.injector.support;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
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
