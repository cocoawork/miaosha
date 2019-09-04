package com.petsquare.response;

public class ResponseDataObject extends ResponseObject {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public ResponseDataObject(ExceptionMsg responseStatus, Object data) {
        super(responseStatus);
        this.data = data;
    }


    public ResponseDataObject(Object data) {
        super(ExceptionMsg.SUCCESS);
        this.data = data;
    }

    public ResponseDataObject(String code, String msg, Object data) {
        super(code, msg);
        this.data = data;
    }
}
