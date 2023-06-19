package com.cloud.test;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cloud.util.Utils;
import com.cloud.util.cache.MapCacheSingleton;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
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
        while (i < j) {
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

        long time = LocalDateTime.now().plusMinutes(1L).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        ;
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

        System.out.println(map.get("杜甫1"));
    }

    /**
     * 测试 对象序列化成map
     */
    @Test
    public void testObjectToMap() {
        User user = new User().setUser();
        // 1、利用反射转
        long s1 = System.currentTimeMillis();
        Map<String, Object> dataMap = objectToMap(user);
        long s2 = System.currentTimeMillis();
        System.out.println("1、花费时间毫秒：" + (s2 - s1));
        System.out.println(dataMap);

        // 2、fastjson的ParseObject 方法
        long s3 = System.currentTimeMillis();
//        Map<String, Object> stringObjectMap = JSON.parseObject(JSON.toJSONString(user), new TypeReference<Map<String, Object>>() {});
        Map<String, Object> stringObjectMap = JSONObject.parseObject(JSON.toJSONString(user), new TypeReference<Map<String, Object>>() {});
//        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(user);
        long s4 = System.currentTimeMillis();
        System.out.println("2、花费时间毫秒：" + (s4 - s3));
        System.out.println(stringObjectMap);

    }

    private static Map<String, Object> objectToMap(Object object) {
        Map<String, Object> dataMap = new HashMap<>(16);
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                dataMap.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }
}

@Data
class User {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private Double salary;
    @JsonProperty("create_date")
    private Date createDate;

    public User setUser() {
        User user = new User();
        user.setId(123L);
        user.setName("王二");
        user.setAge(18);
        user.setBirthday(LocalDateTime.now());
        user.setSalary(2344.4D);
        user.setCreateDate(new Date());
        return user;
    }
}