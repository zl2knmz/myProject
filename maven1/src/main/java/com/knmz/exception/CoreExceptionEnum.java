package com.knmz.exception;

/**
 *  core模块的异常枚举定义类
 */
public enum CoreExceptionEnum implements AbstractServiceException {

    /**
     * token异常
     */
    NO_CURRENT_USER(700, "当前没有登录的用户"),
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),
    PERMISSION_ERROR(800, "没有访问该资源的权限"),

    /**
     * 签名异常
     */
    SIGN_ERROR(700, "签名验证失败"),

    /**
     * 其他
     */
    WRITE_ERROR(500, "渲染界面错误"),
    ENCRYPT_ERROR(600, "加解密错误"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),

    /**
     * 错误的请求
     */
    REQUEST_NULL(400, "请求参数为空或格式错误"),
    PAGE_NULL(404, "请求页面不存在"),
    IO_ERROR(500, "流读取异常"),
    SERVICE_ERROR(500, "服务器异常"),
    REMOTE_SERVICE_NULL(404, "远程服务不存在"),
    DATA_NULL(400, "数据不存在"),
    REQUEST_METHOD_NOT_SUPPORTED(410, "错误的请求类型"),

    MERCHANT_STATUS_FORBID(800, "商户被停用,请联系相关人员处理"),
    SERVICE_BUSY(801, "服务器繁忙,请稍后访问");
	

    CoreExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
