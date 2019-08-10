package com.cocoawork.controller;

import com.cocoawork.pojo.UserDO;
import com.cocoawork.response.*;
import com.cocoawork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/get")
    public ResponseDataObject getUser(@RequestParam("id") String id) throws Exception {
        UserDO userDO = userService.getUserById(Long.parseLong(id));
        if (userDO == null) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, "id不存在");
        }
        return new ResponseDataObject(userDO);
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    @ResponseBody
    public Response getCode(String phone) {
        if (phone == null || phone == "") {
            return new BusinessException(ExceptionMsg.PARAMETER_ERROR, null);
        }

        Random random = new Random();
        int code = random.nextInt(99999);
        code += 10000;
        String codeString = String.valueOf(code);
        Map map = new HashMap();
        map.put("code", codeString);
        ResponseDataObject responseData = new ResponseDataObject(map);
        request.getSession().setAttribute(phone, String.valueOf(code));
        return responseData;
    }

    @ResponseBody
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public Response regist(UserDO userDO, String code) throws BusinessException {
        String c = (String) request.getSession().getAttribute(userDO.getTelphone());
        if (c == null || !c.equals(code)) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        try {
            userService.addUser(userDO);
        }catch (BusinessException e) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        return new ResponseObject();
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(String telphone, String password) {
        if (telphone == null || telphone.length() == 0) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        UserDO userDO = userService.login(telphone, password);
        if (userDO == null) {
            return new ResponseObject(ExceptionMsg.LOGIN_FAIL);
        }else {
            return new ResponseDataObject(userDO);
        }
    }

}
