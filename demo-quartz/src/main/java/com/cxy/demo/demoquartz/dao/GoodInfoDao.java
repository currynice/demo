package com.cxy.demo.demoquartz.dao;


import com.cxy.demo.demoquartz.entity.GoodInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodInfoDao {

    //返回主键
    @Insert(value = "INSERT INTO basic_good_info(name,price,unit)" + "VALUES (#{name},#{price},#{unit})")
    Long save(GoodInfo goodInfo);
}
