package com.cxy.demo.datasources.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 根据两个数据源，配置主从两个JdbcTemplate,存在两个DataSource,默认使用类型查找，会报错，因此加上@Qualifier注解
 */
@Configuration
public class JdbcTemplateConfig {


    @Bean
    public JdbcTemplate jdbcTemplateMaster(@Qualifier("master") DataSource master){
        return new JdbcTemplate(master);
    }


    @Bean
    public JdbcTemplate jdbcTemplateSlave1(@Qualifier("slave1")DataSource slave1){
        return new JdbcTemplate(slave1);
    }
}
