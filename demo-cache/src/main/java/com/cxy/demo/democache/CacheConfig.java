package com.cxy.demo.democache;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


/**
 *
 * @description 配置缓存spring-data-cache key
 * @author cxy
 * @date 2019年10月12日下午4:57:38
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 为缓存设置过期时间
     */
    @Bean(name="ttlRedisCacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(2))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();
        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory);
        return builder.transactionAware().cacheDefaults(config).build();

    }



}
