package com.petsquare.mapper;

import com.petsquare.dao.StatusDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatusMapper {

    /*
    * 添加一条动态
    * */
    public Integer addStatus(StatusDao statusDao);

    /*
    * 根据userId查询动态
    * */
    public List<StatusDao> getStatusesByUserId(@Param("userId") String userId, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    /*
    * 根据当前位置由近及远获取动态
    * */
    public List<StatusDao> getStatusesByLocation(@Param("lat")String lat, @Param("lon")String lon, @Param("province")String province, @Param("city")String city,  @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    /*
    * 获取指定用户的所有关注好友发布的动态
    * */
    public List<StatusDao> getStatusesByUserFollow(@Param("userId") String userId, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);


    /*
    * 获取指定id的某条动态
    * */
    public StatusDao getStatusById(String id);

    /*
    * 更新动态信息
    * */
    public Integer updateStatus(StatusDao statusDao);

    /*
    * 删除指定id的动态(标记删除)
    * */
    public Integer deleteStatusById(String id);


}
