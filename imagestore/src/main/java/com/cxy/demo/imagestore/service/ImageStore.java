package com.cxy.demo.imagestore.service;

import com.cxy.demo.imagestore.Image;

public interface ImageStore {

    String upload(Image image, String bucketName);

    Image download(String url);
}
