package com.cxy.demoaop.aspect;


import cn.hutool.core.util.StrUtil;
import com.cxy.demoaop.exceptionhandler.LimitAccessException;
import com.cxy.demoaop.util.IPUtils;
import com.cxy.demoaop.util.RequestHolder;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;



@Aspect
@Component("limitAspect")
@Slf4j(topic = "Logger")
public class LimitAspect {
    /**使用Lua脚本,原子性,无需考虑事务*/
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RequestHolder requestHolder;

    //切入点
    @Pointcut(value = "within(com.cxy.demoaop.controller..*)&&@annotation(com.cxy.demoaop.aspect.Limit)")
    public void  getPointCut(){
    }

    @Around(value = "getPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Limit limit = methodSignature.getMethod().getAnnotation(Limit.class);
        LimitEnum limitType = limit.limitObject();
        String key;
        HttpServletRequest thisRequest = requestHolder.getHttpServletRequest();

        switch (limitType){
                    case IP:
                        key = IPUtils.getIP(thisRequest);
                        break;
                    case USER_ID: //todo  获取到请求带的参数
                         key = "test-id";
                         break;
                    default:
                        key= limit.key();
                        break;
                }

        /**不可变集合*/
        ImmutableList keys = ImmutableList.of(StrUtil.join("-",limit.prefix() , key, thisRequest.getRequestURI().replaceAll("/","-")));
        String script = buildScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(script, Number.class);
        String sha1Code = redisScript.getSha1();
        Number count = (Number) redisTemplate.execute(redisScript, keys, limit.count(), limit.period());

        if (null != count && count.intValue() <= limit.count()) {
            log.info("第{}次访问key为 {}，描述为 [{}] 的接口", count, keys,limit.name());
            //继续执行
            return joinPoint.proceed();
        } else {
            throw new LimitAccessException("访问次数受限制");
        }
    }

    /**
     * 限流脚本
     * refer to:https://blog.csdn.net/u011489043/article/details/78820285
     */
    private String buildScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }






}
