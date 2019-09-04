package com.petsquare.response;

public interface Response {

    public String getMsg();
    public String getCode();
    public void setCode(String code);
    public void setMsg(String msg);

}
