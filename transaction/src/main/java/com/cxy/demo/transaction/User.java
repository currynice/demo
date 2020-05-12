package com.cxy.demo.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

/**
 * Description:   <br>
 * Date: 2020/5/12 16:55  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Data
@Table(name = "test1")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    private String name;

    public User(String name) {
        this.name = name;
    }
}
