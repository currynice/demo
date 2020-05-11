package com.cxy.demo.demomongo.config;

import com.cxy.demo.demomongo.converter.MoneyReadConverter;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

/**
 * Description:   <br>
 * Date: 2020/5/11 14:31  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Configuration
public class MyMongoConfig extends AbstractMongoConfiguration {
    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getDatabaseName() {
        return "newtest";
    }


    @Bean
    @Override
    public CustomConversions customConversions() {

        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }


}
