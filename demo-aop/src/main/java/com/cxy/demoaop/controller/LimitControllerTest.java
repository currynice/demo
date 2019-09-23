package com.cxy.demoaop.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.cxy.demoaop.aspect.Limit;
import com.cxy.demoaop.aspect.LimitEnum;
import com.cxy.demoaop.util.IPUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 测试 Controller
 * </p>
 */
@RestController
public class LimitControllerTest {
//    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 测试限流方法,60s一个只能访问5次
     * https://blog.csdn.net/tyyh08/article/details/80267261
     *
     * @return {@link Dict}
     */
    @GetMapping("/testLimitId/{userId}")
    @Limit( period = 30, count = 5, name = "testLimitId", prefix = "id")
    public Dict testLimit(@PathVariable(value = "userId",required = true) String userId) {

        return Dict.create().set("message","成功访问").set("time", DateUtil.formatDateTime(new Date()));
    }

    @GetMapping("/testLimitIp")
    @Limit( period = 30, count = 5, name = "testLimitIp", prefix = "ip",limitObject = LimitEnum.IP)
    public Dict testLimit(HttpServletRequest request) {

        return Dict.create().set("message","成功访问").set("ip", IPUtil.getIP(request)).set("time", DateUtil.formatDateTime(new Date()));
    }

    @GetMapping("/whoIam")
    public Dict whoIam(String who) {

        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }

}
