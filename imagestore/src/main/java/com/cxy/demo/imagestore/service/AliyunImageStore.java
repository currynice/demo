package com.cxy.demo.imagestore.service;

import com.cxy.demo.imagestore.Image;

/**
 * 图片经过处理之后,上传到阿里云
 */

public class AliyunImageStore  implements  ImageStore{
    //...省略属性、构造函数等...

    /**
     * 创建上传阿里云需要的bucket(存储目录)
     */
    private void createBucketIfNotExisting(String bucketName) {
        // ...创建bucket代码逻辑...
        // ...失败会抛出异常..
    }

    /**
     *生成唯一访问凭证,阿里云需要，别的可能不需要
     */
    private String generateAccessToken() {
        // ...根据accesskey/secrectkey等生成access token
        return "";
    }



    /**
     * 携token上传图片至指定bucket
     * @param image
     * @param bucketName
     * @return
     */
    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExisting(bucketName);
        String accessToken = generateAccessToken();
        //...上传图片到阿里云...
        //...返回图片存储在阿里云上的地址(url）...
        return "url";
    }

    /**
     * 根据token下载
     * @param url
     * @return
     */
    @Override
    public Image download(String url) {
        String accessToken = generateAccessToken();
        //...从阿里云下载图片...
        return new Image();
    }
}


