package com.petsquare.service;

import com.petsquare.dto.AppUserDto;
import com.petsquare.response.BusinessException;

import java.util.List;

public interface AppUserService {

    /*
    * 添加app用户
    * */
    public Boolean addAppUser(AppUserDto appUserDto) throws BusinessException;

    /*
    * 通过手机号查询用户和用户认证信息
    * */
    public AppUserDto getAppUserInfoByPhone(String phone);

    /*
     * 通过用户id查询用户和用户认证信息
     * */
    public AppUserDto getAppUserInfoByUserId(String userId);

    /*
    * 添加用户认证信息
    * */
    public Boolean addAppUserAuth(AppUserDto.AppUserAuthDto appUserAuthDto) throws BusinessException;

    /*
    * 查询用户认证信息
    * */
    public AppUserDto.AppUserAuthDto getAppUserAuthByUserId(String userId);

    /*
    * 更新用户认证信息
    * */
    public Boolean updateAppUserAuth(AppUserDto.AppUserAuthDto appUserAuthDto) throws BusinessException;

    /*
    * 添加用户社交账号信息
    * */
    public Boolean addAppUserSocial(AppUserDto.AppUserSocialDto appUserSocialDto) throws BusinessException;

    /*
    * 获取用户社交账号信息
    * */
    public AppUserDto.AppUserSocialDto getAppUserSocialByUserId(String userId);

    /*
    * 更新用户社交账号信息
    * */
    public Boolean updateAppUserSocial(AppUserDto.AppUserSocialDto appUserSocialDto) throws BusinessException;

    /*
    * 新增用户关注关系
    * */
    public Boolean addAppUserRelation(AppUserDto.AppUserRelationDto appUserRelationDto) throws BusinessException;

    /*
    * 获取用户的关注列表
    * */
    public List<AppUserDto> getAppUserAllFollowByUserId(String userId, Integer pageIndex, Integer pageSize);

    /*
    * 获取用户的所有粉丝列表
    * */
    public List<AppUserDto> getAppUserAllFansByUserId(String userId, Integer pageIndex, Integer pageSize);

    /*
    * 删除用户关注关系
    * userId: 当前用户
    * followId: 关注的用户id
    * */
    public Boolean deleteAppUserRelation(String userId, String followId);

}
