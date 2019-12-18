package com.cxy.demo.demojson.fastjson;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return builder->{
          builder.indentOutput(true);
          builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        };
    }



    //加入消息转换器列表，不影响默认的
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //FastJsonHttpMessageConverter
        FastJsonHttpMessageConverter jsonHttpMessageConverterconverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy--MM--dd");
        //输出类名，美化输出...
        config.setSerializerFeatures(SerializerFeature.WriteClassName,SerializerFeature.PrettyFormat);
        jsonHttpMessageConverterconverter.setFastJsonConfig(config);



        MappingJackson2XmlHttpMessageConverter  xmlHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
        xmlHttpMessageConverter.setPrettyPrint(true);
        converters.add(jsonHttpMessageConverterconverter);
        converters.add(xmlHttpMessageConverter);
    }
}
