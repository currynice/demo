package com.cxy.demo.datasources.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 从数据源的Mybatis配置
 */
@Slf4j(topic = "Logger")
@Configuration
@MapperScan(basePackages="com.cxy.demo.datasources.dao2",sqlSessionFactoryRef = "sqlSessionFactorySlave", sqlSessionTemplateRef = "sqlSessionTemplateSlave")
public class MyBatisConfigSlave {

     @Resource(name="slave1")
    private DataSource slave;

    @Bean
    public SqlSessionFactory sqlSessionFactorySlave(){
        SqlSessionFactory sqlSessionFactorySlave = null;
        try{
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(slave);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/slave/*.xml"));
            sqlSessionFactorySlave = bean.getObject();
        }catch (Exception e){
            log.error("sqlSessionFactorySlave创建失败{}",e.getMessage());
        }
        return  sqlSessionFactorySlave;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplateSlave(){
        return new SqlSessionTemplate(sqlSessionFactorySlave());
    }
}
