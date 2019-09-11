package com.cxy.demo.demomybatis.dao;


import com.cxy.demo.demomybatis.entity.Coffee;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

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


    //start分页
    @Select("SELECT * FROM t_coffee ORDER BY  id")
    List<Coffee>  findAll();




    /**
     * RowBounds{侵入性最小}
     * @param rowBounds
     * @return
     */
    @Select("SELECT * FROM t_coffee ORDER BY  id")
    List<Coffee>  findWithRowBounds(RowBounds rowBounds);

    @Select("SELECT * FROM t_coffee ORDER BY  id")
    List<Coffee>  findWithPageRowBounds(PageRowBounds pageRowBounds);

    /**
     * 参数方法
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Select("SELECT * FROM t_coffee ORDER BY  id")
    List<Coffee>  findWithMethodsArguments(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);


    //end 分页
}
