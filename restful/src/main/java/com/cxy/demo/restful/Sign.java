package com.cxy.demo.restful;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Description:   <br>
 * Date: 2020/6/11 11:42  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sign {
}
