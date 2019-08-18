package com.cxy.demo.datasources.controller;


import com.cxy.demo.datasources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class JdbcTemplateController {
    @Autowired
    @Qualifier("jdbcTemplateMaster")
    private JdbcTemplate jdbcTemplateMaster;


    @Resource(name="jdbcTemplateSlave1")
    private JdbcTemplate jdbcTemplateSlave1;



    @GetMapping("/user1")
    public List<User> getFromMaster(){
        return jdbcTemplateMaster.query("select * from master_user", new BeanPropertyRowMapper<>(User.class));
    }



    @GetMapping("/user2")

    public List<User> getFromSlave1(){
        return jdbcTemplateSlave1.query("select * from slave1_user", new BeanPropertyRowMapper<>(User.class));
    }
}
