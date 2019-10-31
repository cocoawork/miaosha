package com.petsquare.controller;

import com.petsquare.dao.user.AppUserDao;
import com.petsquare.dao.status.StatusCommentDao;
import com.petsquare.dao.status.StatusDao;
import com.petsquare.dao.status.StatusLikeDao;
import com.petsquare.response.*;
import com.petsquare.service.StatusService;
import com.petsquare.util.validate.ValiadationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController extends BaseController {

    @Autowired
    private StatusService statusService;

    /*
    * 发布一条动态
    * */
    @RequestMapping(value = "/addStatus", method = RequestMethod.POST)
    public Response addStatus(StatusDao statusDao) throws BusinessException {

        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }

        Boolean result = statusService.addStatus(statusDao);
        if (result) {
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }


    /*
    * 获取指定用户id的所有动态列表
    * */
    @RequestMapping(value = "/getStatusesByUserId", method = RequestMethod.POST)
    public Response getStatusesByUserId(String userId, @RequestParam(value = "pageIndex", defaultValue = "0")Integer pageIndex,
                                                       @RequestParam(value = "pageSize", defaultValue = "20")Integer pageSize) {

        if (userId == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        List<StatusDao> statusDaoList = statusService.getStatusesByUserId(userId, pageIndex, pageSize);
        return new ResponseDataObject(statusDaoList);
    }


    /*
    * 根据位置信息, 由近及远返回动态信息
    * */
    @RequestMapping(value = "/getStatusesByLocation", method = RequestMethod.POST)
    public Response getStatusesByLocation(String province, String city, String lat, String lon,
                                          @RequestParam(value = "pageIndex", defaultValue = "0")Integer pageIndex,
                                          @RequestParam(value = "pageSize", defaultValue = "20")Integer pageSize) {
        List<StatusDao> statusDaoList = statusService.getStatusesByLocation(lat, lon, province, city, pageIndex, pageSize);
        return new ResponseDataObject(statusDaoList);
    }

    /*
    * 获取指定用户关注好友发布的所有动态
    * */
    @RequestMapping(value = "/getStatusesByUserFollow", method = RequestMethod.POST)
    public Response getStatusesByUserFollow(String userId, @RequestParam(value = "pageIndex", defaultValue = "0")Integer pageIndex,
                                                            @RequestParam(value = "pageSize", defaultValue = "20")Integer pageSize){
        if (userId == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        return new ResponseDataObject(statusService.getStatusesByUserFollow(userId, pageIndex, pageSize));
    }

    /*
    * 获取指定id的动态详情
    * */
    @RequestMapping(value = "/getStatusById", method = RequestMethod.POST)
    public Response getStatusById(String id) {
        if (id == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        StatusDao statusDao = statusService.getStatusById(id);
        return new ResponseDataObject(statusDao);
    }

    /*
    * 删除指定id的动态(标记删除)
    * */
    @RequestMapping(value = "/deleteStatusById", method = RequestMethod.POST)
    public Response deleteStatusById(String id) {
        if (id == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        Boolean result = statusService.deleteStatusById(id);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }

    /*
    * 更新动态信息
    * */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public Response updateStatus(StatusDao statusDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        Boolean result = statusService.updateStatus(statusDao);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }

    /*
    * 对某条动态点赞
    * */
    @RequestMapping(value = "/addStatusLike", method = RequestMethod.POST)
    public Response addStatusLike(StatusLikeDao statusLikeDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusLikeDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        Boolean result = statusService.addStatusLike(statusLikeDao);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }

    /*
    * 取消某条动态的点赞
    * */
    @RequestMapping(value = "/deleteStatusLike", method = RequestMethod.POST)
    public Response deleteStatusLike(String userId, String statusId) {
        if (userId == null || statusId == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        Boolean result = statusService.deleteStatusLike(userId, statusId);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }

    /*
    * 获取某条动态的所有点赞者信息
    * */
    @RequestMapping(value = "/getStatusLikeByStatusrId", method = RequestMethod.POST)
    public Response getStatusLikeByUserId(String statusId) {
        if (statusId == null){
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        List<AppUserDao> appUserDaoList = statusService.getStatusLikeByStatusId(statusId);
        return new ResponseDataObject(appUserDaoList);
    }

    /*
    * 添加一条评论
    * */
    @RequestMapping(value = "/addStatusComment", method = RequestMethod.POST)
    public Response addStatusComment(StatusCommentDao statusCommentDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(statusCommentDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        Boolean result = statusService.addStatusComment(statusCommentDao);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }

    /*
    * 删除指定id的评论
    * */
    @RequestMapping(value = "/deleteStatusCommentById", method = RequestMethod.POST)
    public Response deleteStatusCommentById(String id){
        if (id == null){
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        Boolean result = statusService.deleteStatusCommentById(id);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }

    /*
    * 获取指定动态的所有评论
    * */
    @RequestMapping(value = "/getStatusCommentsByStatusId", method = RequestMethod.POST)
    public Response getStatusCommentsByStatusId(String statusId, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                                 @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize){
        if (statusId == null){
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        return new ResponseDataObject(statusService.getStatusCommentsByStatusId(statusId, pageIndex, pageSize));
    }

    /*
     * 获取指定动态的所有评论(包含评论者信息)
     * */
    @RequestMapping(value = "/getStatusCommentsAndUsersByStatusId", method = RequestMethod.POST)
    public Response getStatusCommentsAndUsersByStatusId(String statusId, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize){
        if (statusId == null){
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        return new ResponseDataObject(statusService.getStatusCommentsAndUsersByStatusId(statusId, pageIndex, pageSize));
    }

    /*
    * 获取指定用户id的所有历史评论
    * */
    @RequestMapping(value = "/getStatusCommentsByUserId", method = RequestMethod.POST)
    public Response getStatusCommentsByUserId(String userId, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        if (userId == null) {
            return new ResponseObject(ExceptionMsg.PARAMETER_ERROR);
        }
        return new ResponseDataObject(statusService.getStatusCommentsByUserId(userId, pageIndex, pageSize));
    }

}
