package com.petsquare.service.impl;

import com.petsquare.dao.*;
import com.petsquare.dao.user.AppUserAuthDao;
import com.petsquare.dao.user.AppUserDao;
import com.petsquare.dao.user.AppUserRelationDao;
import com.petsquare.dao.user.AppUserSocialDao;
import com.petsquare.dto.AppUserDto;
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
import lombok.Synchronized;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    public Boolean addAppUser(AppUserDto appUserDto) throws BusinessException {

        appUserDto.setUser_id(this.generateUserId());

        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserDto);

        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }

        AppUserDto existUser = this.getAppUserInfoByPhone(appUserDto.getPhone());
        if (existUser != null) {
            return Boolean.FALSE;
        }
        return appUserMapper.addAppUser(convertAppUserDto2AppUserDao(appUserDto)) != 0;
    }

    @Override
    @Transactional
    public AppUserDto getAppUserInfoByPhone(String phone) {
        if (phone == null || phone.length() == 0) {
            return null;
        }
        AppUserDao appUserDao =  appUserMapper.getAppUserInfoByPhone(phone);


        AppUserDto appUserDto =  convertAppUserDao2AppUserDto(appUserDao);
        appUserDto.setAuth(getAppUserAuthByUserId(appUserDto.getUser_id()));
        appUserDto.setSocial(getAppUserSocialByUserId(appUserDto.getUser_id()));
        appUserDto.setFans_num(appUserRelationMapper.getUserAllFansCountByUserId(appUserDao.getUser_id()));
        appUserDto.setFollow_num(appUserRelationMapper.getUserAllFollowCountByUserId(appUserDao.getUser_id()));
        return appUserDto;
    }

    @Override
    @Transactional
    public AppUserDto getAppUserInfoByUserId(String userId) {
        if (userId == null) {
            return null;
        }
        AppUserDao appUserDao = appUserMapper.getAppUserInfoByUserId(userId);
        AppUserDto appUserDto =  convertAppUserDao2AppUserDto(appUserDao);
        appUserDto.setAuth(getAppUserAuthByUserId(userId));
        appUserDto.setSocial(getAppUserSocialByUserId(userId));
        appUserDto.setFans_num(appUserRelationMapper.getUserAllFansCountByUserId(userId));
        appUserDto.setFollow_num(appUserRelationMapper.getUserAllFollowCountByUserId(userId));
        return appUserDto;
    }

    @Override
    public Boolean addAppUserAuth(AppUserDto.AppUserAuthDto appUserAuthDto) throws BusinessException {

        AppUserAuthDao appUserAuthDao = convertAppUserAuthDto2AppUserAuthDao(appUserAuthDto);

        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserAuthDao);

        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserAuthMapper.addAppUserAuth(appUserAuthDao) != 0;
    }


    @Override
    public AppUserDto.AppUserAuthDto getAppUserAuthByUserId(String userId) {
        if (userId == null || userId.length() == 0) {
            return null;
        }
        AppUserAuthDao appUserAuthDao = appUserAuthMapper.getAppUserAuthByUserId(userId);
        return convertAppUserAuthDao2AppUserAuthDto(appUserAuthDao);
    }

    @Override
    public Boolean updateAppUserAuth(AppUserDto.AppUserAuthDto appUserAuthDto) throws BusinessException {
        AppUserAuthDao appUserAuthDao = convertAppUserAuthDto2AppUserAuthDao(appUserAuthDto);
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserAuthDao);

        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserAuthMapper.updateAppUserAuth(appUserAuthDao) != 0;
    }

    @Override
    public Boolean addAppUserSocial(AppUserDto.AppUserSocialDto appUserSocialDto) throws BusinessException {
        AppUserSocialDao appUserSocialDao = convertAppUserSocialDto2AppUserSocialDao(appUserSocialDto);
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserSocialDao);
        if (valiadationResult.getHasErrors()){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserSocialMapper.addAppUserSocial(appUserSocialDao) != 0;
    }

    @Override
    public AppUserDto.AppUserSocialDto getAppUserSocialByUserId(String userId) {
        if (userId == null || userId.length() == 0) {
            return null;
        }
        AppUserSocialDao appUserSocialDao = appUserSocialMapper.getAppUserSocialByUserId(userId);
        return convertAppUserSocialDao2AppUserSocialDto(appUserSocialDao);
    }

    @Override
    public Boolean updateAppUserSocial(AppUserDto.AppUserSocialDto appUserSocialDto) throws BusinessException {
        AppUserSocialDao appUserSocialDao = convertAppUserSocialDto2AppUserSocialDao(appUserSocialDto);
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserSocialDao);
        if (valiadationResult.getHasErrors()){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserSocialMapper.updateAppUserSocial(appUserSocialDao) != 0;
    }

    @Override
    public Boolean addAppUserRelation(AppUserDto.AppUserRelationDto appUserRelationDto) throws BusinessException {
        AppUserRelationDao appUserRelationDao = convertAppUserRelationDto2AppUserRelationDao(appUserRelationDto);
        ValiadationResult valiadationResult = valiadationIpml.validateBean(appUserRelationDao);
        if (valiadationResult.getHasErrors()){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return appUserRelationMapper.addAppUserRelation(appUserRelationDao) != 0;
    }

    @Override
    public List<AppUserDto> getAppUserAllFollowByUserId(String userId, Integer pageIndex, Integer pageSize) {
        if (userId == null || userId.length() == 0 ){
            return null;
        }
        List<AppUserDao> appUserDaos = appUserRelationMapper.getUserAllFollowByUserId(userId, pageIndex*pageSize, pageSize);
        List<AppUserDto> appUserDtos = new ArrayList<>();
        for (AppUserDao appUserDao : appUserDaos) {
            appUserDtos.add(convertAppUserDao2AppUserDto(appUserDao));
        }
        return appUserDtos;
    }

    @Override
    public List<AppUserDto> getAppUserAllFansByUserId(String userId, Integer pageIndex, Integer pageSize) {
        if (userId == null || userId.length() == 0 ){
            return null;
        }
        List<AppUserDao> appUserDaos = appUserRelationMapper.getUserAllFansByUserId(userId, pageIndex*pageSize, pageSize);
        List<AppUserDto> appUserDtos = new ArrayList<>();
        for (AppUserDao appUserDao : appUserDaos) {
            appUserDtos.add(convertAppUserDao2AppUserDto(appUserDao));
        }
        return appUserDtos;
    }

    @Override
    public Boolean deleteAppUserRelation(String userId, String followId) {
        if (userId == null || followId == null){
            return false;
        }
        return appUserRelationMapper.deleteAppUserRelation(userId, followId) != 0;
    }


    /*
    * 生成用户id
    * */
    @Synchronized
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


    private AppUserDao convertAppUserDto2AppUserDao(AppUserDto appUserDto){
        if (appUserDto == null) return null;
        try {
            AppUserDao appUserDao = new AppUserDao();
            PropertyUtils.copyProperties(appUserDao, appUserDto);
            return appUserDao;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AppUserDto convertAppUserDao2AppUserDto(AppUserDao appUserDao){
        if (appUserDao == null) return null;
        try {
            AppUserDto appUserDto = new AppUserDto();
            PropertyUtils.copyProperties(appUserDto, appUserDao);
            return appUserDto;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AppUserDto.AppUserAuthDto convertAppUserAuthDao2AppUserAuthDto(AppUserAuthDao appUserAuthDao) {
        if (appUserAuthDao == null) return null;
        try {
            AppUserDto.AppUserAuthDto appUserAuthDto = new AppUserDto.AppUserAuthDto();
            PropertyUtils.copyProperties(appUserAuthDto, appUserAuthDao);
            return appUserAuthDto;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    private AppUserAuthDao convertAppUserAuthDto2AppUserAuthDao(AppUserDto.AppUserAuthDto appUserAuthDto) {
        if (appUserAuthDto == null) return null;
        try {
            AppUserAuthDao appUserAuthDao = new AppUserAuthDao();
            PropertyUtils.copyProperties(appUserAuthDao, appUserAuthDto);
            return appUserAuthDao;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    private AppUserDto.AppUserSocialDto convertAppUserSocialDao2AppUserSocialDto(AppUserSocialDao appUserSocialDao) {
        if (appUserSocialDao == null) return null;
        try {
            AppUserDto.AppUserSocialDto appUserSocialDto = new AppUserDto.AppUserSocialDto();
            PropertyUtils.copyProperties(appUserSocialDto, appUserSocialDao);
            return appUserSocialDto;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AppUserSocialDao convertAppUserSocialDto2AppUserSocialDao(AppUserDto.AppUserSocialDto appUserSocialDto) {
        if (appUserSocialDto == null) return null;
        try {
            AppUserSocialDao appUserSocialDao = new AppUserSocialDao();
            PropertyUtils.copyProperties(appUserSocialDao, appUserSocialDto);
            return appUserSocialDao;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AppUserRelationDao convertAppUserRelationDto2AppUserRelationDao(AppUserDto.AppUserRelationDto appUserRelationDto) {
        if (appUserRelationDto == null) return null;
        try {
            AppUserRelationDao appUserRelationDao = new AppUserRelationDao();
            PropertyUtils.copyProperties(appUserRelationDao, appUserRelationDto);
            return appUserRelationDao;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AppUserDto.AppUserRelationDto convertAppUserRelationDao2AppUserRelationDto(AppUserRelationDao appUserRelationDao) {
        if (appUserRelationDao == null) return null;
        try {
            AppUserDto.AppUserRelationDto appUserRelationDto = new AppUserDto.AppUserRelationDto();
            PropertyUtils.copyProperties(appUserRelationDto, appUserRelationDao);
            return appUserRelationDto;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
