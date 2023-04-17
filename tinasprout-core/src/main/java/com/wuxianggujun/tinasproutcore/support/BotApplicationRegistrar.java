package com.wuxianggujun.tinasproutcore.support;

import com.wuxianggujun.tinasproutcore.command.injector.CommandParamsInjector;
import com.wuxianggujun.tinasproutcore.command.interceptor.CommandEventInterceptor;
import com.wuxianggujun.tinasproutcore.core.component.BotDispatcher;
import com.wuxianggujun.tinasproutcore.core.component.BotFactory;
import com.wuxianggujun.tinasproutcore.core.component.BotInit;
import com.wuxianggujun.tinasproutcore.core.component.SnowFlakeIdGenerator;
import com.wuxianggujun.tinasproutcore.handler.message.*;
import com.wuxianggujun.tinasproutcore.handler.meta.HeartbeatEventHandler;
import com.wuxianggujun.tinasproutcore.injector.support.*;
import com.wuxianggujun.tinasproutcore.injector.support.friend.FriendInjector;
import com.wuxianggujun.tinasproutcore.injector.support.friend.TempFriendInjector;
import com.wuxianggujun.tinasproutcore.injector.support.group.GroupInjector;
import com.wuxianggujun.tinasproutcore.injector.support.group.MemberInjector;
import com.wuxianggujun.tinasproutcore.injector.support.group.RecallMessageInjector;
import com.wuxianggujun.tinasproutcore.scheduled.FlushCacheScheduled;
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
                CommandEventInterceptor.class.getName(),
                HeartbeatEventHandler.class.getName(),
                PrivateMessageEventHandler.class.getName(),
                GroupMessageEventHandler.class.getName(),
                GroupRecallEventHandler.class.getName(),
                AddFriendEventHandler.class.getName(),
                //CommandEventHandler.class.getName(),
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
                CommandParamsInjector.class.getName(),
                BotInjector.class.getName(),
                FlushCacheScheduled.class.getName(),
                FriendInjector.class.getName(),
        };
    }

}
