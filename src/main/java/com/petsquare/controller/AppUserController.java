package com.petsquare.controller;

import com.petsquare.dao.AppUserDao;
import com.petsquare.response.BusinessException;
import com.petsquare.response.ExceptionMsg;
import com.petsquare.response.ResponseDataObject;
import com.petsquare.response.ResponseObject;
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

        AppUserDao appUserDaoExist = appUserService.getAppUserByPhone(phone);
        if (appUserDaoExist != null) {
            return new ResponseObject(ExceptionMsg.REGIST_FAIL_USER_EXIST);
        }

        AppUserDao appUserDao = new AppUserDao();
        appUserDao.setName(name);
        appUserDao.setPhone(phone);
        appUserDao.setPassword(CommonUtil.StringMD5(password));

        Boolean result = appUserService.addAppUser(appUserDao);
        if (result) {
            return new ResponseObject();
        } else {
            return new ResponseObject(ExceptionMsg.SYSTEM_ERROR);
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
        AppUserDao appUserDao = appUserService.getAppUserByPhone(phone);
        if (appUserDao == null) {
            return new ResponseObject(ExceptionMsg.LOGIN_FAIL);
        }

        String pwd = appUserDao.getPassword();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //比对密码
        if (password.equals(CommonUtil.StringMD5(pwd+dateFormat.format(date)))) {
            return new ResponseDataObject(appUserDao);
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
        List<AppUserDao> fans = appUserService.getAppUserAllFansByUserId(userId, pageIndex, pageSize);
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
        List<AppUserDao> follows = appUserService.getAppUserAllFollowByUserId(userId, pageIndex, pageSize);
        return new ResponseDataObject(follows);
    }


}
