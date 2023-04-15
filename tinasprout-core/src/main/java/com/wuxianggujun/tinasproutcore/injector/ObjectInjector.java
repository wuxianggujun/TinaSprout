package com.wuxianggujun.tinasproutcore.injector;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
public interface ObjectInjector<T> {

    String[] getType();

    Class<T> getClassType();

    T getObject(BaseEvent event, Bot bot);

}