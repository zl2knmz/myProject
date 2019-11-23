package com.knmz.exception;

/**
 *  业务异常的封装
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    private String errorMessage;
    
    
    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
        this.errorMessage = msg;
    }

    public ServiceException(AbstractServiceException exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    public ServiceException(String message) {
        this.errorMessage = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
