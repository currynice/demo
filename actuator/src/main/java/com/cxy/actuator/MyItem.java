package com.cxy.actuator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:   <br>
 * Date: 2020/6/12 10:17  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@AllArgsConstructor
@Data
public class MyItem {
    private String name;
    private Integer price;
}