package com.wuxianggujun.tinasproutcore.core;

import com.alibaba.fastjson.JSONObject;
import com.wuxianggujun.tinasproutcore.api.ApiResult;
import com.wuxianggujun.tinasproutcore.api.support.SendPrivateMsg;
import com.wuxianggujun.tinasproutcore.api.support.SetGroupCard;
import com.wuxianggujun.tinasproutcore.config.BotConfig;
import com.wuxianggujun.tinasproutcore.core.network.BotClient;
import com.wuxianggujun.tinasproutcore.exception.BotException;
import com.wuxianggujun.tinasproutcore.message.CacheMessage;
import com.wuxianggujun.tinasproutcore.message.MessageChain;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WuXiangGuJun
 * @create 2023-03-06 20:52
 **/
@Slf4j
public class Bot {

    private final Map<Long, Friend> friends = new ConcurrentHashMap<>();

    private final Map<Long, Group> groups = new ConcurrentHashMap<>();

    private final Map<Long, Map<Long, Member>> groupMembers = new ConcurrentHashMap<>();

    private final Map<String, Map<Integer, CacheMessage>> cacheMessageChain = new HashMap<>();

    private final Lock cacheMessageChainLock = new ReentrantLock();

    private final CompletableFuture<Long> completableFuture = new CompletableFuture<>();

    private final BotConfig botConfig;
    private final BotClient botClient;

    private long botId = 0;
    private String botName;

    public long getBotId() {
        return botId;
    }

    public String getBotName() {
        return botName;
    }

    public Bot(BotConfig botConfig, BotClient botClient) {
        this.botConfig = botConfig;
        this.botClient = botClient;
    }

    public BotClient getBotClient() {
        return this.botClient;
    }

    public CompletableFuture<Long> getCompletableFuture() {
        return this.completableFuture;
    }

    public void pushGroupCacheMessageChain(Long groupId, Integer messageId, CacheMessage cacheMessage) {
        this.pushCacheMessageChain("group", groupId, messageId, cacheMessage);
    }

    public void pushUserCacheMessageChain(Long groupId, Integer messageId, CacheMessage cacheMessage) {
        this.pushCacheMessageChain("user", groupId, messageId, cacheMessage);
    }

    private void pushCacheMessageChain(String prefix, Long id, Integer messageId, CacheMessage cacheMessage) {
        this.cacheMessageChainLock.lock();
        try {
            Map<Integer, CacheMessage> messageChainMap = this.cacheMessageChain.computeIfAbsent(prefix + id, key -> new LinkedHashMap<>());
            messageChainMap.put(messageId, cacheMessage);
        } finally {
            this.cacheMessageChainLock.unlock();
        }
    }


    public int sendPrivateMessage(long userId, MessageChain messageChain) {
        ApiResult apiResult = this.botClient.invokeApi(new SendPrivateMsg(userId, messageChain), this);
        return this.getObject(apiResult.getData()).getIntValue("message_id");
    }


    private JSONObject getObject(Object object) {
        if (!(object instanceof JSONObject)) {
            throw new BotException(String.format("[%s]调用api失败: 解析结果出错。", this.botName));
        }
        return (JSONObject) object;
    }

    public void setGroupCard(long groupId, long userId, String card) {
        this.botClient.invokeApi(new SetGroupCard(groupId, userId, card), this);
    }

}
