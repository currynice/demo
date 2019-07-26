package com.cxy.properties.Info;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * 有关小彩蛋的配置信息
 */
@Component("eggProperties")
@ConfigurationProperties(prefix = "extra")
@Data
public class EggProperties {

    /**
     * 嵌套配置 --彩蛋
     * 当一个类中引用了<strong>外部类</strong>，需要在该属性上加该注解<br>
     * MyProperties的Rule是内部类，不需要<br>
     * {@link com.cxy.properties.Info.MyProperties.Rules#}
     */
    @NestedConfigurationProperty
    private Egg egg;

}
