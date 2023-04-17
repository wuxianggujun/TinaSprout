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

    /**
     * 是否同意请求
     *
     * @return false 默认不处理
     */
    boolean approve() default false;

    /**
     * 添加后的好友备注（仅在同意后有用）
     *
     * @return string 备注信息
     */
    String remark() default "none";

}
