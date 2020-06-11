package com.cxy.demo.restful;

import lombok.Data;

/**
 * Description:  客户端收集的信息 <br>
 * Date: 2020/6/11 11:25  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Data
public class MyDevice {
    private String type;
    private String version;
    private String screen;
}
