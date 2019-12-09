package com.cxy.demo.demoredis.redis.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DemoRedisRepository extends CrudRepository<DataCache,Long> {

    Optional<DataCache> findByName(String name);

}
