package com.cxy.demo.demoredis.redis.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Slf4j
public class RedisRepositoryService {

    @Autowired
    private DemoRedisRepository demoRedisRepository;

    @Autowired
    private DemoJpaRepository demoJpaRepository;


    //从缓存存储中查
    public Data2 findByName(String  name) {
        Optional<DataCache> cache = demoRedisRepository.findByName(name);
        if (cache.isPresent()) {
            DataCache data1 = cache.get();
            Data2 data = Data2.builder()
                    .name(data1.getName())
                    .subject(data1.getSubject())
                    .build();
            log.info("data:{} found in cache.", data1);
            return data;
        } else {
            //不存在,从jpa查数据库
            Optional<Data2> raw = findOne(name);
            raw.ifPresent(c -> {
                DataCache cacheData = DataCache.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .subject(c.getSubject())
                        .build();
                log.info("Save data {} to cache.", cacheData);
                demoRedisRepository.save(cacheData);
            });
            return raw.isPresent()?raw.get():null;
        }
    }




    public Optional<Data2> findOne(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());

        return demoJpaRepository.findOne(
                Example.of(Data2.builder().name(name).build(), matcher));

    }
}
