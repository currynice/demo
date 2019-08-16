package com.cxy.pure.config;


import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    private DataSource dataSource;


    @Bean(destroyMethod = "close")
    public DataSource dataSource()throws Exception{
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "org.h2.Driver");
        properties.setProperty("url", "jdbc:h2:mem:testdb");
        properties.setProperty("username", "sa");
        return BasicDataSourceFactory.createDataSource(properties);
    }

    @Bean
    public PlatformTransactionManager transactionManager()throws Exception{
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 得到所有bean
     * @throws SQLException
     */
    private static void showBeans(ApplicationContext applicationContext){
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    /**
     * 输出数据源配置情况
     * @param applicationContext
     */
    private static void showDataSourceConfig(ApplicationContext applicationContext) throws SQLException {
        DataSourceConfig config = applicationContext.getBean("dataSourceConfig",DataSourceConfig.class);
        config.showDataSource();
    }


    public void showDataSource()throws SQLException{
        System.out.println(dataSource.toString());
        Connection connection = dataSource.getConnection();
        System.out.println(connection.toString());
        connection.close();
    }

    /**
     *
     */
    public static void main(String []args) throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        showBeans(applicationContext);
        showDataSourceConfig(applicationContext);
    }


}
