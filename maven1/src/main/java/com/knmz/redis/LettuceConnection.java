package com.knmz.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisStringReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;

/**
 * Lettuce
 *
 * @author zl
 * @date 2019/10/10 11:52
 */
public class LettuceConnection {

    private RedisClient client;

    private StatefulRedisConnection<String, String> connection;

    @PostConstruct
    public void init() {
        client = RedisClient.create(RedisURI.create("192.168.1.95", 6379));
        client.setDefaultTimeout(Duration.ofSeconds(10));
        connection = client.connect();
    }

    public RedisCommands<String, String> getRedisCommandsSync() {
        return connection.sync();
    }


    public RedisAsyncCommands<String, String> getRedisCommandsAsync() {
        return connection.async();
    }


    public RedisStringReactiveCommands<String, String> getRedisReactiveCommands() {
        return connection.reactive();
    }

    @PreDestroy
    public void destroy() {
        if (connection != null) {
            connection.close();
        }
        if (client != null) {
            client.shutdown();
        }

    }
}
