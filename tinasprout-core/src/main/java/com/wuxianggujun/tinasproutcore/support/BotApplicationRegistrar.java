package com.wuxianggujun.tinasproutcore.support;

import com.zhuangxv.bot.core.component.BotDispatcher;
import com.zhuangxv.bot.core.component.BotFactory;
import com.zhuangxv.bot.core.component.BotInit;
import com.zhuangxv.bot.core.component.SnowFlakeIdGenerator;
import com.zhuangxv.bot.handler.message.GroupMessageEventHandler;
import com.zhuangxv.bot.handler.message.GroupRecallEventHandler;
import com.zhuangxv.bot.handler.message.MemberAddEventHandler;
import com.zhuangxv.bot.handler.message.PrivateMessageEventHandler;
import com.zhuangxv.bot.handler.meta.HeartbeatEventHandler;
import com.zhuangxv.bot.injector.support.*;
import com.zhuangxv.bot.injector.support.friend.FriendInjector;
import com.zhuangxv.bot.injector.support.friend.TempFriendInjector;
import com.zhuangxv.bot.injector.support.group.GroupInjector;
import com.zhuangxv.bot.injector.support.group.MemberInjector;
import com.zhuangxv.bot.injector.support.group.RecallMessageInjector;
import com.zhuangxv.bot.scheduled.FlushCacheScheduled;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */

/**
 * ImportSelector接口是spring中导入外部配置的核心接口，在SpringBoot的自动化配置和@EnableXXX(功能性注解)都有它的存在。
 */
public class BotApplicationRegistrar implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                BotFactory.class.getName(),
                BotDispatcher.class.getName(),
                SnowFlakeIdGenerator.class.getName(),
                HeartbeatEventHandler.class.getName(),
                PrivateMessageEventHandler.class.getName(),
                GroupMessageEventHandler.class.getName(),
                GroupRecallEventHandler.class.getName(),
                MemberAddEventHandler.class.getName(),
                RecallMessageInjector.class.getName(),
                BotInit.class.getName(),
                MessageStringInjector.class.getName(),
                GroupInjector.class.getName(),
                MessageChainInjector.class.getName(),
                TempFriendInjector.class.getName(),
                MemberInjector.class.getName(),
                MessageIdInjector.class.getName(),
                MessageIdIntInjector.class.getName(),
                BotInjector.class.getName(),
                FlushCacheScheduled.class.getName(),
                FriendInjector.class.getName(),
        };
    }

}
