package com.cocoawork.service.impl;

import com.cocoawork.mapper.UserMapper;
import com.cocoawork.pojo.UserDO;
import com.cocoawork.response.BusinessException;
import com.cocoawork.response.ExceptionMsg;
import com.cocoawork.service.UserService;
import com.cocoawork.utils.ValiadationIpml;
import com.cocoawork.utils.ValiadationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ValiadationIpml valiadationIpml;

    @Override
    public UserDO getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void addUser(@Valid UserDO userDO) throws BusinessException {
        if (userDO == null) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, null);
        }
        ValiadationResult result = valiadationIpml.validateBean(userDO);
        if (result.getHasErrors()) {
            throw new BusinessException(ExceptionMsg.PARAMETER_ERROR, result.getErrorsString());
        }
        userMapper.insertUser(userDO);
    }


    @Override
    public UserDO login(String name, String password) {
        if (name == null || name.length() == 0) return null;
        UserDO userDO = userMapper.getUserByNameAndPassword(name, password);
        return userDO;
    }
}
