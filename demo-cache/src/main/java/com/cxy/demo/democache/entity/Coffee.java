package com.cxy.demo.democache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee implements Serializable {
    private Long id;
    private String name;
    private Long price;
}
