package com.wuxianggujun.tinasproutcore.core.network;

import com.wuxianggujun.tinasproutcore.config.BotConfig;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.component.BotDispatcher;
import com.wuxianggujun.tinasproutcore.core.network.wsr.WsrNetwork;
import com.wuxianggujun.tinasproutcore.network.BotNetwork;
import com.wuxianggujun.tinasproutcore.network.ws.WsNetwork;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author WuXiangGuJun
 * @create 2023-04-11 15:26
 **/
@Slf4j
public class BotNetworkFactory {

    public static void initBotNetwork(List<BotConfig> botConfigs, Map<Long, Bot> bots, BotDispatcher botDispatcher) {
        for (BotConfig botConfig : botConfigs) {
            BotNetwork botNetwork;
            if ("ws".equals(botConfig.getType())) {
                botNetwork = new WsNetwork();
            } else if ("ws-reverse".equals(botConfig.getType())) {
                botNetwork = new WsrNetwork();
            } else if ("http".equals(botConfig.getType())) {
                log.warn(String.format("%s is not supported.", botConfig.getType()));
                return;
            } else {
                log.warn(String.format("%s is not supported.", botConfig.getType()));
                return;
            }
            botNetwork.init(botConfig, bots, botDispatcher);
        }
    }
}
