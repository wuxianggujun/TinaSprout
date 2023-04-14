package com.wuxianggujun.tinasproutcore.core.network;


import com.wuxianggujun.tinasproutcore.config.BotConfig;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.component.BotDispatcher;

import java.util.Map;

/**
 * @author xiaoxu
 * @since 2022/5/19 16:33
 */
public interface BotNetwork {

    void init(BotConfig botConfig, Map<Long, Bot> bots, BotDispatcher botDispatcher);

}
