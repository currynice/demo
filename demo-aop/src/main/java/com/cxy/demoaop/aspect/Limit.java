package com.cxy.demoaop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限制访问注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
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
