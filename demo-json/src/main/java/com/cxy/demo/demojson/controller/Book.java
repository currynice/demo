package com.cxy.demo.demojson.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Book {

    @JsonProperty("author1")
    private String author;

    @JsonIgnore
    private String tips;

    @JsonFormat(pattern = "yyyyMMdd")
    private Date date;
}
