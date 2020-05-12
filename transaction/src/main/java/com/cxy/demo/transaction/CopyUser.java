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
@Entity
@Table(name = "test2")
@AllArgsConstructor
@NoArgsConstructor
public class CopyUser {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    private String copyName;


    public CopyUser(String copyName) {
        this.copyName = copyName;
    }
}
