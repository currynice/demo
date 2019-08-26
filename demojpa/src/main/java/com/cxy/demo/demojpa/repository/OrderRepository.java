package com.cxy.demo.demojpa.repository;

import com.cxy.demo.demojpa.entity.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order,Long> {
    List<Order> findByCustomerOrderById(String customer);

    List<Order> findByItems_Name(String name);


}
