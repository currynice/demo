package com.cxy.demoaop.redisTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class People implements Serializable {

    private static final long serialVersionUID = 8909192724668069266L;
    private String name ;
    private int age ;
}