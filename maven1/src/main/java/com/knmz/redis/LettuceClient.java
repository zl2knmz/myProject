package com.knmz.redis;

import com.knmz.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * LettuceClient
 *
 * @author zl
 * @date 2019/10/10 11:52
 */
public class LettuceClient {

//    public final static Logger LOGGER = LoggerFactory.getLogger(LettuceClient.class);
    private final static String OK = "OK";

    private LettuceConnection lettuceConnection;

    public LettuceClient() {
        this.lettuceConnection = new LettuceConnection();
        lettuceConnection.init();
    }

    public <T> T get(String key, Class<T> type, T defaultValue) {
        T ret = defaultValue;
        if (StringUtils.isNotBlank(key)) {
            try {
                String val = lettuceConnection.getRedisCommandsSync().get(key);
                if (val != null) {
                    ret = JsonUtils.getObjectReaderFor(type).readValue(val);
                }
            } catch (Exception e) {
//                LOGGER.error("lettuce.get take error.", e);
                e.printStackTrace();
            }

        }
        return ret;
    }

    public boolean set(String key, Object val) {
        boolean ret = false;
        if (StringUtils.isNotBlank(key)) {
            try {
                String result = lettuceConnection.getRedisCommandsSync().set(key, JsonUtils.writeValueAsString(val));
                if (OK.equals(result)) {
                    ret = true;
                }
            } catch (Exception e) {
//                LOGGER.error("lettuce.set take error.", e);
                e.printStackTrace();
            }
        }
        return ret;
    }


    public boolean setex(String key, Object val, int seconds) {
        boolean ret = false;
        if (StringUtils.isNotBlank(key)) {
            try {
                String result = lettuceConnection.getRedisCommandsSync().setex(key, seconds, JsonUtils.writeValueAsString(val));
                if (OK.equals(result)) {
                    ret = true;
                }
            } catch (Exception e) {
//                LOGGER.error("lettuce.setex take error.", e);
                e.printStackTrace();
            }
        }
        return ret;
    }


    public boolean del(String key) {
        boolean ret = false;
        if (StringUtils.isNotBlank(key)) {
            try {
                Long result = lettuceConnection.getRedisCommandsSync().del(key);
                if (result != null && result.longValue() > 0) {
                    ret = true;
                }
            } catch (Exception e) {
//                LOGGER.error("lettuce.del take error.", e);
                e.printStackTrace();
            }
        }
        return ret;
    }

    
    public boolean hdel(String key, String... fields) {
        boolean ret = false;
        if (StringUtils.isNotBlank(key) && fields != null && fields.length > 0) {
            try {
                Long result = lettuceConnection.getRedisCommandsSync().hdel(key, fields);
                if (result != null && result.longValue() > 0) {
                    ret = true;
                }
            } catch (Exception e) {
//                LOGGER.error("lettuce.hdel take error.", e);
                e.printStackTrace();
            }
        }
        return ret;
    }
}
