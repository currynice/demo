package com.cxy.demovalidation.bean;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class Book {

    @NotEmpty(message = "麻烦提供一个name")
    private String name;
    @NotEmpty(message = "麻烦提供一个author")
    private String author;

    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "11.23", message = "price最低11.23")
    private BigDecimal price;

    public Book( String name) {
        this.name = name;
    }
}
