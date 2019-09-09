package com.cxy.demo.demomybatis;

import com.cxy.demo.demomybatis.dao.CoffeeMapper;
import com.cxy.demo.demomybatis.entity.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cxy.demo.demomybatis.dao")
@Slf4j
public class DemoMybatisApplication implements CommandLineRunner{
	@Autowired
	private CoffeeMapper coffeeMapper;

	public static void main(String[] args) {
		SpringApplication.run(DemoMybatisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Coffee	espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"),20.00)).build();
	    Long id = 	coffeeMapper.save(espresso);
	    log.info("Coffee{} =>{}",id,espresso);

	    espresso = coffeeMapper.findById(id);
		log.info("Coffee{} =>{}",id,espresso);
	}
}
