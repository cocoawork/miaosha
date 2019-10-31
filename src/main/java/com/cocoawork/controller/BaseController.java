package com.cocoawork.controller;

import com.cocoawork.response.BusinessException;
import com.cocoawork.response.ExceptionMsg;
import com.cocoawork.response.Response;
import com.cocoawork.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response handlerException(HttpServletRequest request, Exception exception) {
        Response responseObject = new ResponseObject();
        if(exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException)exception;
            responseObject.setCode(businessException.getCode());
            responseObject.setMsg(businessException.getMsg());
        }else {
            responseObject.setCode(ExceptionMsg.SYSTEM_ERROR.getCode());
            responseObject.setMsg(ExceptionMsg.SYSTEM_ERROR.getMsg());
        }
        return responseObject;
    }


}
