package com.cxy.demo.demojpa.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Description: 映射User表  </br>
 * Date: 2021/3/21 20:46
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
}
