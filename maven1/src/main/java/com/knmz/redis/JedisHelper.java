package com.knmz.redis;

import com.knmz.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * JedisHelper
 *
 * @author zl
 * @date 2019/2/12 16:39
 */
public class JedisHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(JedisHelper.class);
//    private final static String KEY_PREFIX = "DATAX";
    private final static String OK = "OK";
    private static JedisPool jedisPool;

    /*public static void init(RedisConfiguration config) {
        if (jedisPool == null && config != null) {
            try {
                final JedisPoolConfig poolConfig = new JedisPoolConfig();
                poolConfig.setMaxTotal(config.getMaxTotal());
                poolConfig.setMaxIdle(config.getMaxIdle());
                poolConfig.setMinIdle(config.getMinIdle());
                poolConfig.setTestOnBorrow(true);
                poolConfig.setTestOnReturn(true);
                poolConfig.setTestWhileIdle(true);
                poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
                poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
                poolConfig.setNumTestsPerEvictionRun(3);
                poolConfig.setBlockWhenExhausted(true);
                jedisPool = new JedisPool(poolConfig, config.getHost(), config.getPort(), 2000);
            } catch (Exception e) {
                LOGGER.error("JedisHelper.init take error.", e);
            }
        }
    }*/

    public static void init() {
        if (jedisPool == null) {
            try {
                final JedisPoolConfig poolConfig = new JedisPoolConfig();
                poolConfig.setMaxTotal(256);
                poolConfig.setMaxIdle(128);
                poolConfig.setMinIdle(16);
                poolConfig.setTestOnBorrow(true);
                poolConfig.setTestOnReturn(true);
                poolConfig.setTestWhileIdle(true);
                poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
                poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
                poolConfig.setNumTestsPerEvictionRun(3);
                poolConfig.setBlockWhenExhausted(true);
                jedisPool = new JedisPool(poolConfig, "192.168.1.95", 6379, 2000);
            } catch (Exception e) {
                LOGGER.error("JedisHelper.init take error.", e);
            }
        }
    }

    public static void destory() {
        if (jedisPool != null) {
            try {
                jedisPool.destroy();
            } catch (Exception e) {
                LOGGER.error("JedisHelper.destroy() take error.", e);
            }
        }
    }

    /*private static String setKeyPrefix(String key) throws IllegalArgumentException {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("jedis key can not be blank.");
        }
        return String.format("%s_%s", KEY_PREFIX, key);
    }*/


    public static boolean ping() {
        boolean ret = false;
        try (Jedis jedis = jedisPool.getResource()) {
            ret = "PONG".equals(jedis.ping());
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.ping take error.", ex);
        }
        return ret;
    }

    /**
     * 获取指定key的值,如果key不存在返回null，如果该Key存储的不是字符串，会抛出一个错误
     *
     * @param key
     */
    public static String get(String key) {
        String ret = null;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            ret = jedis.get(key);
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.get take error.", ex);
        }
        return ret;
    }

    public static <T> T get(String key, Class<T> type, T defaultValue) {
        T ret = defaultValue;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)) {
                ret = Utils.getObjectMapper().reader(type).readValue(value);
            }
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.get<T> take error.", ex);
        }
        return ret;
    }

    /**
     * 设置key的值为value
     *
     * @param key
     * @param value
     */
    public static boolean set(String key, Object value) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.set(key, Utils.getObjectMapper().writeValueAsString(value));
            if (OK.equals(result)) {
                ret = true;
            }
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.set take error.", ex);
        }
        return ret;
    }

    /**
     * 设置key value并指定这个键值的有效期
     *
     * @param key
     * @param duration
     * @param value
     */
    public static boolean setex(String key, Object value, long duration, TimeUnit timeUnit) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.setex(key, Math.toIntExact(timeUnit.toSeconds(duration)), Utils.getObjectMapper().writeValueAsString(value));
            if (OK.equals(result)) {
                ret = true;
            }
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.setex take error.", ex);
        }
        return ret;
    }

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param key
     */
    public static boolean del(String key) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            ret = jedis.del(key) == 1;
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.del take error.", ex);
        }
        return ret;
    }

    /**
     * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
     *
     * @param key
     */
    public static boolean incr(String key) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            ret = jedis.incr(key) > 0;
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.incr take error.", ex);
        }
        return ret;
    }

    /**
     * 为 key设置生存时间，以毫秒为单位设置 key 的过期时间戳。
     *
     * @param key
     * @param millisecondsTimestamp
     */
    public static boolean pexpireAt(String key, long millisecondsTimestamp) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            ret = jedis.pexpireAt(key, millisecondsTimestamp) > 0;
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.pexpireAt take error.", ex);
        }
        return ret;
    }

    /**
     * 先对value进行加值+1操作，再为 key设置生存时间，以毫秒为单位设置key的过期时间戳。
     *
     * @param key
     * @param millisecondsTimestamp
     */
    public static boolean incrAndPexpireAt(String key, long millisecondsTimestamp) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.incr(key) > 0) {
                if (jedis.pttl(key) < 0) {
                    ret = jedis.pexpireAt(key, millisecondsTimestamp) > 0;
                } else {
                    ret = true;
                }
            }
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.incrAndPexpireAt take error.", ex);
        }
        return ret;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     */
    public static boolean exists(String key) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            ret = jedis.exists(key);
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.exists take error.", ex);
        }
        return ret;
    }

    /**
     * 通过key给field设置指定的值,如果key不存在,则先创建
     *
     * @param key
     * @param field
     * @param value
     */
    public static boolean hset(String key, String field, String value) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(key, field, value);
            ret = true;
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.hset take error.", ex);
        }
        return ret;
    }

    /**
     * 通过key 删除指定的 field
     *
     * @param key
     * @param fields 可以是 一个 field 也可以是 一个数组
     */
    public static boolean hdel(String key, String... fields) {
        boolean ret = false;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel(key, fields);
            ret = true;
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.hdel take error.", ex);
        }
        return ret;
    }

    /**
     * 通过key获取所有的field和value
     *
     * @param key
     */
    public static Map<String, String> hgetAll(String key) {
        Map<String, String> ret = null;
//        key = setKeyPrefix(key);
        try (Jedis jedis = jedisPool.getResource()) {
            ret = jedis.hgetAll(key);
        } catch (Exception ex) {
            LOGGER.error("JedisHelper.hgetAll take error.", ex);
        }
        return ret;
    }
}
