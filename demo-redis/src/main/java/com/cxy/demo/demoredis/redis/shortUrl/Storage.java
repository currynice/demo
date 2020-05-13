package com.cxy.demo.demoredis.redis.shortUrl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.HashUtil;
import com.cxy.demo.demoredis.exception.SaveUrlException;
import com.cxy.demo.demoredis.redis.shortUrl.util.CustomScaleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public abstract class Storage {

    private static final Logger logger = LoggerFactory.getLogger(Storage.class);

    private static final String DUPLICATED = "DUPLICATED";

    private static final String CHARSET = "UTF-8";


    public Storage() {

    }

    public final String execute(String url){
        //得到10进制hash值
       int hashValue =  getHashValue(url);
       Assert.isTrue(hashValue>0,"短地址计算失败");
       //10进制转为62进制,字符串表示
      // String shortUrl =  CustomScaleUtil._10_to_62(hashValue);
        String shortUrl =  "kOexw";
       //插入原地转和短地址,利用持久化手段唯一性特性(短地址)
        logger.info("尝试保存{}与对应的的短地址{}",url,shortUrl);
       if(insert(shortUrl,url)){
           //插入成功
           return shortUrl;
       }
           //短地址一样不一定是冲突，把原始地址查出来
          logger.info("开始查询短地址{}对应的原始地址",shortUrl);
           String urlSaved = getUrlSaved(shortUrl);
           //真的冲突了，hash冲突
           if(null==urlSaved || !urlSaved.equals(url)){
              //给原始网址添加了一段字符[DUPLICATED]
               int newHashValue =  getHashValue(url+DUPLICATED);
               String newShortUrl =  CustomScaleUtil._10_to_62(newHashValue);
               logger.info("再次尝试保存{}与新生成的短地址{}",url+DUPLICATED,newShortUrl);
               if(!insert(newShortUrl,url+DUPLICATED)){
                   //这次还不成功就抛异常了
                   logger.info("第二次保存{}与{}依然失败",url+DUPLICATED,newShortUrl);
                   throw new SaveUrlException("重复添加失败");
               }
           }else{
               //重复添加而已
               logger.info("{}已经保存过了，直接返回",shortUrl);
               return shortUrl;
           }

           return null;

    }


    /**
     * 插入
     * @param shortUrl 短地址
     * @param url      原始地址
     * @return
     */
    protected boolean  insert(String shortUrl,String url){
        return false;
    }


    /**
     * 查询短地址对应的原始地址
     * @return  null表示不存在，否则返回原始地址
     */
    protected String  getUrlSaved(String shortUrl){
        return null;
    }


    /**
     * 经过MurmurHash算法 获得32位原始网址的hash值(10进制)
     * @param url
     * @return -1表示计算失败
     */
    private static final Integer getHashValue(String url){
        Assert.notBlank(url);
        //url使用utf8编码，因为java int murmur32算法可能返回负数，所以需要把有符号数转换为无符号数
        try {
            int hash =  HashUtil.murmur32(url.getBytes(CHARSET));
            return int2UnsignedInt(hash);
        } catch (UnsupportedEncodingException e) {
            return -1;
        }
    }



    /**
     * int转换成无符号长整型（C中数据类型）
     */
    private static int int2UnsignedInt(int value) {
        if (value >= 0)
            return value;
        Integer unsigned = Integer.valueOf(Integer.toUnsignedString(value));
       return unsigned;
    }






    public static void main(String[] args) {

//        System.out.println(getHashValue("https://www.baidu.com"));
//        String shortUrl =  CustomScaleUtil._10_to_62(getHashValue("https://www.baidu.com"));
//        System.out.println(getHashValue("https://www.baidu222.com/"));
//        String shortUrl2 =  CustomScaleUtil._10_to_62(getHashValue("https://www.baidu222.com/"));
    }
}
