package com.cloud.test;

import com.cloud.util.cache.MapCacheSingleton;
import com.cloud.util.Utils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zl
 * @date 2019/8/27 10:54
 */
public class MapTest {
    /**
     * 测试一个map占用内存的大小
     */
    @Test
    public void testMapSize() throws IOException {
        Map<String, Integer> user = new HashMap<>(16);
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

    /**
     * 测试 map
     */
    @Test
    public void testMapRemove() throws IOException {
        Map<String, String> map = new HashMap<>(16);

        String key = "杜甫";
        String value = "诗圣";

        String key1 = "李白";
        String value1 = "青莲居士";

        String key2 = "欧阳修";
        String value2 = "六一居士";

        String key3 = "白居易";
        String value3 = "香山居士";

        String key4 = "李清照";
        String value4 = "易安居士";

        String key5 = "苏轼";
        String value5 = "东坡居士";

        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);

        map.remove("杜甫");
        System.out.println(map);

        map.remove("杜甫");
        System.out.println(map);
    }
}
