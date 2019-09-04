package com.petsquare.mapper;

import com.petsquare.dao.AppUserDao;

public interface AppUserMapper {

    public Integer addAppUser(AppUserDao user);

    public AppUserDao getAppUserByPhone(String phone);

    public AppUserDao getAppUserAndAuthInfoByPhone(String phone);

}
