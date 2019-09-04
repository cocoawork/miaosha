package com.petsquare.mapper;

import com.petsquare.dao.AppUserDao;
import com.petsquare.dao.AppUserRelationDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppUserRelationMapper {

    public Integer addAppUserRelation(AppUserRelationDao appUserRelationDao);

    /*
    * 获取用户所有关注
    * */
    public List<AppUserDao> getUserAllFollowByUserId(@Param("userId")String userId, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    /*
    * 获取用户所有粉丝
    * */
    public List<AppUserDao> getUserAllFansByUserId(@Param("userId")String userId, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    /*
    * 取消用户关注
    * */
    public Integer deleteAppUserRelation(@Param("userId")String userId, @Param("followId")String followId);
}
