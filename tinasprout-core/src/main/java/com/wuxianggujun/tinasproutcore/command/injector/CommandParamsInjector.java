package com.wuxianggujun.tinasproutcore.command.injector;

import com.wuxianggujun.tinasproutcore.command.CommandParams;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.event.message.GroupMessageEvent;
import com.wuxianggujun.tinasproutcore.event.message.PrivateMessageEvent;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;

/**
 * @author WuXiangGuJun
 * @create 2023-04-17 0:04
 **/
public class CommandParamsInjector implements ObjectInjector<CommandParams> {
    @Override
    public String[] getType() {
        return new String[]{"message"};
    }

    @Override
    public Class<CommandParams> getClassType() {
        return CommandParams.class;
    }

    @Override
    public CommandParams getObject(BaseEvent event, Bot bot) {
        if (event instanceof GroupMessageEvent || event instanceof PrivateMessageEvent) {
            CommandParams params = new CommandParams();
            params.setName("WuXiangGuJun");
            return params;
        }
        return null;
    }
}
