package com.cxy.demo.demoquartz.controller;


import com.cxy.demo.demoquartz.entity.GoodInfo;
import com.cxy.demo.demoquartz.service.GoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/good")
public class GoodController
{
    /**
     * 商品业务逻辑实现
     */
    @Autowired
    private GoodInfoService goodInfoService;
    /**
     * 添加商品
     * @return
     */
    @RequestMapping(value = "/save")
    public Long save(GoodInfo good) throws Exception {
        return goodInfoService.save(good);
    }
}
