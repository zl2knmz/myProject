package com.cloud.util.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zl
 * @date 2019/10/15 11:17
 * 应用程序缓存，ConcurrentHashMap实现缓存，使用单例模式。
 */
public class MapCacheSingleton {
    private static MapCacheSingleton instance = new MapCacheSingleton();

    private Map<String, CacheObject> map;

    private MapCacheSingleton() {
        map = new ConcurrentHashMap<>();
    }

    public static MapCacheSingleton getInstance() {
        return instance;
    }

    /**
     * 设置没有过期时间缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        this.set(key, value, -1L);
    }

    /**
     * 设置带时间缓存，过期的value还在内存中，只是不取。
     *
     * @param key   键
     * @param value 值
     * @param time  缓存时间 (毫秒戳)
     */
    public void set(String key, Object value, Long time) {
        CacheObject cacheObject = new CacheObject(key, value, time);
        map.put(key, cacheObject);
    }

    /**
     * 获取缓存，过期的value还在内存中，只是不取。
     *
     * @param key 键
     * @return Object
     */
    public Object get(String key) {
        CacheObject cacheObject = map.get(key);
        if (cacheObject != null) {
            // 当前毫秒戳
            Long currentTime = System.currentTimeMillis();
            // 未过期的value
            if (cacheObject.getTime() <= 0 || currentTime < cacheObject.getTime()) {
                return cacheObject.getValue();
            } else {
                // 删除过期的key
                map.remove(key);
            }
        }
        return null;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     */
    public void delete(String key) {
        map.remove(key);
    }

    /**
     * 清空缓存
     */
    public void clear() {
        map.clear();
    }

    class CacheObject {
        private String key;
        private Object value;
        private Long time;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        CacheObject(String key, Object value, Long time) {
            this.key = key;
            this.value = value;
            this.time = time;
        }
    }
}
