package com.cocoawork.service;

import com.cocoawork.pojo.UserDO;
import com.cocoawork.response.BusinessException;


public interface UserService {

    public UserDO getUserById(Long id);

    public void addUser(UserDO userDO) throws BusinessException;

    public UserDO login(String name, String password);
}
