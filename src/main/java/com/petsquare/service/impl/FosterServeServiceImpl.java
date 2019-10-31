package com.petsquare.service.impl;

import com.petsquare.dao.user.AppUserDao;
import com.petsquare.dao.FosterServeDao;
import com.petsquare.dto.AppUserDto;
import com.petsquare.mapper.FosterServeMapper;
import com.petsquare.response.BusinessException;
import com.petsquare.response.ExceptionMsg;
import com.petsquare.service.AppUserService;
import com.petsquare.service.BaseService;
import com.petsquare.service.FosterServeService;
import com.petsquare.util.validate.ValiadationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FosterServeServiceImpl extends BaseService implements FosterServeService {

    @Autowired
    private FosterServeMapper fosterServeMapper;
    @Autowired
    private AppUserService appUserService;

    @Override
    public Boolean addFosterServe(FosterServeDao fosterServeDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(fosterServeDao);
        if (valiadationResult.getHasErrors()){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }

        //先判断当前用户是否已经实名认证
        AppUserDto appUserDto = appUserService.getAppUserInfoByUserId(fosterServeDao.getUser_id());

        if(appUserDto == null) {
            //用户不存在
            throw new BusinessException(ExceptionMsg.FAILURE, "用户不存在");
        }

        if (appUserDto.getAuth() == null) {
            //用户未实名认证
            throw new BusinessException(ExceptionMsg.FAILURE, "当前用户还未完成实名认证");
        }

        if (fosterServeMapper.getFosterServeAndUserByUserId(fosterServeDao.getUser_id()) != null){
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, "当前用户已经创建过服务");
        }

        return fosterServeMapper.addFosterServe(fosterServeDao) != 0;
    }

    @Override
    public FosterServeDao getFosterServeById(String id) {
        if (id == null) {
            return null;
        }
        return fosterServeMapper.getFosterServeById(id);
    }

    @Override
    public FosterServeDao getFosterServeAndUserById(String id) {
        if (id == null) {
            return null;
        }
        return fosterServeMapper.getFosterServeAndUserById(id);
    }

    @Override
    public FosterServeDao getFosterServeAndUserByUserId(String userId) {
        if (userId == null) {
            return null;
        }
        return fosterServeMapper.getFosterServeAndUserByUserId(userId);
    }

    @Override
    public List<FosterServeDao> getFosterServesByLocation(String province, String city, String lat, String lon, Integer pageIndex, Integer pageSize) {
        return fosterServeMapper.getFosterServesByLocation(province, city, lat, lon, pageIndex*pageSize, pageSize);
    }

    @Override
    public List<FosterServeDao> getFosterServesAndUsersByLocation(String province, String city, String lat, String lon, Integer pageIndex, Integer pageSize) {
        return fosterServeMapper.getFosterServesAndUsersByLocation(province, city, lat, lon, pageIndex*pageSize, pageSize);
    }

    @Override
    public Boolean deleteForsterServeById(String id) {
        if (id == null){
            return false;
        }
        return fosterServeMapper.deleteForsterServeById(id) != 0;
    }

    @Override
    public Boolean deleteForsterServeByUserId(String userId) {
        if (userId == null){
            return false;
        }
        return fosterServeMapper.deleteForsterServeByUserId(userId) != 0;
    }

    @Override
    public Boolean updateFosterServe(FosterServeDao fosterServeDao) {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(fosterServeDao);
        if (valiadationResult.getHasErrors()){
            return false;
        }

        return fosterServeMapper.updateFosterServe(fosterServeDao) != 0;
    }
}
