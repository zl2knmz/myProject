package com.cloud;

import com.cloud.util.LatLonUtil;
import com.cloud.util.Snowflake;
import com.cloud.util.Utils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author zl
 * @date 2019/11/21 14:15
 * 随意测试类
 */
public class HelloWorld {
    public static void main(String[] args) {
        // joda的DateTime
        int hour = DateTime.now().getHourOfDay();
        int minute = DateTime.now().getMinuteOfHour();
        System.out.println("当前时间的 hour:" + hour + ",minute:" + minute);

        // 武大同步时间测试
        DateTimeFormatter datetime_wd_ticket = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String syncTime = "2019-03-08T17:13:46Z";
        String tempTime = syncTime.replaceAll("T", " ").replaceAll("Z", "");
        DateTime tempDate = DateTime.parse(tempTime, datetime_wd_ticket).plusSeconds(-3);
        tempTime = tempDate.toString(datetime_wd_ticket);
        syncTime = tempTime.substring(0, 10) + "T" + tempTime.substring(11, 19) + "Z";
        System.out.println(syncTime);

        DateTimeFormatter datetime_wd_ticket1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String syncTime1 = "2019-03-08T17:13:46Z";
        DateTime tempDate1 = DateTime.parse(syncTime1, datetime_wd_ticket1).minusSeconds(3);
        syncTime1 = tempDate1.toString(datetime_wd_ticket1);
        System.out.println(syncTime1);

        // int求余
        int alone = 13 % 2;
        int alone1 = 14 % 2;
        int alone2 = 14 % 3;
        System.out.println("alone=" + alone + ",alone1=" + alone1 + ",alone2=" + alone2);

        // 雪花id
        long id = Snowflake.nextId();
        System.out.println("雪花不重复id：" + id);

        // int 最大值
        int maxValue = Integer.MAX_VALUE;
        System.out.println("Integer max value=" + maxValue);

        // 经纬度超过最大值过滤180,90
        String location = "126.415756, 90.000000";
        location = Utils.reverveLocation(location);
        System.out.println("处理过的经纬度：" + location);

        // 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度(minLat, minLng, maxLat, maxLng)
        double lat = 22.5227035253;
        double lon = 113.9452031238;
        double[] latLon = LatLonUtil.getAround(lat, lon, 2000);
        // 数组遍历也可以使用foreach
        for (double v : latLon) {
            System.out.println(v);
        }

        // for循环中的return测试
        int total = forReturnTest();
        System.out.println("forReturn: " + total);

        // String split截取后，数组转list。
        String pushData = "12431241";
        List<String> listTo = Arrays.asList(pushData.split(","));
        System.out.println("string to list: " + listTo);

        // 测试String转Integer，String包含小数时Integer报错需要转成Double。
        List<String> priceStr = new ArrayList<>();
        priceStr.add("9.9");
        priceStr.add("19.9");
        priceStr.add("0");

        List<Double> priceDouble = new ArrayList<>();
        initPrice(priceDouble, priceStr);
        System.out.println(priceDouble);
        System.out.println(0.0 == 0);

        // 测试随机数的随机是否均衡
        varInitTest();
    }

    /**
     * 测试String转Integer，是小数时报错。
     */
    private static void initPrice(List<Double> priceDouble, List<String> priceStr) {
        if (priceStr != null && priceStr.size() > 0) {
            for (String price : priceStr) {
                try {
//                    priceDouble.add(Integer.parseInt(price));
                    priceDouble.add(Double.valueOf(price));
                } catch (Exception e) {
                    System.out.println("报错啦");
                }

            }
        }
    }

    /**
     * for循环中的return测试
     */
    private static int forReturnTest() {
        int total = 0;
        int length = 10;
        for (int i = 0; i < length; i++) {
            total = total + i;
            if (i == 5) {
                return total;
            }
        }
        System.out.println("这里是不会被执行的");
        return total;
    }

    /**
     * 测试随机数的随机是否均衡
     */
    private static void varInitTest() {
        Random random = new Random();
        // 随机数范围[0,10)
        int index = random.nextInt(10);

        int a = 0;
        int b = 0;

        if (5 > index) {
            a = 1;
            b = 2;
        }

        if (b > a) {
            System.out.println("index > 5");
        } else {
            System.out.println("index <= 5");
        }
    }
}
