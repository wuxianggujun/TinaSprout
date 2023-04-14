package com.wuxianggujun.tinasproutcore.command.annotion;

import java.lang.annotation.*;

/**
 * @author WuXiangGuJun
 * @create 2023-04-14 18:38
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommandHandler {

    /**
     * 设置
     *
     * @return 命令操作符
     */
    String prefix() default "/";

    /**
     * 命令，用于解析使用
     * 例如： /none
     *
     * @return 命令参数
     */
    String command() default "none";

}
