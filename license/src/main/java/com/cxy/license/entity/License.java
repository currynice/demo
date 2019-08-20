package com.cxy.license.entity;



import lombok.Data;

/**
 * 许可信息
 * https://github.com/grassprogramming/yblog/blob/master/main/java/com/blog/controller/LoginController.java
 * https://note.youdao.com/ynoteshare1/index.html?id=09e2bfc902b21a335a4505f7946a45c9&type=note
 * https://blog.csdn.net/u013407099/article/details/81271701
 * https://blog.csdn.net/lee272616/article/details/55057311
 * https://blog.csdn.net/yishui2005/article/details/83284195
 */
@Data
public class License {



    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品版本
     */
    private String productVersion;

    /**
     * 许可类型
     */
    private LicenseTypeEnum licenseType;

    /**
     * 到期时间
     */
    private String expiryDate;

    /**
     * cpu编号
     */
    private String cpuNumber;

    /**
     * 真正的许可证签名
     */
    private String signature;
}
