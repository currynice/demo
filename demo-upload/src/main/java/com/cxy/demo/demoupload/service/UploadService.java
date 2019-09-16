package com.cxy.demo.demoupload.service;



import com.cxy.demo.demoupload.entity.FileInfo;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author chengxinyu
 * @description UploadService
 * @date 2019年08月30日16:04
 **/

public interface UploadService {
    /**
     * 上传
     *
     * @param fileInfo
     * @param uploadPath
     * @return
     * @throws IOException
     */
    boolean saveUploadFlies(FileInfo fileInfo, String uploadPath) throws IOException;


    /**
     * 根据文件的前几位bytes猜测文件类型
     *
     * @param inputStream
     * @return
     */
    boolean isAllowedType(InputStream inputStream);


}
