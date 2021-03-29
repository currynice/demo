//package com.cxy.demo.demojpa.repository;
//
//import com.cxy.demo.demojpa.entity.Coffee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
//
//    @Query(value = "SELECT  *  FROM t_menu where price =(SELECT MAX(price)FROM t_menu)" ,nativeQuery = true)
//    Coffee getMaxExperorxcer();
//}
