package com.cloud.test;


import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author zl
 * @date 2022/5/31 14:38
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
        Long second = org.joda.time.LocalDateTime.now().toDate().getTime();
        System.out.println(second);
        Long milliSecond = org.joda.time.LocalDateTime.now().toDateTime().getMillis();
        System.out.println(milliSecond);
    }

    @Test
    public void timeLocalDateTime() {
        //获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(second);
        //获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(milliSecond);
    }


}
