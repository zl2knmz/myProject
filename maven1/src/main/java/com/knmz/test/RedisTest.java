package com.knmz.test;

import com.knmz.redis.JedisHelper;
import com.knmz.redis.LettuceClient;
import com.knmz.util.MyConstants;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zl
 * @Date: 2019/8/23 16:04
 */
public class RedisTest {

    public static void main(String[] args) {
        String key = String.format(MyConstants.TEST_ID_FORMAT, 1012);
        System.out.println("key = "+key);

        // 初始化redis客户端
//        JedisHelper.init();

//        if (JedisHelper.exists(key)) {
//            System.out.println("exists key = "+key);
//        }

        // 设置key value并指定这个键值的有效期
//        JedisHelper.setex(key, System.currentTimeMillis(), 1, TimeUnit.DAYS);

        // 通过key给field设置指定的值,如果key不存在,则先创建。
//        JedisHelper.hset(MyConstants.TEST_REDIS, "123", "456");

        // 先对value进行加值+1操作，再为 key设置生存时间，以毫秒为单位设置key的过期时间戳。
//        JedisHelper.incrAndPexpireAt(String.format(MyConstants.TEST_TO_FORMAT, "123"), Utils.getTimeInMillisBeforeTomorrow());

        // 通过key 删除指定的 field
//        JedisHelper.hdel(MyConstants.TEST_REDIS, "123");

        // Lettuce操作redis
        LettuceClient lettuceClient = new LettuceClient();
        lettuceClient.setex(key, "lettuce", 120);
    }

}
