package com.knmz.test;

import com.knmz.util.Utils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
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

}
