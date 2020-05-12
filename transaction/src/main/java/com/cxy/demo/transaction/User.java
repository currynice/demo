package com.cxy.demo.transaction;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class User {
    @Id
    private Integer id;

    private String name;
}
