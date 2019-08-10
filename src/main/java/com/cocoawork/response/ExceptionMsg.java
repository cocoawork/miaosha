package com.cocoawork.response;

public enum ExceptionMsg {
    SUCCESS("200", "请求成功"),
    FAILURE("201", "请求失败"),
    PARAMETER_ERROR("250", "参数错误"),
    SYSTEM_ERROR("260", "系统内部错误"),
    LOGIN_FAIL("280", "登录失败"),
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
