package com.petsquare.mapper;

import com.petsquare.dao.VerifyCodeDao;

public interface VerifyCodeMapper {

    public Integer addVerifyCode(VerifyCodeDao verifyCodeDao);

    public VerifyCodeDao getLastestAvaliableVerifyCode(String phone);

    public Integer updateVerifyCode(VerifyCodeDao verifyCodeDao);

}
