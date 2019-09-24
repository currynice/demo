package com.cxy.demo.democache.dao;



import com.cxy.demo.democache.entity.Coffee;
import org.apache.ibatis.annotations.*;

import java.util.List;

//与@MapperScan二选一
@Mapper
public interface CoffeeMapper {

    //返回主键
    @Insert(value = "INSERT INTO t_coffee(name,price)" + "VALUES (#{name},#{price})")
    Long save(Coffee coffee);

    @Select("SELECT * FROM t_coffee WHERE id  = #{id}")
    Coffee findById(@Param("id") Long id);



    @Select("SELECT * FROM t_coffee ORDER BY  id")
    List<Coffee>  findAll();

    @Select("UPDATE  t_coffee (price = #{price}) ")
    void  update(Long price,Long id);


}
