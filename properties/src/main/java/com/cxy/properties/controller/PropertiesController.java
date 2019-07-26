package com.cxy.properties.controller;


import cn.hutool.core.lang.Dict;
import com.cxy.properties.Info.EggProperties;
import com.cxy.properties.Info.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {
    private MyProperties myProperties;
    private EggProperties eggProperties;

    @Autowired
    private PropertiesController (MyProperties myProperties,EggProperties eggProperties){
        this.myProperties = myProperties;
        this.eggProperties = eggProperties;
    }

    /**
     * result
     * {"我自定义的配置信息":{"projectName":"springboot-demo-properties","authors":["cxy","cxy2"],"versionEnum":"TRY","
     * level":1,"level2":2,"rules":{"id":1,"content":"规则"}},"小彩蛋":"只是一个小彩蛋"}
     * @return
     */
    @GetMapping("/property")
    public Dict index() {
        Dict dict = Dict.create().set("我自定义的配置信息", myProperties);
        if(eggProperties.getEgg().getShow()){
            dict.set("小彩蛋",eggProperties.getEgg().getDesc());
        }
        return dict;
    }
}
