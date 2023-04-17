package com.wuxianggujun.tinasproutcore.annotation;

import java.lang.annotation.*;

/**
 * @author WuXiangGuJun
 * @create 2023-04-17 14:02
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddFriendHandler {
    String remark() default "robot";
    boolean approve() default false;
    String flag() default "123456789";
}
