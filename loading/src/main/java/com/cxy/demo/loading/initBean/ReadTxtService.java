package com.cxy.demo.loading.initBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 */
@Service
public class ReadTxtService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        try(InputStream  ios=Thread.currentThread().getContextClassLoader().getResourceAsStream("word.txt");
            InputStreamReader isr = new InputStreamReader(ios);
            BufferedReader reader = new BufferedReader(isr);){
           String nextLine;
           while((nextLine=reader.readLine())!=null){
                System.out.println(nextLine);
           }
        }catch (IOException e){

        }

    }
}
