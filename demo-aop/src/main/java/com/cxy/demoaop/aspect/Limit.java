package com.cxy.demoaop.aspect;

import java.lang.annotation.*;

/**
 * 限制访问注解 todo 初始化可pass次数 于redis-4.0 redis-cell 限流模块
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {
    // 资源名称，用于描述接口功能
    String name() default "";

    // 资源 key //限制ip时，是StringUtils.getIP(request)
    String key() default "";

    // key prefix
    String prefix() default "";

    // 时间 单位秒
    int period() default -1;

    // 限制访问次数
    int count() default -1;

    // 限制类型(默认为用户id)
    LimitEnum limitObject() default LimitEnum.USER_ID;
}
