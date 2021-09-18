package com.cxy.demo.demolog.annotation;

import java.lang.annotation.*;

/**
 * 动态操作日志模板
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecord {
    //操作日志成功的文本模板（SpEL表达式），必填
    String success();

    //操作日志失败的文本模板，非必填
    String fail() default "";

    //操作日志执行人(操作日志很重要的信息)，非必填
    // 普通Web 应用中用户信息都是保存在一个线程上下文的静态方法中，
    // 假定获取当前登陆用户的方式是 UserContext.getCurrentUser()）。
    // 所以，默认 operator = "#{T(com.cxy.demo.demolog.UserContext).getCurrentUser()}"
    String operator() default "#{T(com.cxy.demo.demolog.UserContext).getCurrentUser()}";

    //操作日志绑定的业务标识(方便查询某个业务id的所有操作)，必填
    String bizNo();

    //操作日志类型，非必填
    String category() default "";

    //拓展参数，记录操作日志的修改详情，非必填
    String detail() default "";

    //记录条件，非必填
    String condition() default "";
}
