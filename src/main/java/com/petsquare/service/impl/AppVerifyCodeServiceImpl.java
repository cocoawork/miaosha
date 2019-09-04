package com.petsquare.service.impl;

import com.petsquare.service.AppVerifyCodeService;
import org.springframework.stereotype.Service;

@Service
public class AppVerifyCodeServiceImpl implements AppVerifyCodeService {

    @Override
    public Boolean sendAppVerifyCode(String phone) {
        System.out.println("获取验证码, 有待实现");
        return true;
    }
}
