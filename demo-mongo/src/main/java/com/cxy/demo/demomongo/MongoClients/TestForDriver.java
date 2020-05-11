package com.cxy.demo.demomongo.MongoClients;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Description:   <br>
 * Date: 2020/5/11 15:48  <br>
 *
 *  for more details about reactivestreams(jdk9才支持) :https://mongoing.com/archives/28750
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class TestForDriver {

    private static final String connStr = "mongodb://cxy:123456@localhost:27017/newtest";

    public void sync(){
        ConnectionString connString = new ConnectionString(
                connStr
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("newtest");
    }


    public static void main(String args[]){

    }

}
