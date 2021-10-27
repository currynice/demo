package com.cxy.demo.demojpa.base.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
@ToString
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
}
