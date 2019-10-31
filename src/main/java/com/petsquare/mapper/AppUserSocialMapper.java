package com.petsquare.mapper;

import com.petsquare.dao.user.AppUserSocialDao;

public interface AppUserSocialMapper {

    public Integer addAppUserSocial(AppUserSocialDao appUserSocialDao);

    public AppUserSocialDao getAppUserSocialByUserId(String userId);

    public Integer updateAppUserSocial(AppUserSocialDao appUserSocialDao);
}
