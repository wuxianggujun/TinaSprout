package com.wuxianggujun.tinasproutcore.annotation;

import java.lang.annotation.*;

/**
 * @author WuXiangGuJun
 * @create 2023-04-11 14:40
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TempMessageHandler {
    /**
     *限制bot 参数为bot qq  0为不限制
     */
    long bot() default 0;

    /**
     *匹配正则
     */
    String regex() default "none";

    /**
     *限制发言人
     */
    long[] senderIds() default {};

    /**
     *排除发言人
     */
    long[] excludeSenderIds() default {};

}
