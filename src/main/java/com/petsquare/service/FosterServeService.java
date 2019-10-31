package com.petsquare.service;

import com.petsquare.dao.FosterServeDao;
import com.petsquare.response.BusinessException;

import java.util.List;

public interface FosterServeService {

    public Boolean addFosterServe(FosterServeDao fosterServeDao) throws BusinessException;

    public FosterServeDao getFosterServeById(String id);

    public FosterServeDao getFosterServeAndUserById(String id);

    public FosterServeDao getFosterServeAndUserByUserId(String userId);

    public List<FosterServeDao> getFosterServesByLocation(String province, String city, String lat, String lon, Integer pageIndex, Integer pageSize);

    public List<FosterServeDao> getFosterServesAndUsersByLocation(String province, String city, String lat, String lon, Integer pageIndex, Integer pageSize);

    public Boolean deleteForsterServeById(String id);

    public Boolean deleteForsterServeByUserId(String userId);

    public Boolean updateFosterServe(FosterServeDao fosterServeDao);

}
