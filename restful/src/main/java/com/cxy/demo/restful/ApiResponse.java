package com.cxy.demo.restful;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:   <br>
 * Date: 2020/6/11 11:40  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Data
@AllArgsConstructor
public class ApiResponse <T> {
        T data;
        boolean success;
        String message;
        String sign;
    }
