package com.cxy.demo.datasources.config;




import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
@Slf4j(topic = "Logger")
public class DataSourceConfig {

    /**
     * 主数据源
     * @return
     */
    //destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
    @Bean(destroyMethod ="close")
    public DataSource master(){
        DataSourceProperties salveProperties = masterProperties();
        return salveProperties.initializeDataSourceBuilder().build();
    }



    /**
     * 从数据源1
     * @return
     */
    @Bean(destroyMethod ="close")
    public DataSource salve1(){
        DataSourceProperties salveProperties = salveProperties();
        return salveProperties.initializeDataSourceBuilder().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "slave.datasource")
    public DataSourceProperties salveProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties masterProperties(){
        return new DataSourceProperties();
    }
}
