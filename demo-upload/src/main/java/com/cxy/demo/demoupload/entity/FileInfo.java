package com.cxy.demo.demoupload.entity;


import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息封装
 */
public class FileInfo {
    //file_id
    private String id;

    private String entityId;

    private Integer entityType;
    /**
     * 文件名
     */
    private String name;

    /**
     * 后缀名
     */
    private String suffix;

    /**
     *上传文件的地址(上传保存，下载)
     */
    private String path;

    /**
     *pdf文件的预览地址(预览)
     */
    private String pdfPath;


    /**
     * 文件->pdf 状态
     * 0-待转换 1转换完成 2-转换失败，默认0
     */
    private Integer conState;

    private MultipartFile file;

    //源文件pdf文件的文件夹名(也就是日期名) like 20190904
    private String dirName;


    public FileInfo() {
        super();
    }

    public String getId() {
        return id;
    }

    public FileInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getSuffix() {
        return suffix;
    }

    public FileInfo setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileInfo setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public FileInfo setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
        return this;
    }


    public Integer getConState() {
        return conState;
    }

    public FileInfo setConState(Integer conState) {
        this.conState = conState;
        return this;
    }

    public String getEntityId() {
        return entityId;
    }

    public FileInfo setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public FileInfo setEntityType(Integer entityType) {
        this.entityType = entityType;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public FileInfo setFile(MultipartFile file) {
        this.file = file;
        return  this;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }


}
