package com.cxy.demo.demoredis.redis.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

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
    @Bean(name = "multiTemplate")
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
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        /* redis 转换为xml*/
         OxmSerializer oxmSerializer = new OxmSerializer();
        ObjectMapper om = new ObjectMapper();
        //指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        //序列化输入类型，非final ,Integer String 将报错
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);



        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
    }





    @Bean(name = "jdkSerializer")
    public RedisSerializer jdkSerializationRedisSerializer(){
        return new JdkSerializationRedisSerializer();
    }

}
