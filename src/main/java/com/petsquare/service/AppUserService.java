package com.petsquare.service;

import com.petsquare.dao.AppUserAuthDao;
import com.petsquare.dao.AppUserDao;
import com.petsquare.dao.AppUserRelationDao;
import com.petsquare.dao.AppUserSocialDao;
import com.petsquare.response.BusinessException;

import java.util.List;

public interface AppUserService {

    /*
    * 添加app用户
    * */
    public Boolean addAppUser(AppUserDao user) throws BusinessException;

    /*
    * 通过手机号查询app用户
    * */
    public AppUserDao getAppUserByPhone(String phone);

    /*
    * 通过手机号查询用户和用户认证信息
    * */
    public AppUserDao getAppUserAndAuthInfoByPhone(String phone);

    /*
    * 添加用户认证信息
    * */
    public Boolean addAppUserAuth(AppUserAuthDao appUserAuthDao) throws BusinessException;

    /*
    * 查询用户认证信息
    * */
    public AppUserAuthDao getAppUserAuthByUserId(String userId);

    /*
    * 更新用户认证信息
    * */
    public Boolean updateAppUserAuth(AppUserAuthDao appUserAuthDao) throws BusinessException;

    /*
    * 添加用户社交账号信息
    * */
    public Boolean addAppUserSocial(AppUserSocialDao appUserSocialDao) throws BusinessException;

    /*
    * 获取用户社交账号信息
    * */
    public AppUserSocialDao getAppUserSocialByUserId(String userId);

    /*
    * 更新用户社交账号信息
    * */
    public Boolean updateAppUserSocial(AppUserSocialDao appUserSocialDao) throws BusinessException;

    /*
    * 新增用户关注关系
    * */
    public Boolean addAppUserRelation(AppUserRelationDao appUserRelationDao) throws BusinessException;

    /*
    * 获取用户的关注列表
    * */
    public List<AppUserDao> getAppUserAllFollowByUserId(String userId, Integer pageIndex, Integer pageSize);

    /*
    * 获取用户的所有粉丝列表
    * */
    public List<AppUserDao> getAppUserAllFansByUserId(String userId, Integer pageIndex, Integer pageSize);

    /*
    * 删除用户关注关系
    * userId: 当前用户
    * followId: 关注的用户id
    * */
    public Boolean deleteAppUserRelation(String userId, String followId);

}
