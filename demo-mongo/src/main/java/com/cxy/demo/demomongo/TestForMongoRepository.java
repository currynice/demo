package com.cxy.demo.demomongo;

import com.cxy.demo.demomongo.entity.Coffee;
import com.cxy.demo.demomongo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;


/**
 * Description:   <br>
 * Date: 2020/5/11 12:04  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Component
@Slf4j
//@EnableMongoRepositories
public class TestForMongoRepository implements ApplicationRunner {
    //类似JdbcTemplate
    @Autowired
    private CoffeeRepository coffeeRepository;






    @Override
    public void run(ApplicationArguments args) throws Exception {


        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        coffeeRepository.insert(Arrays.asList(espresso, latte));
        coffeeRepository.findAll(Sort.by("name"))
                .forEach(c -> log.info("Saved Coffee {}", c));

        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
        latte.setUpdateTime(new Date());
        coffeeRepository.save(latte);
        coffeeRepository.findByName("latte")
                .forEach(c -> log.info("Coffee {}", c));

//        coffeeRepository.deleteAll();
    }
}
