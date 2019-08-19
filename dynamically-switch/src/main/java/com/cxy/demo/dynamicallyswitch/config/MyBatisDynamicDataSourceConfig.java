package com.cxy.demo.dynamicallyswitch.config;

/**
 * 动态MyBatis配置
 */
import com.cxy.demo.dynamicallyswitch.DataSources;
import com.cxy.demo.dynamicallyswitch.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyBatisDynamicDataSourceConfig {

    @Autowired
    @Qualifier(DataSources.MASTER_DB)
    private DataSource master;

    @Autowired
    @Qualifier(DataSources.SLAVE_DB)
    private DataSource slave;


    @Bean(name ="dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //默认
       dynamicDataSource.setDefaultTargetDataSource(master);

       //配置多数据源
        Map<Object,Object> dbMap =  new HashMap<>();
        dbMap.put(DataSources.MASTER_DB,master);
        dbMap.put(DataSources.SLAVE_DB,slave);
        dynamicDataSource.setTargetDataSources(dbMap);
        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionFactoryBean")
    @ConfigurationProperties(prefix ="mybatis")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource());
        //对应mybatis.type-aliases-package配置
        //bean.setTypeAliasesPackage("com.cxy.dataSource.pojo");
        //对应mybatis.mapper-locations配置  用注解了
       // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        //开启驼峰映射
        //bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }


    /**
     * sqlSession模版，用于配置自动扫描pojo实体类
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "testSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactoryBean") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //关于事务管理器，不管是JPA还是JDBC等都实现自接口 PlatformTransactionManager
    // 如果你添加的是 spring-boot-starter-jdbc 依赖，框架会默认注入 DataSourceTransactionManager 实例。
    //在Spring容器中，我们手工注解@Bean 将被优先加载，框架不会重新实例化其他的 PlatformTransactionManager 实现类。
    //MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源
//        // 与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。

}
