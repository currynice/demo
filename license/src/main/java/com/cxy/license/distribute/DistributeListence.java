package com.cxy.license.distribute;


import com.cxy.license.entity.License;

public class DistributeListence {
        /** 生成公钥、私钥对。公钥公开，注意保管好私钥（如果泄露，则有可能被hacker随意创建license）
         *  
         */
         public static void generateKey() throws Exception{
//             KeyUtil.generateKey()
         }

        /**
         * 用私钥对license进行数据签名
         */
         public  synchronized void sign(License license) throws Exception{

        }

       /**
      * 验证license是否合法。
      * 首先验证Mac地址是否有改变，有的话则非法。（防止用户自由拷贝软件）。
      * 然后根据公钥验证签名是否合法。
        */
         public  boolean  validate(License license) throws Exception{
                return false;
        }

    public static void main(String[] args) throws Exception{

    }



}
