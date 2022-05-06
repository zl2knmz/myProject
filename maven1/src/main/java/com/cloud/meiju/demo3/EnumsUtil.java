package com.cloud.meiju.demo3;

import cn.hutool.core.util.StrUtil;

/**
 * @author fanghuan
 * @date 2021-05-20 10:03
 */
public class EnumsUtil {

    /**
     * 获取枚举集合
     */
    public static <T extends IEnum> T[] values(Class<T> clazz) {
        if (!clazz.isEnum()) {
            throw new IllegalArgumentException("Class[" + clazz + "]不是枚举类型");
        }
        return clazz.getEnumConstants();
    }

    /**
     * 获取枚举
     *
     * @param clazz
     * @param code
     * @return
     */
    public static <T extends IEnum> T valueOfIgnoreCase(Class<T> clazz, String code) {

        return valueOf(clazz, code, true);
    }

    /**
     * 获取枚举,区分大小写
     *
     * @param clazz
     * @param code
     * @return
     */
    public static <T extends IEnum> T valueOf(Class<T> clazz, String code) {

        return valueOf(clazz, code, false);
    }

    /**
     * 获取枚举区分大小写
     */
    private static <T extends IEnum> T valueOf(Class<T> clazz, String code, boolean ignoreCase) {
        T[] enums = values(clazz);

        if (enums == null || enums.length == 0) {
            return null;
        }

        for (T t : enums) {
            if (ignoreCase && StrUtil.toString(t.getCode()).equalsIgnoreCase(code)) {
                return t;
            } else if (StrUtil.toString(t.getCode()).equals(code)) {
                return t;
            }
        }
        return null;
    }
}
