package com.wuxianggujun.tinasproutcore.core.network;

import com.wuxianggujun.tinasproutcore.api.ApiResult;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import com.wuxianggujun.tinasproutcore.core.Bot;

/**
 * @author WuXiangGuJun
 * @create 2023-04-05 8:55
 **/
public interface BotClient {
    ApiResult invokeApi(BaseApi baseApi, Bot bot);
    
    void heartbeat();
}
