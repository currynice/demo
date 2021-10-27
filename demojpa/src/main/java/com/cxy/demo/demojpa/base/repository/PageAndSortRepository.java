package com.cxy.demo.demojpa.base.repository;

/**
 * Description:   </br>
 * Date: 2021/3/21 20:49
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

import com.cxy.demo.demojpa.base.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageAndSortRepository extends JpaRepository<User,Long> {

    //根据分页参数查询User，返回带分页信息的Page(从page 0开始) 对象
//    Page<User> findByNameStartingWith(String name, Pageable pageable);


    //类似游标分页
//    Slice<User> findByNameStartingWith(String name, Pageable pageable);


    //限制查询结果
    List<User> findTop3ByNameStartingWith(String name,Pageable pageable);
}

