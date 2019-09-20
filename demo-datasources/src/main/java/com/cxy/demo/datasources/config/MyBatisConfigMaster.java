package com.cxy.demo.datasources.config;

import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
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
import java.util.Properties;

/**
 * 主数据源的Mybatis配置
 * @MapperScan：扫描 Mapper 接口并容器管理。
 * sqlSessionFactoryRef 表示定义一个唯一 SqlSessionFactory 实例。
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
            //分页插件
            Interceptor interceptor = new PageInterceptor();
            Properties properties = new Properties();
            //数据库
            properties.setProperty("helperDialect", "mysql");
            //是否将参数offset作为PageNum使用
            properties.setProperty("offsetAsPageNum", "true");
            //是否进行count查询
            properties.setProperty("rowBoundsWithCount", "true");
            //是否分页合理化
            //pageNum<=0 时会查询第一页，pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。
            properties.setProperty("reasonable", "false");
            interceptor.setProperties(properties);
            bean.setPlugins(new Interceptor[] {interceptor});

            sqlSessionFactoryMaster = bean.getObject();
        }catch (Exception e){
            log.error("sqlSessionFactoryMaster创建失败{}",e.getMessage());
        }
        return  sqlSessionFactoryMaster;
    }

    //管理MyBatis的SqlSession
    @Bean
    public SqlSessionTemplate sqlSessionTemplateMaster(){
        return new SqlSessionTemplate(sqlSessionFactoryMaster());
    }


}
