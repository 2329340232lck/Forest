package com.demo.forest.config.exception.custom;

/**
 * 异常枚举类
 */
public enum ExceptionEnum {
    // -------------------------USER错误枚举-----------------------------------
    USER_LOGIN_FAIL(601, "账号名或密码错误!"),
    DISABLED_ACCOUNT(602, "登录失败,该账号被禁用!"),
    USER_LOGIN_OUT_ERROR(603, "警告,当前账号不存在或已注销!"),
    TOO_MANY_ATTEMPTS(604, "警告,用户登录错误次数过多!"),
    DUPLICATE_USER_NAME(610, "错误,重复的用户"),
    // --------------------------Grade错误枚举-------------------------------------
    GRADE_REPEAT_INSERT(1001, "添加失败，小班名称冲突!");

    private int code;

    private String errorMessage;

    ExceptionEnum(int code, String errorMessage) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
