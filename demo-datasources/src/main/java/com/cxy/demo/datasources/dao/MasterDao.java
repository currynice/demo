package com.cxy.demo.datasources.dao;

import com.cxy.demo.datasources.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MasterDao {

    List<User> getAllUser();
}
