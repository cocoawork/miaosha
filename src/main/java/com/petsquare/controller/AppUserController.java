package com.petsquare.controller;

import com.petsquare.dto.AppUserDto;
import com.petsquare.response.*;
import com.petsquare.service.AppUserService;
import com.petsquare.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RequestMapping("/appUser")
@Controller
public class AppUserController extends BaseController {

    @Autowired
    private AppUserService appUserService;

    /*
    * 用户注册
    * password: 用户密码md5
    * */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ResponseObject registUser(String name, String phone, String password) throws BusinessException {

        if (name == null || phone == null || password == null || !CommonUtil.StringIsPhoneNumber(phone)) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }

        AppUserDto AppUserDto = appUserService.getAppUserInfoByPhone(phone);
        if (AppUserDto != null) {
            return new ResponseObject(ExceptionMsg.REGIST_FAIL_USER_EXIST);
        }

        AppUserDto appUserDto1 = new AppUserDto();
        appUserDto1.setName(name);
        appUserDto1.setPhone(phone);
        appUserDto1.setPassword(CommonUtil.StringMD5(password));

        Boolean result = appUserService.addAppUser(appUserDto1);
        if (result) {
            return new ResponseObject(ExceptionMsg.SUCCESS);
        } else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }

    }


    /*
    * 用户手机号登录
    * phone:用户手机号
    * password: 密码md5+今日(20190902)后 再次md5
    * */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseObject login(String phone, String password) {
        if (phone == null || password == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        AppUserDto appUserDto = appUserService.getAppUserInfoByPhone(phone);
        if (appUserDto == null) {
            return new ResponseObject(ExceptionMsg.LOGIN_FAIL);
        }

        String pwd = appUserDto.getPassword();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //比对密码
        if (password.equals(CommonUtil.StringMD5(pwd+dateFormat.format(date)))) {
            return new ResponseDataObject(appUserDto);
        }
        return new ResponseObject(ExceptionMsg.LOGIN_FAIL);
    }


    /*
    * 获取用户所有粉丝
    * */
    @RequestMapping(value = "/getUserFans", method = RequestMethod.POST)
    public ResponseObject getUserFans(String userId, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                     @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize){
        if (userId == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        List<AppUserDto> fans = appUserService.getAppUserAllFansByUserId(userId, pageIndex, pageSize);
        return new ResponseDataObject(fans);
    }

    /*
    * 获取用户所有关注用户
    * */
    @RequestMapping(value = "/getUserFollows", method = RequestMethod.POST)
    public ResponseObject getUserFollows(String userId, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                        @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize){
        if (userId == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        List<AppUserDto> follows = appUserService.getAppUserAllFollowByUserId(userId, pageIndex, pageSize);
        return new ResponseDataObject(follows);
    }


    /*
    * 添加用户实名认证信息
    * */
    @RequestMapping(value = "/addAppUserAuth", method = RequestMethod.POST)
    public Response addAppUserAuth(AppUserDto.AppUserAuthDto appUserAuthDto) {
        return null;
    }


}
