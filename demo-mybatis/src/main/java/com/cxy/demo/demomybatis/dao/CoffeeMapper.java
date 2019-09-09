package com.cxy.demo.demomybatis.dao;


import com.cxy.demo.demomybatis.entity.Coffee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CoffeeMapper {

    //返回主键
    @Insert(value = "INSERT INTO t_coffee(name,price,create_time,update_time)" + "VALUES (#{name},#{price},now(),now())")
    @Options(useGeneratedKeys=true)
    Long save(Coffee coffee);

    @Select("SELECT * FROM t_coffee WHERE id  = #{id}")
    // map-underscore-to-camel-case = true 可以实现一样的效果
    @Results({@Result(id = true,column = "id",property = "id"),@Result(column = "create_time",property = "createTime"),@Result(column = "update_time", property = "updateTime")})
    Coffee findById(@Param("id")Long id);

}
