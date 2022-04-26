package com.cloud.test;

import com.cloud.redis.LettuceClient;
import com.cloud.redis.RedisBitSet;
import com.cloud.util.MyConstants;
import com.cloud.util.bloom.BloomFilter;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author zl
 * @date 2019/8/23 16:04
 */
public class RedisTest {

    public static void main(String[] args) {
        String key = String.format(MyConstants.TEST_ID_FORMAT, 1012);
        System.out.println("key = " + key);

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

    @Test
    public void bitSetTest() {
        BloomFilter<String> filter = new BloomFilter<String>(0.0001, 10000);
        Jedis jedis = new Jedis("192.168.16.225", 6379);
        //jedis.auth("1234");
        filter.bind(new RedisBitSet(jedis, "bloomfilter:key:name"));

        filter.add("test1");
        filter.add("test2");
        filter.add("test3");
        System.out.println(filter.contains("test2"));
        System.out.println(filter.contains("test4"));
        System.out.println(filter.count());
        System.out.println(filter.isEmpty());
        filter.clear();

        System.out.println(filter.isEmpty());
        System.out.println(filter.contains("test1"));
    }

}
