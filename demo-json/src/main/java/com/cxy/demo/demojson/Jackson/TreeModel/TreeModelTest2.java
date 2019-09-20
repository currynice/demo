package com.cxy.demo.demojson.Jackson.TreeModel;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 *在json映射java结构不是很nice的情况下，可以使用Tree Model代表json结构,借助JsonNode进行CRUD操作，比XML DOM tree 方便
 *
 *
 */
public class TreeModelTest2 {
    private static final ObjectMapper mapper  = new ObjectMapper();


    public static void main(String[] args){
        try {


            //读取array
            JsonNode root = mapper.readTree(new File("./file/json/tree2.json"));
                //获取code
                int code = root.path("code").asInt();
                System.out.println("code:" + code);

                //获取metainfo
                JsonNode metainfoNode = root.path("metainfo");
                if (!metainfoNode.isMissingNode()) {//metainfo存在
                    System.out.println("metainfo.total:" + metainfoNode.path("total").asInt());
                    if(!metainfoNode.path("info").isMissingNode()){
                        if(!metainfoNode.path("info").path("parts").isMissingNode()&&metainfoNode.path("info").path("parts").isArray()){
                            JsonNode  partsArray = metainfoNode.path("info").path("parts");
                            for(JsonNode part:partsArray){
                                int count = part.path("count").asInt();
                                System.out.println(count);
                            }
                        }
                    }
                }





        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
