package com.knmz.exception;

/**
 *  异常规范
 */
public interface AbstractServiceException {

    /**
     * 获取异常的状态码
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     */
    String getMessage();
}
