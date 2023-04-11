package com.wuxianggujun.tinasproutcore.core;

/**
 * @author WuXiangGuJun
 * @create 2023-04-11 15:46
 **/
public interface BotContext {
    boolean approve(Long qq, String token);

    void connected(Bot bot);
}
