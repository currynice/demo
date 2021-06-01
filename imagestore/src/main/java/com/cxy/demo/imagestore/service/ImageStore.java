package com.cxy.demo.imagestore.service;

import com.cxy.demo.imagestore.Image;

public interface ImageStore {

    //上传,返回图片url
    String upload(Image image, String bucketName);

    //下载
    Image download(String url);

    /**
     * 对象存储类型（AWS,阿里云...）
     * @return
     */
    String getType();
}
