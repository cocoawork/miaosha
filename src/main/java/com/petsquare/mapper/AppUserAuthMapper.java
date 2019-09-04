package com.petsquare.mapper;

import com.petsquare.dao.AppUserAuthDao;

public interface AppUserAuthMapper {

    public Integer addAppUserAuth(AppUserAuthDao appUserAuthDao);

    public AppUserAuthDao getAppUserAuthByUserId(String user_id);

    public Integer updateAppUserAuth(AppUserAuthDao appUserAuthDao);

}
