package com.cxy.demo.demojpa;

import com.cxy.demo.demojpa.base.entity.User;
import com.cxy.demo.demojpa.base.repository.PageAndSortRepository;
import com.cxy.demo.demojpa.base.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTestCases {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageAndSortRepository pageAndSortRepository;


    @Test
    public void testDQM() {
//        userRepository.findAll().forEach(System.out::println)
       User u =  userRepository.findByEmail("123@126.com");
       System.out.println(u.toString());
    }


    @Test
    public void testPageAndSort(){
//        userRepository.findAll().forEach(System.out::println)
        //分页查: 查询user表里 name=jack的第0页，每页大小是20条；会返回一共有多少页的额外信息
        List<User> users =  pageAndSortRepository.findTop3ByNameStartingWith("jack", PageRequest.of(1,2));
        users.forEach(System.out::println);
    }


    @Test
    public void repositoryReturnType(){




        Streamable<User> userStreamable = userRepository.findAll(PageRequest.of(0,10));

        List<String> names = userStreamable.map(User::getName).stream().collect(Collectors.toList());

        names.forEach(System.out::println);

    }


    @Test
    public void query(){

        User user2 = userRepository.findByQuery("jack");
        System.out.println(user2);

    }

}
