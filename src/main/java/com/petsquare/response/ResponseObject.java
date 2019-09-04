package com.petsquare.response;

public class ResponseObject implements Response{

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseObject(ExceptionMsg exceptionMsg) {
        this.code = exceptionMsg.getCode();
        this.msg = exceptionMsg.getMsg();
    }

    public ResponseObject() {
        this.code = ExceptionMsg.SUCCESS.getCode();
        this.msg = ExceptionMsg.SUCCESS.getMsg();
    }

    public ResponseObject(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
