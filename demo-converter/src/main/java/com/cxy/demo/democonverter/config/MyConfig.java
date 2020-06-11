package com.cxy.demo.democonverter.config;

import com.cxy.demo.democonverter.config.converter.EnumIgnoreCaseConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Description:   <br>
 * Date: 2020/6/11 11:34  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumIgnoreCaseConverterFactory());
    }
}
