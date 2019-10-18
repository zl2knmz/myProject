package com.knmz.test;

import com.knmz.util.cache.MapCacheSingleton;
import com.knmz.util.Utils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zl
 * @Date: 2019/8/27 10:54
 */
public class MapTest {
    /**
     * 测试一个map占用内存的大小
     */
    @Test
    public void testMapSize() throws IOException {
        Map<String, Integer> user = new HashMap<>();
        int i = 0;
        int j = 1000000;
        String key = "123456";
        int value = 233;
        while (i < j){
            user.put(key + i, i + value);
            i++;
        }
        FileOutputStream out = new FileOutputStream("E:\\Program Files\\image\\test.txt");
        out.write(Utils.getObjectMapper().writeValueAsBytes(user));
    }

    /**
     * 测试 ConcurrentHashMap实现缓存
     */
    @Test
    public void testMapCacheSingleton() throws IOException {
        MapCacheSingleton mapCacheSingleton = MapCacheSingleton.getInstance();
        mapCacheSingleton.set("key", "value");
        String str = mapCacheSingleton.get("key") == null ? null : mapCacheSingleton.get("key").toString();
        System.out.println(str);

        long time = LocalDateTime.now().plusMinutes(1L).toInstant(ZoneOffset.of("+8")).toEpochMilli();;
        long time1 = Instant.now().minusSeconds(100L).getEpochSecond();
        mapCacheSingleton.set("key1", "value-time", time);
        String str1 = mapCacheSingleton.get("key1") == null ? null : mapCacheSingleton.get("key1").toString();
        System.out.println(str1);
    }

}
