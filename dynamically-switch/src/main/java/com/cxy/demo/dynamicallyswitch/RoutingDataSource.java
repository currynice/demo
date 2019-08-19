package com.cxy.demo.dynamicallyswitch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切换数据源(用于方法)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoutingDataSource {
    //数据源名
    String value() default DataSources.MASTER_DB;

    //操作
    String operate() default "";
}
