package com.cxy.demoaop;

import com.cxy.demoaop.aspect.logwithnotannotation.model.Coffee;
import com.cxy.demoaop.aspect.logwithnotannotation.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.cxy.demoaop.aspect.logwithnotannotation.repository")
//使得Aop生效
// proxyTargetClass控制aop的具体实现方式,默认使用java Proxy,true:cglib
//exposeProxy控制代理的暴露方式,解决内部调用不能使用代理的场景，默认为false)
@EnableAspectJAutoProxy
public class DemoAopApplication implements CommandLineRunner {

    @Autowired
    private CoffeeService coffeeService;

    public static void main(String[] args) {
        SpringApplication.run(DemoAopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Coffee> iLike = coffeeService.findOneCoffee("mocha");
        if(iLike.isPresent()){
            System.out.println("库存还在");
        }
    }
}
