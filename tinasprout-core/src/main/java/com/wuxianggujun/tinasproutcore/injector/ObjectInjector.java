package com.wuxianggujun.tinasproutcore.injector;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;

/**
 * @author WuXiangGuJun
 * @create 2023-04-10 19:59
 **/
public interface ObjectInjector<T> {
    
    String[] getType();
    
    Class<T> getClassType();
    
    T getObject(BaseEvent event, Bot bot);
}
