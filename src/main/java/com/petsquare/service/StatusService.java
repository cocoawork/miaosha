package com.petsquare.service;


import com.petsquare.dao.AppUserDao;
import com.petsquare.dao.StatusCommentDao;
import com.petsquare.dao.StatusDao;
import com.petsquare.dao.StatusLikeDao;
import com.petsquare.response.BusinessException;

import java.util.List;

public interface StatusService {

    /*
     * 添加一条动态
     * */
    public Boolean addStatus(StatusDao statusDao) throws BusinessException;

    /*
     * 获取指定id的某条动态
     * */
    public StatusDao getStatusById(String id);

    /*
     * 根据userId查询动态
     * */
    public List<StatusDao> getStatusesByUserId(String userId, Integer pageIndex, Integer pageSize);

    /*
     * 根据当前位置由近及远获取动态
     * */
    public List<StatusDao> getStatusesByLocation(String lat, String lon, String province, String city, Integer pageIndex, Integer pageSize);

    /*
    * 获取用户所有关注好友的发布动态
    * */
    public List<StatusDao> getStatusesByUserFollow(String userId, Integer pageIndex, Integer pageSize);

    /*
     * 更新动态信息
     * */
    public Boolean updateStatus(StatusDao statusDao) throws BusinessException;

    /*
     * 删除指定id的动态(标记删除)
     * */
    public Boolean deleteStatusById(String id);

    /*
    * 添加动态点赞
    * */
    public Boolean addStatusLike(StatusLikeDao statusLikeDao) throws BusinessException;

    /*
    * 获取某条动态的所有点赞者信息
    * */
    public List<AppUserDao> getStatusLikeByStatusId(String statusId);

    /*
    * 删除某条点赞
    * */
    public Boolean deleteStatusLike(String userId, String statusId);

    /*
    * 为某条动态添加评论
    * */
    public Boolean addStatusComment(StatusCommentDao statusCommentDao) throws BusinessException;

    /*
    * 获取某条动态的所有评论
    * */
    public List<StatusCommentDao> getStatusCommentsByStatusId(String statusId, Integer pageIndex, Integer pageSize);

    /*
     * 获取某条动态的所有评论信息(包括评论者信息)
     * */
    public List<StatusCommentDao> getStatusCommentsAndUsersByStatusId(String statusId, Integer pageIndex, Integer pageSize);

    /*
     * 获取某用户的所有评论
     * */
    public List<StatusCommentDao> getStatusCommentsByUserId(String userId, Integer pageIndex, Integer pageSize);

    /*
    * 删除某条评论
    * */
    public Boolean deleteStatusCommentById(String id);


}
