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
public class TreeModelTest {
    private static final ObjectMapper mapper  = new ObjectMapper();


    public static void main(String[] args){
        try {

//            Staff staff1 = Staff.builder().name(new Name("c","xy")).age(22).contacts(Arrays.asList(new Contact[]{new Contact("home","120"),new Contact("company","110")})).build();
//            Staff staff2 = Staff.builder().name(new Name("c1","xy")).age(20).contacts(Arrays.asList(new Contact[]{new Contact("home","711"),new Contact("company","110")})).build();
//            Staff staff3 = Staff.builder().name(new Name("c2","xy")).age(20).contacts(Arrays.asList(new Contact[]{new Contact("home","222"),new Contact("company","110")})).build();
//
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("./file/json/tree.json"),Arrays.asList(staff1,staff2,staff3));

            //读取array
            JsonNode rootArray = mapper.readTree(new File("./file/json/tree.json"));
            for(JsonNode root:rootArray ){
                //获取age
                int age = root.path("age").asInt();
                System.out.println("age:" + age);
                //获取name
                JsonNode nameNode = root.path("name");
                String fullName ="";
                if (!nameNode.isMissingNode()) {//name存在
                    System.out.println("first:" + nameNode.path("first").asText());
                    System.out.println("last:" + nameNode.path("last").asText());
                    fullName+=nameNode.path("first").asText();
                    fullName+=nameNode.path("last").asText();

                }
                //获取contact
                JsonNode contactsNode = root.path("contacts");
                if (contactsNode.isArray()) {

                    //turn to ArrayNode
                    System.out.println("Is "+fullName+" 's contactsNode an Array? " + contactsNode.isArray());

                    for (JsonNode node : contactsNode) {
                        String type = node.path("type").asText();
                        String ref = node.path("ref").asText();
                        System.out.println("type : " + type);
                        System.out.println("ref : " + ref);

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
