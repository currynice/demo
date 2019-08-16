package com.cxy.demo.datasources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * 多数据源两种方式
 *   一：主库dataSource使用springboot自动配置，并声明为@Primary
 *   二：排除exclude:DataSourceAutoConfiguration,DataSourceTransactionManagerAutoConfiguration,JdbcTemplateAutoConfiguration，全部使用手动配置
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
@Slf4j(topic = "Logger")
public class DemoDatasourcesApplication implements CommandLineRunner{

        //主数据源
    @Autowired
    private DataSource dataSource;

    //从数据源
    @Autowired
    private DataSource salve1;


    @Autowired
    private PlatformTransactionManager masterTxManager;


    @Autowired
    private PlatformTransactionManager salve1TxManager;

    public static void main(String[] args) {
        SpringApplication.run(DemoDatasourcesApplication.class, args);
    }

    /**
     * 查看数据源记忆连接信息
     * @throws SQLException
     */
    public void showConnection() throws SQLException {
        log.info("主数据源{}",dataSource.toString());
        log.info("从数据源{}",salve1.toString());
        log.info("主数据源连接信息{}",dataSource.getConnection().toString());
        log.info("从数据源连接信息{}",salve1.getConnection().toString());
    }

    /**
     * 查看数据源记忆连接信息
     * @throws SQLException
     */
    public void showTransactionInfo() {
        log.info("主事务管理{}",masterTxManager.toString());
        log.info("从事务管理{}",salve1TxManager.toString());
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showTransactionInfo();
    }
}
