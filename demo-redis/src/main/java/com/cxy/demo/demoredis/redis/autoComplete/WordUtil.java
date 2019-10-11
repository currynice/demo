package com.cxy.demo.demoredis.redis.autoComplete;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;

public class WordUtil {

    /**
     * cat->c,ca,cat
     * dog>d,do,dog
     *
     * @return
     */
    public static String[] getAllPrefixsArr(String word) {
        String[] result = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            result[i] = StrUtil.sub(word, 0, i + 1);
        }
        return result;
    }

    public static List<String> getAllPrefixsList(String word) {
        return Arrays.asList(getAllPrefixsArr(word));
    }





    public static void main(String args[]){
        System.out.println(WordUtil.getAllPrefixsList("cat"));
        System.out.println(WordUtil.getAllPrefixsList("china"));
    }
}
