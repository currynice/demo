//package com.cxy.demo.datasources.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//
//import javax.sql.DataSource;
//
//@Configuration
//@Slf4j(topic = "Logger")
//public class PlatformManagerConfig {
//
//    //主数据源
//    @Autowired
//    private DataSource master;
//
//    //从数据源
//    @Autowired
//    private DataSource slave1;
//
//
//    /**
//     * 从数据源1
//     * @return
//     */
//    @Bean
//    public PlatformTransactionManager masterTxManager(){
//        return new DataSourceTransactionManager(master);
//    }
//
//
//    @Bean
//    public PlatformTransactionManager slave1TxManager(){
//        return new DataSourceTransactionManager(slave1);
//    }
//}
