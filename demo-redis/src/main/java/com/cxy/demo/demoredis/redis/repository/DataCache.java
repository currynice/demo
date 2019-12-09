package com.cxy.demo.demoredis.redis.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

//ttl:60
//给data-redis-repository用的
@RedisHash(value = "test_cache01",timeToLive = 60)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataCache implements Serializable {
    @Id
    private Long id;


    @Indexed
    private String name;

    private String subject;
}
