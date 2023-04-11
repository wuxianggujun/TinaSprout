package com.wuxianggujun.tinasproutcore.core.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author WuXiangGuJun
 * @create 2023-04-11 16:03
 **/
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BotInit implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        BotFactory.initHandlerMethod();
        BotFactory.initBot();
    }
}
