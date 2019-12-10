package com.cxy.demo.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@Slf4j
public class ReactiveApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }

    private static final String KEY = "MENU";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;


    @Bean
    ReactiveStringRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveStringRedisTemplate(factory);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ReactiveHashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        //锁存器
        CountDownLatch cdl = new CountDownLatch(1);

        //从数据库查:5条
        List<Coffee> list = jdbcTemplate.query(
                "select * from t_coffee", (rs, i) ->
                        Coffee.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .price(rs.getLong("price"))
                                .build()
        );

        Flux.fromIterable(list)
                //单线程
                .publishOn(Schedulers.single())
                .doOnComplete(() -> log.info("list ok"))
                //元素异步转换为生产者
                .flatMap(c -> {
                    log.info("try to put {},{}", c.getName(), c.getPrice());
                    return hashOps.put(KEY, c.getName(), c.getPrice().toString());
                })
                .doOnComplete(() -> log.info("set ok"))
                .concatWith(redisTemplate.expire(KEY, Duration.ofMinutes(1)))
                .doOnComplete(() -> log.info("expire ok"))
                .onErrorResume(e -> {
                    log.error("exception {}", e.getMessage());
                    return Mono.just(Boolean.FALSE);
                })
                //消费
                .subscribe(b -> log.info("Boolean: {}", b),
                        e -> log.error("Exception {}", e.getMessage()),
                        () -> cdl.countDown());//完成后减少锁存器
        //验证是不是异步
        log.info("Waiting");
        cdl.await();
    }


}
