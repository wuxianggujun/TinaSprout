package com.wuxianggujun.tinasproutcore.core.network;

import com.wuxianggujun.tinasproutcore.api.ApiResult;
import com.wuxianggujun.tinasproutcore.api.BaseApi;
import com.wuxianggujun.tinasproutcore.core.Bot;

/**
 * @author xiaoxu
 * @since 2022/5/19 10:59
 */
public interface BotClient {

    ApiResult invokeApi(BaseApi baseApi, Bot bot);

    void heartbeat();

}
