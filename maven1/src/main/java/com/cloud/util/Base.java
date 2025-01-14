package com.cloud.util;

/**
 * Created by david on 2017/9/7.
 */
public class Base {
    public static final int TICKS = 0;         //16位可持續30年，超過30年自動變為17位
    public static final int MILLISECONDS = 1;   //12位可持續30年，超過30年自動變為13位
    public static final int MS864 = 2;          //9位可持續30年，超過30年自動變為10位
    public static final int SECONDS = 3;        //9位可持續30年，超過30年自動變為10位
    public static final int S8P64 = 4;          //8位可持續30年，超過30年自動變為9位
    public static final int MINUTES = 5;        //7位可持續30年，超過30年自動變為8位
    public static final int HOURS = 6;          //6位可持續100年，超過100年自動變為7位
    public static final int H2P4 = 7;           //5位可持續30年，超過30年自動變為6位
    public static final int DAYS = 8;            //4位可持續30年，超過30年自動變為5位
}
