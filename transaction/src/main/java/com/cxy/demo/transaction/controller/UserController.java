package com.cxy.demo.transaction.controller;

import com.cxy.demo.transaction.User;
import com.cxy.demo.transaction.service.PropagationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:   </br>
 * Date: 2020/5/12 22:55
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private PropagationService propagationService;
    @GetMapping("wrong")public Long wrong(@RequestParam("name") String name) {
        try {
            propagationService.createUserRight(new User(name));
        } catch (Exception ex) {
            log.error("createUserWrong failed, reason:{}", ex.getMessage());
        }
        return propagationService.getUserCount();
    }
}
