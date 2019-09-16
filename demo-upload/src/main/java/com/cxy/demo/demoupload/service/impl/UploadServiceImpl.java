package com.cxy.demo.demoupload.service.impl;

import com.cxy.demo.demoupload.entity.FileInfo;
import com.cxy.demo.demoupload.service.UploadService;
import com.cxy.demo.demoupload.util.FileAndTypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author chengxinyu
 * @version 1.1.0
 * @description UploadServiceImpl
 * @date 2019年08月30日16:04
 **/
@Service
public class UploadServiceImpl implements UploadService {





    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    private static final ThreadLocal<SimpleDateFormat> dateTimneFormat = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    //保存文件方法
    @Transactional(rollbackFor = Exception.class)
    @Override
     public boolean saveUploadFlies(FileInfo fileInfo, String uploadPath) {
        try{
       String todayStr = dateTimneFormat.get().format(new Date());
       String filePath = uploadPath+File.separator+todayStr+File.separator+fileInfo.getId();// test/20190808/132312
        File dir = new File(filePath);
        if(!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if(!mkdirs){
                logger.info("目录{}创建失败",filePath);
                return false;
            }
        }
            MultipartFile file = fileInfo.getFile();
            String saveFile = fileInfo.getName()+"."+fileInfo.getSuffix();
            byte []bytes = file.getBytes();
            Path path = Paths.get(filePath+File.separator+saveFile);
            Files.write(path,bytes);



        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Override
    public boolean isAllowedType(InputStream inputStream) {
        return FileAndTypeUtil.getType(inputStream)!=null;
    }






}



