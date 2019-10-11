package com.cxy.demo.demoredis.redis.autoComplete;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 不考虑事务
 */
@Service
public class AutoCompleteService {

    @Resource(name = "zSetOperations")
    private ZSetOperations zSetOperations;



    public void init(){
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        File file = null;
        try {
            file = resourceLoader.getResource("classpath:/init/names.txt").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr)){
                String nextLine;
            while(true) {
                nextLine = reader.readLine();
                if(nextLine == null) {
                    break;
                }
                this.incrPrefix(nextLine);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }





    @SuppressWarnings("unchecked")
    public void incrPrefix(String word){
        List<String> prefixs = WordUtil.getAllPrefixsList(word);
        Assert.notNull(word,"word must be not null");
        for(String key:prefixs){
            zSetOperations.incrementScore(key,word,1);
        }
    }


    @SuppressWarnings("unchecked")
    public List<String> getAutoCompletes(String prefix,long num){
        Assert.notNull(prefix,"prefix must be not null");

        Set<String> result = zSetOperations.range(prefix,0,num);

        return result.stream().collect(Collectors.toList());
    }

}
