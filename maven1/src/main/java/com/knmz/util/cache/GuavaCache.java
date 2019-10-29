package com.knmz.util.cache;

import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zl
 * @date 2019/10/18 18:29
 */
public class GuavaCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaCache.class);

    /**
     * 使用google guava缓存处理
     */
    private static Cache<String, Object> cache;

    /*
     * maximumSize：最大key数 10000个
     * initialCapacity：初始容量 100
     * concurrencyLevel：并发数 4
     * expireAfterWrite：给定时间没有被写访问(创建或覆盖) 30分钟
     * recordStats：开启 Guava Cache 的统计功能，打开后Cache.stats()方法会返回统计信息。
     * removalListener：移除监听器
     */
    static {
        cache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .initialCapacity(100)
                .concurrencyLevel(4)
                .recordStats()
                .removalListener(new RemovalListener<String, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Object> rn) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info("guava cache remove {}:{}", rn.getKey(), rn.getValue());
                        }
                    }
                })
                .build();
    }
    
    /**
     * 获取缓存
     */
    public static Object get(String key) {
        return StringUtils.isNotEmpty(key) ? cache.getIfPresent(key) : null;
    }

    /**
     * 放入缓存
     */
    public static void put(String key, Object value) {
        if (StringUtils.isNotEmpty(key) && value != null) {
            cache.put(key, value);
        }
    }

    /**
     * 移除缓存
     */
    public static void remove(String key) {
        if (StringUtils.isNotEmpty(key)) {
            cache.invalidate(key);
        }
    }

    /**
     * 批量删除缓存
     */
    public static void remove(List<String> keys) {
        if (keys != null && keys.size() > 0) {
            cache.invalidateAll(keys);
        }
    }

    /**
     * 统计信息
     * hitCount=命中的次数,
     * missCount=未命中次数,
     * loadSuccessCount=成功加载新值的次数,
     * loadExceptionCount=失败加载新值的次数,
     * totalLoadTime=全部加载时间,
     * evictionCount=丢失的条数
     */
    public static CacheStats stats() {
        return cache.stats();
    }
}
