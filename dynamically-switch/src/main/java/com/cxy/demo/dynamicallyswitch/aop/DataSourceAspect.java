package com.cxy.demo.dynamicallyswitch.aop;


import com.cxy.demo.dynamicallyswitch.RoutingDataSource;
import com.cxy.demo.dynamicallyswitch.change.DataSourcesHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "Logger")
public class DataSourceAspect  {

    @Autowired
    private DataSourcesHolder dataSourcesHolder;



    /**
     * 有RoutingDataSource注解的方法/参数
     * "within(com.cxy..*) && @annotation(routingData)"
     */
    @Before("@annotation(routingDataSource)")
    public void beforeMethod(JoinPoint joinPoint, RoutingDataSource routingDataSource){
        String datasourceName = routingDataSource.value();
        dataSourcesHolder.setSources(datasourceName);
        log.info("当前操作:"+routingDataSource.operate());
        log.info("当前数据源:"+datasourceName);
        log.info("对象:"+joinPoint.getSignature().toString());//对象
        log.info("参数:"+parseParams(joinPoint.getArgs()));
    }


    //方法返回之后,清除数据源
    @AfterReturning(pointcut="@annotation(routingDataSource)")
    public void afterReturnningMethod(JoinPoint joinPoint ,RoutingDataSource routingDataSource){
        dataSourcesHolder.clear();
    }


    //错误时返回默认数据源
    @AfterThrowing(pointcut="@annotation(routingDataSource)",throwing = "e")
    public void afterThrowingMethid(JoinPoint joinPoint,RoutingDataSource routingDataSource,Exception e){
        log.error("数据源切换出错:{}",e.getMessage());
        dataSourcesHolder.initWhenError();
    }

    public String parseParams(Object[] params){
        if(params.length<=0||params==null){
            return "";
        }
        StringBuffer sb = new StringBuffer("传入的参数为:");
        for(Object o:params){
            sb.append(o.toString());
        }
        return sb.toString();

    }

}
