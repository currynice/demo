package com.cxy.demoaop.aspect;


import java.lang.annotation.*;

/**
 * 标记功能
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodMeasure {
}
