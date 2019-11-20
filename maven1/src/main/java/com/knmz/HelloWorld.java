package com.knmz;

import com.knmz.util.LatLonUtil;
import com.knmz.util.Snowflake;
import com.knmz.util.Utils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HelloWorld {
    public static void main(String[] args) {
        /*System.out.println("helloWorld !");
        int a = DateTime.now().getHourOfDay();
        int b = DateTime.now().getMinuteOfHour();
        System.out.println(111);

        DateTimeFormatter datetime_wd_ticket = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String syncTime = "2019-03-08T17:13:46Z";
        String tempTime = syncTime.replaceAll("T"," ").replaceAll("Z","");
        DateTime tempDate = DateTime.parse(tempTime, datetime_wd_ticket).plusSeconds(-3);
        tempTime = tempDate.toString(datetime_wd_ticket);
        syncTime = tempTime.substring(0, 10) + "T" + tempTime.substring(11, 19) + "Z";
        System.out.println(syncTime);

        DateTimeFormatter datetime_wd_ticket1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String syncTime1 = "2019-03-08T17:13:46Z";
        DateTime tempDate1 = DateTime.parse(syncTime1, datetime_wd_ticket1).minusSeconds(3);
        syncTime1 = tempDate1.toString(datetime_wd_ticket1);
        System.out.println(syncTime1);

        int alone = 13 % 2;
        int alone1 = 14 % 2;
        System.out.println(alone + " " + alone1);

        long id = Snowflake.nextId();
        System.out.println("不重复id：" + id);

        int maxValue = Integer.MAX_VALUE;
        System.out.println(maxValue);

        String location = "126.415756, 90.000000";
        location = Utils.reverveLocation(location);
        System.out.println(location);

        double lat = 22.5227035253;
        double lon = 113.9452031238;
        double[] latLon = LatLonUtil.getAround(lat, lon, 2000);
        for (int i = 0; i < latLon.length; i++) {
            System.out.println(latLon[i]);
        }*/

        /*int total = forReturnTest();
        System.out.println(total);*/

        // String split截取
        /*String pushData = "12431241";
        List<String> listTo = Arrays.asList(pushData.split(","));
        System.out.println(listTo);*/

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
        java.time.format.DateTimeFormatter df = java.time.format.DateTimeFormatter.ofPattern(pattern);
        // localDateTime 当前时间=2019-11-20T18:55:17.983
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime 当前时间=" + localDateTime);
        String formatTime = localDateTime.format(df);
        System.out.println("formatTime localDateTime 当前时间=" + formatTime);
        String time = "2018-06-12 16:04:43";
        LocalDateTime localDateTime1 = LocalDateTime.parse(time,java.time.format.DateTimeFormatter.ofPattern(pattern));
        System.out.println("localDateTime parse 当前时间=" + localDateTime1);

        // localTime 当前时间=18:55:17.983
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime 当前时间=" + localTime);

        // 测试String转Integer，是小数时报错
        /*List<String> price_s = new ArrayList<>();
        price_s.add("9.9");
        price_s.add("19.9");
        price_s.add("0");

        List<Double> price = new ArrayList<>();
        initPrice(price, price_s);
        System.out.println(price);
        System.out.println(0.0 == 0);*/
    }

    private static void initPrice(List<Double> price, List<String> price_s) {
        if (price_s != null && price_s.size() > 0) {
            for (int i = 0; i < price_s.size(); i++) {
                try {
//                    price.add(Integer.parseInt(price_s.get(i)));
                    price.add(Double.valueOf(price_s.get(i)));
                } catch (Exception e) {
                    System.out.println("报错啦");
                }

            }
        }
    }

    public static int forReturnTest() {
        int total = 0;
        int length = 10;
        for (int i = 0; i < length; i++) {
            total = total + i;
            if (i == 5) {
                return total;
            }
        }
        System.out.println(111);
        return total;
    }

    public static void varInitTest() {
        Random random = new Random();
        // 随机数[0,10)
        int index = random.nextInt(10);

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        if (5 > index) {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
        }

        if (b > a) {
            System.out.println(111);
        }

        if (d > c) {
            System.out.println(222);
        }
    }
}
