package com.cxy.demo.imagestore;

import com.cxy.demo.imagestore.service.AliyunImageStore;
import com.cxy.demo.imagestore.service.ImageStore;

// AliyunImageStore类的使用举例
public class ImageProcessingJob {
    private static final String BUCKET_NAME = "ai_images_bucket";
    //...省略其他无关代码...

    public void process() {
        Image image = new Image(); //todo 处理图片，并封装为Image对象
        ImageStore imageStore = new AliyunImageStore(/*省略参数*/);//todo 工厂模式
        imageStore.upload(image, BUCKET_NAME);
    }

}