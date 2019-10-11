//package com.cxy.demo.demoasync.event;
//
//
//import com.szxy.zxb.modules.accessory.dao.AccessoryDao;
//import com.szxy.zxb.modules.accessory.service.impl.UploadServiceImpl;
//import org.jodconverter.DocumentConverter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.event.SmartApplicationListener;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//
///**
// * @Author: cxy
// * @Date: 2019/9/4
// * @Description: pdf转换完成listener
// */
//@Component
//public class PdfDoneListener implements SmartApplicationListener {
//
//    @Autowired
//    private DocumentConverter documentConverter;
//
//    private static final Logger log = LoggerFactory.getLogger(PdfDoneListener.class);
//
//    @Autowired
//    private AccessoryDao accessoryDao;
//
//    @Override
//    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
//        return aClass == File2PdfEvent.class;
//
//    }
//
//    @Override
//    public boolean supportsSourceType(Class<?> sourceType) {
//
//        return sourceType== UploadServiceImpl.class;
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//    @Override
//    @Async
//    public void onApplicationEvent(ApplicationEvent applicationEvent){
//        log.info("正在异步转换");
//        //转换事件类型
//        File2PdfEvent file2PdfEvent = (File2PdfEvent) applicationEvent;
//        try {
//            documentConverter.convert(file2PdfEvent.getSourceFile()).to(file2PdfEvent.getTargetFile()).execute();
//            if(accessoryDao.updatePdfPathAndState(file2PdfEvent.getAccessoryId(),file2PdfEvent.getPdfPath())<=0){
//                log.warn("附件表更新失败");
//            }
//        } catch (Exception e) {
//            log.warn(file2PdfEvent.getSourceFile().getName()+"转换pdf失败");
//        }
//        log.info("异步转换结束");
//    }
//}
