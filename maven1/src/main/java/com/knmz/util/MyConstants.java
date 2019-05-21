package com.knmz.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;
/**
 * @Author: zl
 * @Date: 2019/5/21 14:15
 * 常量类
 */
public class MyConstants {

    public static DateTimeFormatter SyncTimeFormater = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static DateTimeFormatter datetime_formtat2 = DateTimeFormat.forPattern("yyyyMMdd");
    public static String HDX_SCAN_IMAGE_DAY_MAX_KEY="FACEPASS_SCAN_IMAGE_DAY_MAX_KEY";
    public static int HDX_SCAN_IMAGE_DAY_MAX_NUM=20000;
    /**打印服务eid子模板标识字符*/
    public static final String EID_CHAR = "a";

    /**APP推送模式*/
    public static final List<String> MODES = Arrays.asList("registration_id", "alias", "tag", "tag_and", "tag_not", "segment", "abtest");

    /**APP推送android平台*/
    public final static String PLATFORM_ANDROID = "android";

    /**APP推送ios平台*/
    public final static String PLATFORM_IOS = "ios";
}
