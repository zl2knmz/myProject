package com.knmz.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zl
 * @date 2019/11/21 10:14
 * java 8 时间
 */
public class Java8DateTest {
    public static void main(String[] args) {
        // 13位毫秒时间戳 1574247317549
        long a = System.currentTimeMillis();
        System.out.println(a);

        // 15位纳秒级时间戳 114587769486220
        long b = System.nanoTime();
        System.out.println(b);

        // 10位秒级时间戳 1574247317
        long c = Instant.now().getEpochSecond();
        System.out.println(c);

        // 秒数，时间线后面的秒数字段中的纳秒数。始终是正的，并且从不超过99999999。
        int d = Instant.now().getNano();
        System.out.println(d);

        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        // LocalDateTime 当前时间=2019-11-20T18:55:17.983
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime 当前时间=" + localDateTime);

        String formatTime = localDateTime.format(df);
        System.out.println("LocalDateTime formatter 当前时间=" + formatTime);

        String time = "2018-06-12 16:04:43";
        LocalDateTime localDateTime1 = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        System.out.println("LocalDateTime parse 当前时间=" + localDateTime1);

        // LocalTime 当前时间=18:55:17.983
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime 当前时间=" + localTime);
    }
}
