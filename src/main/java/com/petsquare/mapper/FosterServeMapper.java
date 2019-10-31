package com.petsquare.mapper;

import com.petsquare.dao.FosterServeDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FosterServeMapper {

    public Integer addFosterServe(FosterServeDao fosterServeDao);

    public FosterServeDao getFosterServeById(String id);

    public FosterServeDao getFosterServeAndUserById(String id);

    public FosterServeDao getFosterServeAndUserByUserId(String userId);

    public List<FosterServeDao> getFosterServesByLocation(@Param("province")String province, @Param("city")String city, @Param("lat")String lat, @Param("lon")String lon, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    public List<FosterServeDao> getFosterServesAndUsersByLocation(@Param("province")String province, @Param("city")String city, @Param("lat")String lat, @Param("lon")String lon, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    public Integer deleteForsterServeById(String id);

    public Integer deleteForsterServeByUserId(String userId);

    public Integer updateFosterServe(FosterServeDao fosterServeDao);

}
