package com.petsquare.controller;

import com.petsquare.dao.FosterServeDao;
import com.petsquare.response.*;
import com.petsquare.service.FosterServeService;
import com.petsquare.util.validate.ValiadationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fosterService")
public class FosterServiceController extends BaseController {

    @Autowired
    private FosterServeService fosterServeService;

    /*
    * 用户创建寄养服务(需要完成实名认证)
    * */
    @RequestMapping(value = "/addFosterService", method = RequestMethod.POST)
    public Response addFosterService(FosterServeDao fosterServeDao) throws BusinessException {
        ValiadationResult valiadationResult = valiadationIpml.validateBean(fosterServeDao);
        if (valiadationResult.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, valiadationResult.getErrorsString());
        }
        Boolean result = fosterServeService.addFosterServe(fosterServeDao);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseDataObject(ExceptionMsg.FAILURE);
        }
    }


    /*
    * 获取指定id的服务信息
    * */
    @RequestMapping(value = "/getFosterServiceAndUserById", method = RequestMethod.POST)
    public Response getFosterServiceById(String id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, "id为空");
        }
        return new ResponseDataObject(fosterServeService.getFosterServeAndUserById(id));
    }

    /*
     * 获取指定用户id创建的服务信息
     * */
    @RequestMapping(value = "/getFosterServiceAndUserByUserId", method = RequestMethod.POST)
    public Response getFosterServiceAndUserByUserId(String userId) throws BusinessException {
        if (userId == null) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, "userId为空");
        }
        return new ResponseDataObject(fosterServeService.getFosterServeAndUserByUserId(userId));
    }

    /*
    * 根据位置信息 返回同城服务列表
    * */
    @RequestMapping(value = "/getFosterServicesAndUsersByLocation", method = RequestMethod.POST)
    public Response getFosterServicesAndUsersByLocation(String province, String city, String lat, String lon,
                                                        @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                        @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return new ResponseDataObject(fosterServeService.getFosterServesAndUsersByLocation(province, city, lat, lon, pageIndex, pageSize));
    }

    /*
    * 删除指定id的服务
    * */
    @RequestMapping(value = "/deleteForsterServiceById", method = RequestMethod.POST)
    public Response deleteForsterServiceById(String id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, "id为空");
        }
        Boolean result = fosterServeService.deleteForsterServeById(id);
        if (result){
            return new ResponseObject(ExceptionMsg.SUCCESS);
        }else {
            return new ResponseObject(ExceptionMsg.FAILURE);
        }
    }
}
