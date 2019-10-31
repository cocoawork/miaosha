package com.petsquare.mapper;

import com.petsquare.dao.user.AppUserDao;
import com.petsquare.dao.status.StatusLikeDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatusLikeMapper {

    public Integer addStatusLike(StatusLikeDao statusLikeDao);

    public List<AppUserDao> getAllStatusLikeByStatusId(String statusId);

    public Integer deleteStatusLike(@Param("userId")String userId, @Param("statusId")String statusId);



}
