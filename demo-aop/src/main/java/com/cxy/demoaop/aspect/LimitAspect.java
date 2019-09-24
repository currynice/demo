package com.cxy.demoaop.aspect;


import cn.hutool.core.util.StrUtil;
import com.cxy.demoaop.exceptionhandler.LimitAccessException;
import com.cxy.demoaop.util.IPUtil;
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
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局控流，适用于分布场景
 * redis incr配合lua脚本的计数器
 *
 * todo 保障限流接口可用
 * 1.本地缓存所谓的“本地批量预取”(AtomicLong)，是指让使用限流服务的业务进程，每次从从远程资源预取多个令牌在本地缓存，处理限流逻辑时先从本地缓存中读，不够再去获取,缺点，不够精确，访问次数可能超出设定值
 */
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
                        key = IPUtil.getIP(thisRequest);
                        break;
                    case USER_ID: //todo  获取到请求带的参数
                         //key = "test-id";
                         key = joinPoint.getArgs()[0].toString();
                         break;
                    default:
                        key= limit.key();
                        break;
                }

        /**不可变集合*/
        ImmutableList keys = ImmutableList.of(StrUtil.join("-",limit.prefix() , key, thisRequest.getRequestURI().replaceAll("/","-")));
        String script = buildScript();
        RedisScript<Number> redisScript = RedisScript.of(script, Number.class);
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
     * 限流脚本,
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


    //https://www.jianshu.com/p/8cc17c8e8cac
    private String buildLimitQPSScript(){
       //操作的 Redis Key
        return "local rate_limit_key = KEYS[1]"+
        // 每秒最大的 QPS 许可数
        "\nlocal max_permits = ARGV[1]"+
        //此次申请的许可数
         "\nlocal incr_by_count_str = ARGV[2]"+
          //当前已用的许可数
        "\nlocal currentStr = redis.call('get', rate_limit_key)"+
        "\nlocal current = 0"+
           //这里返回的是type个"boolean"哦
           "\nif currentStr then"+
         "\ncurrent = tonumber(currentStr)"+
          "\nend"+
            //-- 剩余可分发的许可数(最大-已用)
         "\nlocal remain_permits = tonumber(max_permits) - current"+
           "\nlocal incr_by_count = tonumber(incr_by_count_str)"+
           //如果可分发的许可数小于申请的许可数，只能申请到可分发的许可数
           "\nif remain_permits < incr_by_count then"+
            "\nincr_by_count = remain_permits"+
                "\nend"+
        //将此次实际申请的许可数加到 Redis Key 里面
        "\nlocal result = redis.call('incrby', rate_limit_key, incr_by_count)"+
           //初次操作 Redis Key 设置 1 秒的过期,
        "\nif result == incr_by_count then"+
                "\nredis.call('expire', rate_limit_key, 1)"+
                "\nend"+

        // -- 返回实际申请到的许可数
        "\nreturn incr_by_co";

    }





    //另外，对于突发流量和微服务间依赖复杂导致的雪崩问题，就需要通过手动或自动熔断机制来避免整体服务不可用
    //todo 自动熔断机制中，确认 Fail-fast 时的熔断阈值(单位时间内耗时超过1s的比例超过50%)






}
