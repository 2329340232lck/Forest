package com.demo.forest.config.exception.custom;

import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class HTMLException extends RuntimeException {

    private int errorCode;

    private String errorMessage;

    public HTMLException() {
        super();
    }

    public HTMLException(ExceptionEnum exception) {
        super(exception.getErrorMessage());
        this.errorCode = exception.getCode();
        this.errorMessage = exception.getErrorMessage();
    }
}
