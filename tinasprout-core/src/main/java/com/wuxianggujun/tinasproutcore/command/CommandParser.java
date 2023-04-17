package com.wuxianggujun.tinasproutcore.command;

/**
 * @author WuXiangGuJun
 * @create 2023-04-16 23:43
 **/
public class CommandParser {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
        }
    }


}
