package com.cxy.demo.democache;

import cn.hutool.core.lang.Dict;
import com.cxy.demo.democache.entity.Coffee;
import com.cxy.demo.democache.service.CoffeeService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.cxy.demo.democache.dao")
@RestController
public class DemoCacheApplication {

    @Autowired
    private CoffeeService coffeeService;


    @RequestMapping("/getById/{id}")
    public Coffee getById(@PathVariable("id") String id){
        return coffeeService.getSingleCoffee(id);
    }

    @RequestMapping("/all")
    public Dict getAll(){
        return Dict.create().set("coffees", coffeeService.getAll());
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoCacheApplication.class, args);
    }

}
