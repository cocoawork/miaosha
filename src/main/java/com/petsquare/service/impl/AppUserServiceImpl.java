package com.petsquare.service.impl;

import com.petsquare.dao.*;
import com.petsquare.mapper.AppUserAuthMapper;
import com.petsquare.mapper.AppUserMapper;
import com.petsquare.mapper.AppUserRelationMapper;
import com.petsquare.mapper.AppUserSocialMapper;
import com.petsquare.response.BusinessException;
import com.petsquare.response.ExceptionMsg;
import com.petsquare.service.AppUserService;
import com.petsquare.service.BaseService;
import com.petsquare.service.DataSequenceService;
import com.petsquare.util.validate.ValiadationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl extends BaseService implements AppUserService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private AppUserAuthMapper appUserAuthMapper;

    @Autowired
    private DataSequenceService dataSequenceService;

    @Autowired
    private AppUserSocialMapper appUserSocialMapper;

    @Autowired
    private AppUserRelationMapper appUserRelationMapper;


    @Override
    public Boolean addAppUser(AppUserDao user) throws BusinessException {

        user.setUser_id(this.generateUserId());

        ValiadationResult valiadationResult = valiadationIpml.validateBean(user);

        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }

        AppUserDao existUser = this.getAppUserByPhone(user.getPhone());
        if (existUser != null) {
            return Boolean.FALSE;
        }


        int id = appUserMapper.addAppUser(user);
        return id != 0;
    }


    @Override
    public AppUserDao getAppUserByPhone(String phone) {
        if (phone == null || phone.length() == 0) {
            return null;
        }
        return appUserMapper.getAppUserByPhone(phone);
    }


    @Override
    public Boolean addAppUserAuth(AppUserAuthDao appUserAuthDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserAuthDao);

        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserAuthMapper.addAppUserAuth(appUserAuthDao) != 0;

    }

    @Override
    public AppUserDao getAppUserAndAuthInfoByPhone(String phone) {
        if (phone == null || phone.length() == 0) {
            return null;
        }
        return appUserMapper.getAppUserAndAuthInfoByPhone(phone);
    }

    @Override
    public AppUserAuthDao getAppUserAuthByUserId(String userId) {
        if (userId == null || userId.length() == 0) {
            return null;
        }
        return appUserAuthMapper.getAppUserAuthByUserId(userId);
    }

    @Override
    public Boolean updateAppUserAuth(AppUserAuthDao appUserAuthDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserAuthDao);

        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserAuthMapper.updateAppUserAuth(appUserAuthDao) != 0;
    }

    @Override
    public Boolean addAppUserSocial(AppUserSocialDao appUserSocialDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserSocialDao);
        if (valiadationResult.getHasErrors()){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserSocialMapper.addAppUserSocial(appUserSocialDao) != 0;
    }

    @Override
    public AppUserSocialDao getAppUserSocialByUserId(String userId) {
        if (userId == null || userId.length() == 0) {
            return null;
        }
        return appUserSocialMapper.getAppUserSocialByUserId(userId);
    }

    @Override
    public Boolean updateAppUserSocial(AppUserSocialDao appUserSocialDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserSocialDao);
        if (valiadationResult.getHasErrors()){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserSocialMapper.updateAppUserSocial(appUserSocialDao) != 0;
    }

    @Override
    public Boolean addAppUserRelation(AppUserRelationDao appUserRelationDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserRelationDao);
        if (valiadationResult.getHasErrors()){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserRelationMapper.addAppUserRelation(appUserRelationDao) != 0;
    }

    @Override
    public List<AppUserDao> getAppUserAllFollowByUserId(String userId, Integer pageIndex, Integer pageSize) {
        if (userId == null || userId.length() == 0 ){
            return null;
        }
        return appUserRelationMapper.getUserAllFollowByUserId(userId, pageIndex*pageSize, pageSize);
    }

    @Override
    public List<AppUserDao> getAppUserAllFansByUserId(String userId, Integer pageIndex, Integer pageSize) {
        if (userId == null || userId.length() == 0 ){
            return null;
        }
        return appUserRelationMapper.getUserAllFansByUserId(userId, pageIndex*pageSize, pageSize);
    }

    @Override
    public Boolean deleteAppUserRelation(String userId, String followId) {
        if (userId == null || followId == null){
            return false;
        }
        return appUserRelationMapper.deleteAppUserRelation(userId, followId) != 0;
    }

    private String generateUserId() {

        String tableName = "app_user";
        DataSequenceDao dataSequenceDao = dataSequenceService.getDataSequenceByName(tableName);
        if (dataSequenceDao == null) {
            dataSequenceDao = new DataSequenceDao();
            dataSequenceDao.setBegin(100000000);
            dataSequenceDao.setCurrent(100000000);
            dataSequenceDao.setStep(1);
            dataSequenceDao.setName(tableName);
            dataSequenceService.addSequence(dataSequenceDao);
        }
        Integer next = dataSequenceDao.getCurrent() + dataSequenceDao.getStep();
        dataSequenceDao.setCurrent(next);
        dataSequenceService.updateDataSequence(dataSequenceDao);
        return String.valueOf(dataSequenceDao.getCurrent());
    }

}
