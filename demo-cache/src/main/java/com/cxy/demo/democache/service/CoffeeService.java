package com.cxy.demo.democache.service;


import com.cxy.demo.democache.MyKeyGenerator;
import com.cxy.demo.democache.dao.CoffeeMapper;
import com.cxy.demo.democache.entity.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Autowired
    private MyKeyGenerator myKeyGenerator;

    public List<Coffee> getAll(){

        return coffeeMapper.findAll();
    }

    /**
     * @Cacheable 该方法缓存，value表示缓存的名字,key为方法参数，可自定义配置,implements{@link javax.crypto.KeyGenerator},值为方法返回参数,condition表示条件限定
     *  @Cacheable(key="#id)
     *  @Cacheable(key="#coffee.id)
     *  key也可以使用root对象的属性，#root.methodName(当前方法名);method(方法对象);caches(使用的缓存);target(被调用的对象);targetClass;args(方法参数数组)
     * @param id
     * @return
     */
    @Cacheable(value = "singleCoffee_cache",keyGenerator = "myKeyGenerator",condition = "#id.equals('2')")
    public Coffee getSingleCoffee(String id){

        return coffeeMapper.findById(Long.valueOf(id));
    }


    //执行方法将结果缓存，会覆盖之前的
    @CachePut
    public void update(String price,String id){
        coffeeMapper.update(Long.valueOf(price),Long.valueOf(id));
    }

    /**
    @CacheEvict
    allEntries  是否删除所有缓存,默认false(仅删除当前key的缓存),不允许true的同时设置key
    beforeInvocation 是否在方法执行之前移除缓存中的数据，默认false ，即在方法执行之后移除缓存中的敖据
     */




}
