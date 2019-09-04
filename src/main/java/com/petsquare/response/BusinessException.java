package com.petsquare.response;

public class BusinessException extends Exception implements Response {

    private ExceptionMsg exceptionMsg;

    @Override
    public String getMsg() {
        return this.exceptionMsg.getMsg();
    }

    @Override
    public String getCode() {
        return this.exceptionMsg.getCode();
    }

    @Override
    public void setCode(String code) {
        this.exceptionMsg.setCode(code);
    }

    @Override
    public void setMsg(String msg) {
        this.exceptionMsg.setMsg(msg);
    }

    public BusinessException(ExceptionMsg exceptionMsg, String errorMsg) {
        this.exceptionMsg = exceptionMsg;
        if (errorMsg != null && errorMsg.length() != 0) this.exceptionMsg.setMsg(errorMsg);
    }


}
