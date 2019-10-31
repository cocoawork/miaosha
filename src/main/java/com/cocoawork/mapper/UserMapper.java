package com.cocoawork.mapper;

import com.cocoawork.pojo.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public UserDO getUserById(Long id);

    public void insertUser(UserDO userDO);

    public UserDO getUserByNameAndPassword(@Param("telphone") String telphone, @Param("password") String password);


}
