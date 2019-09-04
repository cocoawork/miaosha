package com.petsquare.response;

public enum ExceptionMsg {
    SUCCESS("200", "请求成功"),
    FAILURE("201", "请求失败"),


    PARAMETER_ERROR("250", "参数错误"),
    SYSTEM_ERROR("260", "系统内部错误"),

    LOGIN_SUCCESS("10010", "登录成功"),
    LOGIN_FAIL("10011", "登录失败"),

    REGIST_SUUCCESS("10020", "注册成功"),
    REGIST_FAIL_USER_EXIST("10021", "用户已存在"),


    ;
    private String code;
    private String msg;

    ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
