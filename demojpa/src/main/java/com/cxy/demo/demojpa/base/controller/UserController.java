package com.cxy.demo.demojpa.base.controller;

import com.cxy.demo.demojpa.base.entity.User;
import com.cxy.demo.demojpa.base.repository.UserPagingAndSortingRepository;
import com.cxy.demo.demojpa.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;


/**
 * Description:   </br>
 * Date: 2021/3/21 20:51
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    /**

     * 保存用户

     * @param user

     * @return

     */

    @PostMapping(path = "user",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User addNewUser(@RequestBody User user) {

        return userRepository.save(user);

    }

    /**

     * 验证排序和分页查询方法，Pageable的默认实现类：PageRequest

     * @return

     */

    @GetMapping(path = "/page")

    @ResponseBody

    public Page<User> getAllUserByPage() {

        return userRepository.findAll(

                PageRequest.of(1, 20,Sort.by(new Sort.Order(Sort.Direction.ASC,"name"))));

    }

    /**

     * 排序查询方法，使用Sort对象

     * @return

     */

    @GetMapping(path = "/sort")

    @ResponseBody

    public Iterable<User> getAllUsersWithSort() {

        return userRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.ASC,"name")));

    }


}

