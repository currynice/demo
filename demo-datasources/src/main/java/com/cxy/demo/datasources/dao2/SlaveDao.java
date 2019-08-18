package com.cxy.demo.datasources.dao2;

import com.cxy.demo.datasources.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlaveDao {

    List<User> getAllUser();
}
