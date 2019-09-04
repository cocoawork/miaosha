package com.petsquare.mapper;

import com.petsquare.dao.FosterServiceDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FosterServiceMapper {

    public Integer addFosterService(FosterServiceDao fosterServiceDao);

    public FosterServiceDao getFosterServiceById(String id);

    public FosterServiceDao getFosterServiceAndUserById(String id);

    public FosterServiceDao getFosterServiceAndUserByUserId(String userId);

    public List<FosterServiceDao> getFosterServicesByLocation(@Param("province")String province, @Param("city")String city, @Param("lat")String lat, @Param("lon")String lon, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    public List<FosterServiceDao> getFosterServicesAndUsersByLocation(@Param("province")String province, @Param("city")String city, @Param("lat")String lat, @Param("lon")String lon, @Param("curIndex")Integer curIndex, @Param("pageSize")Integer pageSize);

    public Integer deleteForsterServiceById(String id);

    public Integer deleteForsterServiceByUserId(String userId);

    public Integer updateFosterService(FosterServiceDao fosterServiceDao);

}
