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
@Entity
@Table(name = "test2")
public class CopyUser {
    @Id
    private Integer id;

    private String copyName;


    public CopyUser(String copyName) {
        this.copyName = copyName;
    }
}
