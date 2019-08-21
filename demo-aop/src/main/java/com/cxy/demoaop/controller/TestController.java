package com.cxy.demoaop.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.cxy.demoaop.aspect.Limit;
import com.cxy.demoaop.aspect.LimitEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 测试 Controller
 * </p>
 */
@RestController
public class TestController {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 测试限流方法,60s一个只能访问5次
     * https://blog.csdn.net/tyyh08/article/details/80267261
     *
     * @return {@link Dict}
     */
    @GetMapping("/testLimit")
   // @Limit(name = "测试限流方法",prefix = "test",period = 60,count = 5,limitObject = LimitEnum.IP)
    @Limit( period = 30, count = 5, name = "testLimit", prefix = "limit")
    public Dict testLimit() {

        return Dict.create().set("次数",atomicInteger.incrementAndGet());
    }

    @GetMapping("/whoIam")
    public Dict whoIam(String who) {

        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }

}
