package com.petsquare.mapper;

import com.petsquare.dao.StatusCommentDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatusCommentMapper {

    public Integer addStatusComment(StatusCommentDao statusCommentDao);

    public List<StatusCommentDao> getStatusCommentsByStatusId(@Param("statusId")String statusId, @Param("curIndex") Integer curIndex, @Param("pageSize") Integer pageSize);

    public List<StatusCommentDao> getStatusCommentsAndUsersByStatusId(@Param("statusId")String statusId, @Param("curIndex") Integer curIndex, @Param("pageSize") Integer pageSize);

    public List<StatusCommentDao> getStatusCommentsByUserId(@Param("userId")String userId, @Param("curIndex") Integer curIndex, @Param("pageSize") Integer pageSize);

    public Integer deleteStatusCommentById(String id);

}
