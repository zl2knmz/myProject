package com.knmz.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.knmz.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collection;

/**
 *  业务异常工具类,语法类似junit断言语法 Created By Reese In 2019/1/14
 */
public class AssertUtil {
    private AssertUtil() {
    }

    /**
     * 断定目标值为false.为true则抛出业务异常
     *
     * @param expression
     * @param message
     * @throws  ServiceException
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new ServiceException(message);
        }
    }

    /**
     * 断定目标值为true.为false则抛出业务异常
     *
     * @param expression
     * @param message
     * @throws ServiceException
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ServiceException(message);
        }
    }

    /**
     * 断定目标值为null.不为null则抛出业务异常
     *
     * @param obj
     * @param message
     * @throws ServiceException
     */
    public static void isNull(Object obj, String message) {
        if (obj != null) {
            throw new ServiceException(message);
        }
    }

    /**
     * 断定目标值不为null.为null则抛出业务异常
     *
     * @param obj
     * @param message
     */
    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new ServiceException(message);
        }
    }

    /**
     * 断定目标list不为空.为空则抛出业务异常
     *
     * @param collection
     * @param message
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ServiceException(message);
        }
    }

    /**
     * 断定目标list为空.不为空则抛出业务异常
     *
     * @param collection
     * @param message
     */
    public static void isEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new ServiceException(message);
        }
    }

    /**
     * 断定目标字符串不为空.为空则抛出业务异常
     *
     * @param string
     * @param message
     */
    public static void notEmpty(String string, String message) {

        if (StringUtils.isBlank(string)) {
            throw new ServiceException(message);
        }
    }

    /**
     * 断定目标字符串为空.不为空则抛出业务异常
     *
     * @param string
     * @param message
     */
    public static void empty(String string, String message) {

        if (StringUtils.isNotBlank(string)) {
            throw new ServiceException(message);
        }
    }

    /**
     * 根据sql返回结果断定新增,修改,删除执行成功
     *
     * @param result
     * @param message
     */
    public static void executeSuccess(int result, String message) {

        if (result <= 0) {
            throw new ServiceException(message);
        }
    }

    /**
     * 可选非数字
     *
     * @param param
     * @param paramName
     */
    public static void choicedNotDigits(String param, String paramName) {
        if (StringUtils.isNotBlank(param)) {
            notDigits(param, paramName, "必须为数字");
        }
    }

    /**
     * 判断字符串中是否全为数字(没有小数点)
     *
     * @param param
     * @param paramName 参数名
     * @param message
     */
    public static void notDigits(String param, String paramName, String message) {
        if (!NumberUtils.isDigits(param)) {
            throw new ServiceException(paramName + message);
        }
    }

    /**
     * 判断字符串中是否全为数字(没有小数点)
     *
     * @param param
     * @param paramName 参数名
     */
    public static void notDigits(String param, String paramName) {
        notEmpty(param, paramName);
        notDigits(param, paramName, "必须为数字");
    }

    public static void notAlphabetOrumber(String param, String paramName) {
        notAlphabetOrumber(param, paramName, "参数无效");
    }

    public static void notAlphabetOrumber(String param, String paramName, String message) {
        notEmpty(param, paramName);
        if (!param.matches("[a-zA-Z0-9]+")) {
            throw new ServiceException(paramName + message);
        }
    }

    public static void notInteger(String param, String paramName) {
        notEmpty(param, paramName);
        notInteger(param, paramName, "必须为整型数据");
    }

    public static void notInteger(String param, String paramName, String message) {
        if (!NumberUtils.isNumber(param) || param.contains(".")) {
            throw new ServiceException(paramName + message);
        }
    }
}
