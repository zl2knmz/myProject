package com.cloud.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author zl
 * @date 2021/6/9 11:34
 */
public class UuidUtil {

    public static final String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 生成8位简短主键ID的工具类
     */
    public static String getShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String S4() {
        return UUID.randomUUID().toString().substring(5, 13);
    }

    public static void main(String[] args) {
        // 生成8位简短主键ID的工具类
//        System.out.println(getShortUuid());

        // uuid后8位
//        System.out.println("S4=" + S4());

        System.out.println("毫秒戳=" + System.currentTimeMillis());

        System.out.println("秒戳=" + String.valueOf(System.currentTimeMillis()).substring(0, 10));

        Random r = new Random(1);
        System.out.println(r.nextInt(10000000));
        System.out.println(r.nextLong());
    }

}


