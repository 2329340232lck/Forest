package com.demo.forest.util;

import com.demo.forest.config.exception.custom.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseInfo {
    private Integer code;
    private Object resultData;
    private String resultMessage;

    public ResponseInfo() {
        this.code = 200;
        this.resultData = null;
        this.resultMessage = "Success!";
    }

    public ResponseInfo(Integer code, Object resultData, String resultMessage) {
        this.code = code;
        this.resultData = resultData;
        this.resultMessage = resultMessage;
    }

    public static ResponseInfo SUCCESS() {
        return new ResponseInfo(200, null, "Success!");
    }

    public static ResponseInfo SUCCESS(Object resultData) {
        return new ResponseInfo(200, resultData, "Success!");
    }

    public static ResponseInfo SUCCESS(String resultMessage) {
        return new ResponseInfo(200, null, resultMessage);
    }

    public static ResponseInfo SUCCESS(Object resultData, String resultMessage) {
        return new ResponseInfo(200, resultData, resultMessage);
    }

    public static ResponseInfo ERROR() {
        return new ResponseInfo(500, null, "Error!");
    }

    public static ResponseInfo ERROR(Object resultData) {
        return new ResponseInfo(500, resultData, "Error!");
    }

    public static ResponseInfo ERROR(ExceptionEnum exceptionEnum) {
        return new ResponseInfo(exceptionEnum.getCode(), null, exceptionEnum.getErrorMessage());
    }

    public static ResponseInfo ERROR(Integer code, Object resultData, String resultMessage) {
        return new ResponseInfo(code, resultData, resultMessage);
    }

    public static ResponseInfo ERROR(Integer code, String resultMessage) {
        return new ResponseInfo(code, null, resultMessage);
    }

    public static ResponseInfo ERROR(String resultMessage) {
        return new ResponseInfo(500, null, resultMessage);
    }
}
