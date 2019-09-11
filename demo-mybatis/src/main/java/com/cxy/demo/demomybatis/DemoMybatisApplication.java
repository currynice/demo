package com.cxy.demo.demomybatis;

import com.cxy.demo.demomybatis.dao.CoffeeMapper;
import com.cxy.demo.demomybatis.entity.Coffee;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

//https://pagehelper.github.io/docs/howtouse/#2-%E9%85%8D%E7%BD%AE%E6%8B%A6%E6%88%AA%E5%99%A8%E6%8F%92%E4%BB%B6
@SpringBootApplication
@MapperScan("com.cxy.demo.demomybatis.dao")
@Slf4j
public class DemoMybatisApplication implements CommandLineRunner{
	@Autowired
	private CoffeeMapper coffeeMapper;


	public static void main(String[] args) {
		SpringApplication.run(DemoMybatisApplication.class, args);
	}

	//
	private void rowBounds(){
		coffeeMapper.findWithRowBounds(new RowBounds(1,3)).forEach(System.out::println);
		//默认无法查询总数,PageRowBounds可以得到总数total
		PageRowBounds pageRowBounds = new PageRowBounds(1,3);
		coffeeMapper.findWithPageRowBounds(pageRowBounds).forEach(System.out::println);
		System.out.println("pageRowBounds获得总数"+pageRowBounds.getTotal());

	}

	//mapper接口
	private void mapperInterface(){
		//只对紧跟着的第一个查询生效,获取第一页，每页3条
		PageHelper.startPage(1,3);
		List<Coffee> coffees = coffeeMapper.findAll();
		coffees.forEach(System.out::println);
		//分页时，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>,或者PageInfo
		System.out.println(((Page)coffees).getTotal());
		System.out.println("style1分页结束");
		PageHelper.offsetPage(0,3);
		List<Coffee> coffees2 = coffeeMapper.findAll();
		coffees2.forEach(System.out::println);
		//用PageInfo把结果封装起来
		PageInfo pageInfo = new PageInfo(coffees2);
		System.out.println("分页信息"+pageInfo);
		System.out.println("style2分页结束");

	}

	//方法参数(support-methods-arguments=true)
	private void methodsArguments(){
		coffeeMapper.findWithMethodsArguments(1,5).forEach(System.out::println);
	}


	@Override
	public void run(String... args) throws Exception {
//		Coffee	espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"),20.00)).build();
//	    Long id = 	coffeeMapper.save(espresso);
//	    log.info("Coffee{} =>{}",id,espresso);
//
//	    espresso = coffeeMapper.findById(id);
//		log.info("Coffee{} =>{}",id,espresso);
		//rowBounds();
		mapperInterface();
		//methodsArguments();

	}
}
