package com.cxy.demo.transaction;

import com.cxy.demo.transaction.service.impl.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement(mode = AdviceMode.PROXY )
//除非使用 AspectJ 静态植入实现 AOP，否则@Transactional 在public方法才能生效\
// 因为动态代理,private 方法无法代理到
public class TransactionApplication implements CommandLineRunner {

    @Autowired
	private TestService testService;


	@Autowired
	private JdbcTemplate jdbcTemplate;



	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		try {
//			testService.insert();
//		} catch (Exception e) {
//			log.info("cxy {}",
//					jdbcTemplate
//							.queryForObject("SELECT COUNT(*) FROM test WHERE name='cxy'", Long.class));
//		}
//		try {
//			testService.insertThenRollback();
//		} catch (Exception e) {
//			log.info("cxy {}",
//					jdbcTemplate
//							.queryForObject("SELECT COUNT(*) FROM test WHERE name='bbb'", Long.class));
//
//		}

//		try {
//			testService.InvokeInsertThenRollback();
//		} catch (Exception e) {
//			log.info("cxy {}",
//					jdbcTemplate
//							.queryForObject("SELECT COUNT(*) FROM test WHERE name='bbb'", Long.class));
//
//		}
	}



}

