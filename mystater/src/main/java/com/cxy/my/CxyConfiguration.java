package com.cxy.my;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//开启配置
@EnableConfigurationProperties(CxyProperties.class)//开启使⽤配置参数注解
@ConditionalOnClass(InitService.class)//存在InitService.class时初始化该配置类
@ConditionalOnProperty//存在对应配置信息时初始化该配置类
        (prefix	= "cxy",//存在配置前缀cxy
         value	= "enabled",//开启
         matchIfMissing	= true//缺失检查
        )
public class CxyConfiguration {

    //配置文件对象
    @Autowired
    private CxyProperties cxyProperties;

    /**
     * Spring条件注解@ConditionalOnXxx:
     * 	具有Xxx条件
     *  @ConditionalOnBean: 当SpringIoc容器内存在指定Bean的条件
     *  @ConditionalOnClass: 当SpringIoc容器内存在指定Class的条件
     *  @ConditionalOnExpression: 基于SpEL表达式作为判断条件
     *  @ConditionalOnJava： 基于JVM版本作为判断条件
     *  @ConditionalOnJndi: 在JNDI存在时查找指定的位置
     *  @ConditionalOnMissingBean: 当SpringIoc容器内不存在指定Bean的条 件
     *  @ConditionalOnMissingClass: 当SpringIoc容器内不存在指定Class的条件
     *  @ConditionalOnNotWebApplication: 当前项⽬不是Web项⽬的条件
     *  @ConditionalOnProperty: 指定的属性是否有指定的值
     *  @ConditionalOnResource: 类路径是否有指定的值
     *  @ConditionalOnSingleCandidate: 当指定Bean在SpringIoc容器内只有⼀个，或者虽然有多个 但是是@Primary的
     *  @ConditionalOnWebApplication: 当前项⽬是Web项⽬的条件
     * 以上都是元注解	@Conditional	演变⽽来的，根据不⽤的条件对应创建以上的具体条件注解。
     *
     */

    /**
     *	根据条件判断不存在InitService时初始化新bean到SpringIoc
     *	@return
     */
    @Bean
    @ConditionalOnMissingBean(InitService.class)//缺失InitService实体bean时，初始化InitService并添加到SpringIoc
    public InitService initService(){
        System.out.println("InitService not found ,will create new Bean");
        InitService initService = new InitService();
        initService.setInitMsg(cxyProperties.getInitMsg());
        initService.setShow(cxyProperties.isShow());
        return initService;
    }






}
