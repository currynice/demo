package com.cxy.demoaop.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author chengxinyu
 * @version 1.1.0
 * @description RedisConfig TODO 更新序列化白名单范围
 *  @ConditionalOnClass参考
 *  https://blog.csdn.net/xcy1193068639/article/details/81517456
 * @date 2019年06月06日15:06
 **/
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    //默认过期时间 :10s
    private Duration EXPIRE_TIME = Duration.ofMinutes(10);




    /**主键策略 ->like :com.cxy.admin methodName paramNames*/
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object object : params) {
                    sb.append(object.toString());
                }
                return sb.toString();
            }
        };
    }


    /**RedisTemplate 非事务 */
    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        //StringRedisTemplate  <-  - > RedisTemplate<String,String>
        // StringRedisTemplate template = new StringRedisTemplate(factory);  due to RedisTemplate<String,Object>
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //连接factory
        template.setConnectionFactory(factory);
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

    /**RedisTemplate 事务 */
    @Bean(name = "TredisTemplate")
    public RedisTemplate<String,Object> redisTemplate2(RedisConnectionFactory factory) {
        //StringRedisTemplate  <-  - > RedisTemplate<String,String>
        // StringRedisTemplate template = new StringRedisTemplate(factory);  due to RedisTemplate<String,Object>
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //连接factory
        template.setConnectionFactory(factory);
        setSerializer(template);
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private  void setSerializer( RedisTemplate<String,Object> template){
        // key 序列化
        RedisSerializer stringSerializer = new StringRedisSerializer();
        /*Javabean json 间相互转换  序列化反序列化redis的value 默认JDK*/
        //Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        /* redis 转换为xml*/
        // OxmSerializer oxmSerializer = new OxmSerializer();
//        ObjectMapper om = new ObjectMapper();
//        //指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        //序列化输入类型，非final ,Integer String 将报错
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);

        //FastJson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // 全局开启AutoType，不建议使用
        // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept("com.cxy.domain");
//        ParserConfig.getGlobalInstance().addAccept("me.zhengjie.modules.system.service.dto");
//        ParserConfig.getGlobalInstance().addAccept("me.zhengjie.modules.system.domain");
//        ParserConfig.getGlobalInstance().addAccept("me.zhengjie.modules.quartz.domain");
//        ParserConfig.getGlobalInstance().addAccept("me.zhengjie.modules.monitor.domain");
//        ParserConfig.getGlobalInstance().addAccept("me.zhengjie.modules.security.security");
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
    }





}
