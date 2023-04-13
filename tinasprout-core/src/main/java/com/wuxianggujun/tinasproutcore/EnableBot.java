package com.wuxianggujun.tinasproutcore;

import com.wuxianggujun.tinasproutcore.support.BotApplicationRegistrar;
import com.wuxianggujun.tinasproutcore.support.BotAutoConfigRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author WuXiangGuJun
 * @create 2023-04-13 17:57
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({BotApplicationRegistrar.class, BotAutoConfigRegistrar.class})
public @interface EnableBot {
}
