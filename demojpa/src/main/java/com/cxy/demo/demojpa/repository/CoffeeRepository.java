package com.cxy.demo.demojpa.repository;

import com.cxy.demo.demojpa.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
}
