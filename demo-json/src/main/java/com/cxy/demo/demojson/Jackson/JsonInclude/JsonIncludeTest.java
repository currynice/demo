package com.cxy.demo.demojson.Jackson.JsonInclude;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonIncludeTest {
    public static void main(String[] args) {
    try{
        ObjectMapper objectMapper = new ObjectMapper();
        //全局配置
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = "{\"name\":\"cxy\",\"age\":20,\"position\":null,\"skills\":null,\"salary\":null}";
        Staff staff = objectMapper.readValue(json,Staff.class);
        String prettyStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
        System.out.println(prettyStr);
        /**
         * {
         "name" : "mkyong",
         "age" : 38
         }
         */
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
