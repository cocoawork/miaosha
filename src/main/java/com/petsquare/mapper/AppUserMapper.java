package com.petsquare.mapper;

import com.petsquare.dao.user.AppUserDao;

public interface AppUserMapper {

    public Integer addAppUser(AppUserDao user);

    public AppUserDao getAppUserInfoByPhone(String phone);

    public AppUserDao getAppUserInfoByUserId(String userId);

}
