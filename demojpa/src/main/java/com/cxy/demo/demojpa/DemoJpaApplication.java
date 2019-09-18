package com.cxy.demo.demojpa;

import com.cxy.demo.demojpa.entity.Coffee;
import com.cxy.demo.demojpa.entity.Order;
import com.cxy.demo.demojpa.entity.OrderState;
import com.cxy.demo.demojpa.repository.CoffeeRepository;
import com.cxy.demo.demojpa.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j(topic = "Logger")
public class DemoJpaApplication implements ApplicationRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }

    public void init(){
        Coffee espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"),20.0)).build();
        coffeeRepository.save(espresso);
        log.info("espresso:{}",espresso);

        Coffee latte = Coffee.builder().name("latte").price(Money.of(CurrencyUnit.of("CNY"),30.0)).build();
        coffeeRepository.save(latte);
        log.info("latte:{}",latte);

        Coffee maxExperienceCoffee = coffeeRepository.getMaxExperorxcer();
        log.info("maxExperienceCoffee:{}",maxExperienceCoffee);

        Order order = Order.builder().customer("cxy").items(Collections.singletonList(espresso)).state(OrderState.PAID).build();
        orderRepository.save(order);
        log.info("order:{}",order);

        Order order2 = Order.builder().customer("wang").items(Arrays.asList(espresso,latte)).state(OrderState.CANCELLED).build();
        orderRepository.save(order2);
        log.info("order2:{}",order2);

    }

    public void select(){

        coffeeRepository.findAll(Sort.by(Sort.Direction.DESC,"id")).forEach(c-> log.info("Loading:{}",c));
        List<Order> list = orderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedOrderId(list));

        list = orderRepository.findByCustomerOrderById("cxy");
        log.info("findByCustomerOrderById: {}", getJoinedOrderId(list));

        // 不开启事务会因为没Session而报LazyInitializationException
        list.forEach(o -> {
            log.info("Order {}", o.getId());
            o.getItems().forEach(i -> log.info("  Item {}", i));
        });

        list = orderRepository.findByItems_Name("latte");
        log.info("findByItems_Name: {}", getJoinedOrderId(list));


    }
    private String getJoinedOrderId(List<Order> list) {
        return list.stream().map(o -> o.getId().toString())
                .collect(Collectors.joining(","));
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        init();
        select();
    }
}
