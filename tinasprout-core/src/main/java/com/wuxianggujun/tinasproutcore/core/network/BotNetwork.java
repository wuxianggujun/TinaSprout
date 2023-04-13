package com.wuxianggujun.tinasproutcore.core.network;

import com.wuxianggujun.tinasproutcore.config.BotConfig;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.component.BotDispatcher;

import java.util.Map;

/**
 * @author WuXiangGuJun
 * @create 2023-04-08 22:54
 **/
public interface BotNetwork {
    void init(BotConfig botConfig, Map<Long, Bot> bots, BotDispatcher botDispatcher);
}
