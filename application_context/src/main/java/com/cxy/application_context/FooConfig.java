package com.cxy.application_context;

import com.cxy.application_context.aop.AfterReturningAspect;
import com.cxy.application_context.benas.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class FooConfig {
    @Bean
    public TestBean testBeanX() {
        return new TestBean("foo");
    }

    @Bean
    public TestBean testBeanY() {
        return new TestBean("foo");
    }

    @Bean
    public AfterReturningAspect fooAspect() {
        return new AfterReturningAspect();
    }
}
