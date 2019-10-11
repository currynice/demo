//package com.cxy.demo.demoasync.event;
//
//
//import org.springframework.context.ApplicationEvent;
//
//import java.io.File;
//
///**
// * @Author: cxy
// * @Date: 2019/6/14 16:20
// * @Description:
// */
//public class File2PdfEvent extends ApplicationEvent {
//
//    private File sourceFile;
//    private File targetFile;
//    private  String accessoryId;
//    private  String pdfPath;
//
//    public File getSourceFile() {
//        return sourceFile;
//    }
//
//    public File getTargetFile() {
//        return targetFile;
//    }
//
//    public String getAccessoryId() {
//        return accessoryId;
//    }
//
//    public String getPdfPath() {
//        return pdfPath;
//    }
//
//    public File2PdfEvent(Object source, File sourceFile, File targetFile, String accessoryId, String pdfPath) {
//        super(source);
//        this.sourceFile = sourceFile;
//        this.targetFile = targetFile;
//        this.accessoryId = accessoryId;
//        this.pdfPath = pdfPath;
//    }
//}
