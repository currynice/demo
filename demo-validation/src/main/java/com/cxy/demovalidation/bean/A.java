package com.cxy.demovalidation.bean;


import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class A {
    @Future(message = "日期必须是未来的")
    private Date time;

    @NotBlank(message = "name不可以为空")
    private String name;
}
