package com.knmz;

import com.knmz.util.LatLonUtil;
import com.knmz.util.Snowflake;
import com.knmz.util.Utils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("helloWorld !");
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
        }

    }

}