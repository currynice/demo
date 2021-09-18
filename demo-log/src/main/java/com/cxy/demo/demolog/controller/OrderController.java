package com.cxy.demo.demolog.controller;

import com.cxy.demo.demolog.annotation.LogRecord;
import com.cxy.demo.demolog.entity.UpdateDelivery;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>订单</h1>
 * */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    //记录特定日志的声明
    private final Logger businessLog = LoggerFactory.getLogger("businessLog");



    @GetMapping("/changeAddress1")
    public String changeAddress1() {

        businessLog.info("修改了配送地址");

        return "ok";
    }


//    @GetMapping("/changeAddress2")
//    @LogRecord(content = "修改了订单的配送地址：从“#oldAddress”, 修改到“#request.address”",
//            bizNo="#request.deliveryOrderNo")
//    public String changeAddress2(UpdateDelivery request) {
//
//        // 查询出原来的地址是什么
//        LogRecordContext.putVariable("oldAddress", DeliveryService.queryOldAddress(request.getDeliveryOrderNo()));
//
////        更新派送信息: 电话，收件人、配送地址
//        //doUpdate()
//
//
//        return "ok";
//    }

    /**
     * 使用自定义函数queryOldUser（查原来的配送人）， deveryUser（查配送人信息） 使用大括号把 Spring 的 SpEL 表达式包裹起来
     * 让自定义函数的解析在 modifyAddress() 方法执行之
     * @param request
     */
    @LogRecord(content = "修改了订单的配送员：从“{queryOldUser{#request.deliveryOrderNo()}}”, 修改到“{deveryUser{#request.userId}}”",
            bizNo="#request.deliveryOrderNo")
    public void modifyAddress(UpdateDelivery request){
        // 更新派送信息 电话，收件人、地址
//        doUpdate(request);
    }


}
