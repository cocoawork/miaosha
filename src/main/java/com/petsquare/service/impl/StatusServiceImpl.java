package com.petsquare.service.impl;

import com.petsquare.dao.user.AppUserDao;
import com.petsquare.dao.status.StatusCommentDao;
import com.petsquare.dao.status.StatusDao;
import com.petsquare.dao.status.StatusLikeDao;
import com.petsquare.mapper.StatusCommentMapper;
import com.petsquare.mapper.StatusLikeMapper;
import com.petsquare.mapper.StatusMapper;
import com.petsquare.response.BusinessException;
import com.petsquare.response.ExceptionMsg;
import com.petsquare.service.AppUserService;
import com.petsquare.service.BaseService;
import com.petsquare.service.StatusService;
import com.petsquare.util.validate.ValiadationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl extends BaseService implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private StatusLikeMapper statusLikeMapper;

    @Autowired
    private StatusCommentMapper statusCommentMapper;

    @Autowired
    private AppUserService appUserService;


    @Override
    public Boolean addStatus(StatusDao statusDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }

        if (appUserService.getAppUserInfoByUserId(statusDao.getUser_id()) == null) {
            //用户不存在
            throw new BusinessException(ExceptionMsg.FAILURE, "用户不存在");
        }

        return statusMapper.addStatus(statusDao) != 0;
    }

    @Override
    public StatusDao getStatusById(String id) {
        if (id == null) {
            return null;
        }
        return statusMapper.getStatusById(id);
    }

    @Override
    public List<StatusDao> getStatusesByUserId(String userId, Integer pageIndex, Integer pageSize) {
        if (userId == null) {
            return null;
        }
        return statusMapper.getStatusesByUserId(userId, pageIndex*pageSize, pageSize);
    }

    @Override
    public List<StatusDao> getStatusesByLocation(String lat, String lon, String province, String city, Integer pageIndex, Integer pageSize) {

        return statusMapper.getStatusesByLocation(lat, lon, province, city, pageIndex*pageSize, pageSize);
    }

    @Override
    public List<StatusDao> getStatusesByUserFollow(String userId, Integer pageIndex, Integer pageSize) {
        if (userId == null) {
            return null;
        }
        return statusMapper.getStatusesByUserFollow(userId, pageIndex*pageSize, pageSize);
    }

    @Override
    public Boolean updateStatus(StatusDao statusDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return statusMapper.updateStatus(statusDao) != 0;
    }

    @Override
    public Boolean deleteStatusById(String id) {
        if (id == null) {
            return false;
        }
        return statusMapper.deleteStatusById(id) != 0;
    }

    @Override
    public Boolean addStatusLike(StatusLikeDao statusLikeDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusLikeDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return statusLikeMapper.addStatusLike(statusLikeDao) != 0;
    }

    @Override
    public List<AppUserDao> getStatusLikeByStatusId(String statusId) {
        if (statusId == null){
            return null;
        }
        return statusLikeMapper.getAllStatusLikeByStatusId(statusId);
    }


    @Override
    public Boolean deleteStatusLike(String userId, String statusId) {
        if (statusId == null || userId == null) {
            return false;
        }
        return statusLikeMapper.deleteStatusLike(userId, statusId) != 0;
    }


    @Override
    public Boolean addStatusComment(StatusCommentDao statusCommentDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusCommentDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        return statusCommentMapper.addStatusComment(statusCommentDao) != 0;
    }

    @Override
    public List<StatusCommentDao> getStatusCommentsByStatusId(String statusId, Integer pageIndex, Integer pageSize) {
        if (statusId == null) {
            return null;
        }
        return statusCommentMapper.getStatusCommentsByStatusId(statusId, pageIndex*pageSize, pageSize);
    }

    @Override
    public List<StatusCommentDao> getStatusCommentsAndUsersByStatusId(String statusId, Integer pageIndex, Integer pageSize) {
        if (statusId == null){
            return null;
        }
        return statusCommentMapper.getStatusCommentsAndUsersByStatusId(statusId, pageIndex*pageSize, pageSize);
    }

    @Override
    public List<StatusCommentDao> getStatusCommentsByUserId(String userId, Integer pageIndex, Integer pageSize) {
        if (userId == null) {
            return null;
        }
        return statusCommentMapper.getStatusCommentsByUserId(userId, pageIndex*pageSize, pageSize);
    }

    @Override
    public Boolean deleteStatusCommentById(String id) {
        if (id == null){
            return false;
        }
        return statusCommentMapper.deleteStatusCommentById(id) != 0;
    }


}
