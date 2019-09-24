package com.cxy.demo.democache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义主键,keyGenerator="myKeyGenerator"
 */
@Component
public class MyKeyGenerator implements KeyGenerator {

    /**
     * @param target 当前对象
     * @param method  请求的方法
     * @param params  方法参数
     * @return
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return method.getName()+":"+target.getClass().getName()+":"+params.toString();
    }
}
