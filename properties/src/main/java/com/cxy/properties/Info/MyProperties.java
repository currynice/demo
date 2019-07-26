package com.cxy.properties.Info;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


/**
 * ConfigurationProperties配置属性文件
 * 默认从全局配置文件中application.properties获取值 或者使用spring.profiles.active=init指定
 * 必须是容器中的组件
 * @ConfigurationProperties 和 （@PropertySource @Value ）3个选择一种方案即可
 */

@Data
@Component("myProperties")
@ConfigurationProperties(prefix="init")
//@PropertySource(value = {"classpath:application-init.properties"},encoding = "UTF-8")
public class MyProperties {
   // @Value("${init.projectName}")
    private String projectName;

     private String[] authors;

     private VersionEnum versionEnum;

     private int level;

     private int level2;

     //周期(默认1小时)
     @DurationUnit(ChronoUnit.MILLIS)
     private Duration period =  Duration.ofMillis(3000L);


      //规则
     private Rules rules;



      enum VersionEnum{
       /**
        * 试用版
        */
        TRY,
       /**
        * 正式版
        */
       FORMAL
      }

      @Data
      static class Rules{
          private int id;
          private String content;
      }
}
