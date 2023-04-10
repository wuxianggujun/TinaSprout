package com.wuxianggujun.tinasproutcore.core.component;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author WuXiangGuJun
 * @create 2023-04-10 19:56
 **/
@Data
public class HandlerMethod {

    private Class<?> type;

    private Object object;

    private Method method;
}
