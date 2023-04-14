package com.wuxianggujun.tinasproutcore;

import com.wuxianggujun.tinasproutcore.support.BotApplicationRegistrar;
import com.wuxianggujun.tinasproutcore.support.BotAutoConfigRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xiaoxu
 * @since 2020-08-07 10:43
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import 主要提供导人配置类的功能
@Import({BotApplicationRegistrar.class, BotAutoConfigRegistrar.class})
public @interface EnableBot {
}
