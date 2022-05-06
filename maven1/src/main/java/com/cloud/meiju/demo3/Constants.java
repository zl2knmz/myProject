package com.cloud.meiju.demo3;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zl
 * @date 2022/5/6 11:22
 */
public interface Constants {
    /**
     * reids缓存key前缀
     */
    String PRE = "HDX_LIVE_";

    String REDIS_LOCK_PREFIX = "HDX:LOCK:";

    /**
     * 活动会议列表缓存 key前缀
     */
    String ROOM_PAGE_CACHE = PRE + "ROOM_PAGE_CACHE";


    /**
     * REDIS 缓存KEY
     */
    class RedisCacheKey {
        /**
         * 缓存用户Key
         */
        public static final String CACHE_KEY_USER = PRE + "CACHE_KEY_USER";

        /**
         * 缓存城市Key
         */
        public static final String CACHE_KEY_CITY = PRE + "hdx.cache.hash.city";

        /**
         * 缓存职位Key
         */
        public static final String CACHE_KEY_JOB = PRE + "hdx.cache.hash.job";

        /**
         * 缓存公司Key
         */
        public static final String CACHE_KEY_COMPANY = PRE + "hdx.cache.hash.company";
    }

    @Getter
    @AllArgsConstructor
    enum LiveWatermark_Enum implements IEnum {

        CLEAR_WATER_MARK("0", 657268L, "透明水印"),
        TRC_RIGHT_UP("1", 364810L, "trc-默认"),
        OBS_RIGHT_UP("2", 166711L, "obs-默认"),
        ;

        private final String code;
        private final Long value;
        private final String desc;
    }

    @Getter
    enum LiveWatermarkRule_Enum implements IEnum {

        LEFT_UP("1", 1L, 5L, "左上角"),
        RIGHT_UP("2", 89L, 5L, "右上角"),
        LEFT_DOWN("3", 1L, 90L, "左下角"),
        RIGHT_DOWN("4", 89L, 90L, "右下角"),
        ;
        private final String code;
        private final Long x;
        private final Long y;
        private final Long width = 10L;
        private final Long height = 100L;
        private final String desc;

        LiveWatermarkRule_Enum(String code, Long x, Long y, String desc) {
            this.code = code;
            this.x = x;
            this.y = y;
            this.desc = desc;
        }
    }
}
