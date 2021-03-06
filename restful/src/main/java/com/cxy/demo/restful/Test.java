package com.cxy.demo.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:   <br>
 * Date: 2020/5/12 15:00  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@RestController
public class Test {



    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);


    @GetMapping("wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before  = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);
        try{
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after  = Thread.currentThread().getName() + ":" + currentUser.get();
        //汇总输出两次查询结果
        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
        } finally {
            //在finally代码块中删除ThreadLocal中的数据，确保数据不串
            currentUser.remove();    }
    }

    /**
     * {
     *     "type": "ios",
     *     "version": "1.0",
     *     "screen": "1280*800"
     * }
     * @param device
     * @return
     */
    @GetMapping("collectDevice")
    public MyDevice collectDevice(MyDevice device) {
       return device;
    }



}
