package com.cloud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * 生成唯一id
 *
 * @author zl
 * @date 2021/06/15 16:52
 */
public class IdGenerator {
    private static SimpleDateFormat nice = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 指定的start时间
     */
    private static Date start = new Date();
    private static Calendar calendar = Calendar.getInstance();
    private static Random random = new Random(Long.valueOf(String.valueOf(calendar.MINUTE) + String.valueOf(calendar.SECOND) + String.valueOf(calendar.MILLISECOND)));
    public static String serverPrifix = "";
    private static double[] units = new double[]{1d, 10000d, 8640000d, 10000000d, 86400000d, 600000000d, 36000000000d, 86400000000d, 864000000000d};
    private static int[] unitslength = new int[]{16, 12, 9, 9, 8, 7, 6, 5, 4};

    private IdGenerator() {

    }

    static {
        try {
            start = nice.parse("2010-01-01");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String Generate(int randomLength, int baseCode, long time_stamp) {
        return Generate(randomLength, baseCode, 0, time_stamp);
    }

    public static String Generate(int randomLength, int baseCode, int snLength, long time_stamp) {
        StringBuilder sb = new StringBuilder(serverPrifix);
        sb.append(RandomNumber(randomLength));
        int index = (int) baseCode;
        double ticks = Double.valueOf((time_stamp - start.getTime()) * 10000);
        sb.append(getFull(String.valueOf((new Double(ticks / units[index])).longValue()), unitslength[index], "0"));
        sb.append(SerialNumber(snLength));
        return sb.toString();
    }

    public static String Generate(int randomLength, int baseCode, Date timeBase) {
        return Generate(randomLength, baseCode, 0, timeBase);
    }

    public static String Generate(int randomLength, int baseCode, int snLength, Date timeBase) {
        StringBuilder sb = new StringBuilder(serverPrifix);
        sb.append(RandomNumber(randomLength));
        int index = (int) baseCode;
        double ticks = Double.valueOf(timeBase.getTime() - start.getTime());
        sb.append(getFull(String.valueOf(new Double(ticks / units[index]).longValue()), unitslength[index], "0"));
        sb.append(SerialNumber(snLength));
        return sb.toString();
    }

    public static Date ParseDateTime(String code, int baseCode) {
        return ParseDateTime(code, baseCode, 0);
    }

    public static Date ParseDateTime(String code, int baseCode, int snLength) {
        int index = (int) baseCode;
        double timebase = Double.valueOf(code.substring(code.length() - snLength - unitslength[index], unitslength[index]));
        long span = new Double(timebase * units[index]).longValue();
        return getAddedDate(start, span / 10000);
    }

    private static Date getAddedDate(Date date, long miliseconds) {
        return new Date(date.getTime() + miliseconds);
    }

    private static String RandomNumber(int length) {
        if (length <= 0) return "";
        int index = length > 9 ? 8 : length - 1;
        int max = powers[index];
        int min = powers[index] / 10;
//        return random.Next(powers[index] / 10, powers[index]).ToString().PadLeft(lengths[index], '0');
        return getFull(String.valueOf(random.nextInt(max) % (max - min + 1) + min), lengths[index], "0");
    }

    private static int[] indexs = new int[]{random.nextInt(10), random.nextInt(100), random.nextInt(1000), random.nextInt(10000), random.nextInt(100000), random.nextInt(1000000), random.nextInt(10000000), random.nextInt(100000000), random.nextInt(1000000000)};
    private static int[] lengths = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static int[] powers = new int[]{10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    private static Object _syncObj = new Object();

    private static String SerialNumber(int length) {
        if (length <= 0) return "";
        int index = length > 9 ? 8 : length - 1;
        String tmp = getFull(String.valueOf(indexs[index]), lengths[index], "0");
        indexs[index] = (indexs[index] + 1) % powers[index];
        return tmp;
    }

    public static String getFull(String str, int count, String tmp) {
        if (str.length() < count) {
            int len = str.length();

            for (int i = 0; i < count - len; i++) {
                str = tmp + str;
            }
        }
        return str;
    }

    public static void main(String[] args) {
//        String result = Generate(0, Base.SECONDS, System.currentTimeMillis()) + "9" + getFull(String.valueOf(new Random().nextInt(9999999)), 7, "0");
//        System.out.println(result);

        String id8 = IdGenerator.Generate(0, Base.S8P64, System.currentTimeMillis());
        System.out.println("8位id=" + id8);

        String id9 = Generate(0, Base.SECONDS, System.currentTimeMillis());
        System.out.println("9位id=" + id9);

        System.out.println("8位随机数=" + new Random().nextInt(100000000));

//    	test();
    }

    public static void test() {
        long TICKS_AT_EPOCH = 621355968000000000L;
        long TICKS_PER_MILLISECOND = 10000;
        long ticks = 634200192000000000L;

        Date date = new Date((ticks - TICKS_AT_EPOCH) / TICKS_PER_MILLISECOND);
        System.out.println(date);

        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(utc);
        calendar.setTime(date);
        System.out.println(calendar.getTime().getTime());
    }

    public static Long getKey(Long replyId){
        Date now = new Date();
        String commentId = IdGenerator.Generate(0, Base.SECONDS, now.getTime()) + "9" + String.format("%07d", new Random().nextInt(9999999));
        if ( replyId != null && replyId.toString().length() >= 16) {
            Date date = IdGenerator.ParseDateTime(replyId.toString(), Base.SECONDS, 8);
            long seclong = now.getTime() - date.getTime();
            if (seclong <= 0){
                seclong = 0;
            }else if (seclong > 90000000){
                seclong = (new Random().nextInt(9999999) + 80000000);
            }
            seclong = 90000000 - seclong;
            commentId = replyId.toString().substring(0, commentId.length() - 8) + String.format("%08d", seclong);
        }
        return Long.valueOf(commentId);
    }
}
