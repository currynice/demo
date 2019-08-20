package com.cxy.demo.dynamicallyswitch.dao;


import com.cxy.demo.dynamicallyswitch.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao {

    List<User> getAllUser1();

    List<User> getAllUser2();
}
