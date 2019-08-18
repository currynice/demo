package com.cxy.demo.datasources.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@Slf4j(topic = "Logger")
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "slave.datasource")
    public DataSourceProperties slaveProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "master.datasource")
    public DataSourceProperties masterProperties(){
        return new DataSourceProperties();
    }

    /**
     * 主数据源
     * @return
     */
    //destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
    @Bean(destroyMethod ="close")
    public DataSource master(){
        return masterProperties().initializeDataSourceBuilder().build();

    }



    /**
     * 从数据源1
     * @return
     */
    @Bean(destroyMethod ="close")
    public DataSource slave1(){
        return slaveProperties().initializeDataSourceBuilder().build();
    }
}
