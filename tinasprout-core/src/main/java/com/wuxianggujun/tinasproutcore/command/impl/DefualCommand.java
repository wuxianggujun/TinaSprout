package com.wuxianggujun.tinasproutcore.command.impl;

import com.wuxianggujun.tinasproutcore.command.Command;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WuXiangGuJun
 * @create 2023-04-16 23:46
 **/
@Slf4j
public class DefualCommand implements Command {
    
    
    @Override
    public void execute() {
        log.info("命令模式: " + this.getClass().getSimpleName());
    }
    
}
