package com.petsquare.controller;


import com.petsquare.response.BusinessException;
import com.petsquare.response.ExceptionMsg;
import com.petsquare.response.Response;
import com.petsquare.response.ResponseObject;
import com.petsquare.service.AppVerifyCodeService;
import com.petsquare.util.CommonUtil;
import com.petsquare.util.validate.ValiadationIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    public ValiadationIpml valiadationIpml;

    @Autowired
    private AppVerifyCodeService appVerifyCodeService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject exceptionHandler(Exception exception) {
        Response responseObject = new ResponseObject();
        if(exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException)exception;
            responseObject.setCode(businessException.getCode());
            responseObject.setMsg(businessException.getMsg());
        }else {
            responseObject.setCode(ExceptionMsg.SYSTEM_ERROR.getCode());
            responseObject.setMsg(ExceptionMsg.SYSTEM_ERROR.getMsg());
        }
        return (ResponseObject) responseObject;
    }


    /*
    * 获取验证码
    * phone: 手机号
    * type: 验证码类型(登录, 修改密码...)
    * */
    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public ResponseObject getVerifyCode(String phone, Integer type) {
        if (!CommonUtil.StringIsPhoneNumber(phone)) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        Boolean result = appVerifyCodeService.sendAppVerifyCode(phone);
        if (result) return new ResponseObject();
        return new ResponseObject(ExceptionMsg.FAILURE);
    }

}
