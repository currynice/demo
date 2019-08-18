package com.cxy.demo.datasources.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 主数据源的Mybatis配置
 */
@Slf4j(topic = "Logger")
@Configuration
@MapperScan(basePackages="com.cxy.demo.datasources.dao",sqlSessionFactoryRef = "sqlSessionFactoryMaster", sqlSessionTemplateRef = "sqlSessionTemplateMaster")
public class MyBatisConfigMaster {

    @Autowired
    @Qualifier("master")
    private DataSource master;

    @Bean
    public SqlSessionFactory sqlSessionFactoryMaster(){
        SqlSessionFactory sqlSessionFactoryMaster = null;
        try{
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(master);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/master/*.xml"));
            sqlSessionFactoryMaster = bean.getObject();
        }catch (Exception e){
            log.error("sqlSessionFactoryMaster创建失败{}",e.getMessage());
        }
        return  sqlSessionFactoryMaster;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplateMaster(){
        return new SqlSessionTemplate(sqlSessionFactoryMaster());
    }
}
