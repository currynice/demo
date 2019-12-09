package com.cxy.demo.demoredis.redis.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

//给data-jpa-repository用的
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "demo_data")
public class Data2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String subject;

}
