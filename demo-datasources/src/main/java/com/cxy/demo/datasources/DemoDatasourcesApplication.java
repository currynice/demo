package com.cxy.demo.datasources;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * 多数据源两种方式
 *   一：主库dataSource使用springboot自动配置，并声明为@Primary
 *   二：排除exclude:DataSourceAutoConfiguration,DataSourceTransactionManagerAutoConfiguration,JdbcTemplateAutoConfiguration，全部使用手动配置
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, PageHelperAutoConfiguration.class})
@Slf4j(topic = "Logger")
public class DemoDatasourcesApplication implements CommandLineRunner{

        //主数据源
    @Autowired
    private DataSource master;

    //从数据源
    @Autowired
    private DataSource slave1;

    

    public static void main(String[] args) {
        SpringApplication.run(DemoDatasourcesApplication.class, args);
    }

    /**
     * 查看数据源记忆连接信息
     * @throws SQLException
     */
    public void showConnection() throws SQLException {
        log.info("主数据源{}",master.toString());
        log.info("从数据源{}",slave1.toString());
        log.info("主数据源连接信息{}",master.getConnection().toString());
        log.info("从数据源连接信息{}",slave1.getConnection().toString());
    }



    @Override
    public void run(String... args) throws Exception {
        showConnection();
    }
}
