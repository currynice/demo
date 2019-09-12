package com.cxy.demo.demojson.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Book2 {


    private String author;

    @JsonIgnore
    private String tips;


    private Date date;
}
