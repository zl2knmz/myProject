package com.knmz.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private final static ObjectMapper OBJECT_MAPPER;
    private final static ObjectWriter OBJECT_WRITER;
    private final static ObjectReader OBJECT_READER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //为了避免 OBJECT_MAPPER 创建后修改配置项导致线程不安全问题，使用 OBJECT_WRITER 和 OBJECT_READER 保证线程安全
        OBJECT_WRITER = OBJECT_MAPPER.writer();
        OBJECT_READER = OBJECT_MAPPER.reader();
    }


    public static ObjectWriter getObjectWriter() {
        return OBJECT_WRITER;
    }

    public static String writeValueAsString(Object obj) {
        try {
            return OBJECT_WRITER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("writeValueAsString take error.", e);
            return null;
        }
    }

    public static ObjectReader getObjectReader() {
        return OBJECT_READER;
    }

    public static ObjectReader getObjectReaderFor(Class<?> type) {
        return OBJECT_READER.forType(type);
    }

    public static <T> T readValue(final String json, final TypeReference<T> typeReference) {
        T data = null;
        if (StringUtils.isNotBlank(json)) {
            try {
                data = OBJECT_MAPPER.readValue(json, typeReference);
            } catch (Exception e) {
                LOGGER.error("readValue take error, while type={}, json={}", typeReference.getType().toString(), json, e);
            }
        }
        return data;
    }

}
