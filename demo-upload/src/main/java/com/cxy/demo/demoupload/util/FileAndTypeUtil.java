package com.cxy.demo.demoupload.util;


import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型工具类
 */
public class FileAndTypeUtil {

    private FileAndTypeUtil() {
    }
    private static Map<String,String> fileTypeMap = new HashMap<>();
    static{
        fileTypeMap.put("255044462d312e", "pdf"); // Adobe Acrobat (pdf)
        fileTypeMap.put("d0cf11e0a1b11ae10000", "doc"); // MS Excel 注意：word、msi 和 excel的文件头一样
        fileTypeMap. put("504B03040A0000000000874EE24","docx");
    }


    /**
     * 获得文件流的头部信息
     *
     * @param in {@link InputStream}
     * @return 类型，文件的扩展名，未找到为<code>null</code>
     * @throws IORuntimeException 读取流引起的异常
     */
    public static String getFileStreamHexHead(InputStream in) throws IORuntimeException {
        return IoUtil.readHex28Upper(in);
    }

    public static String getFileStreamHexHead(File file) throws IORuntimeException {
        FileInputStream in = null;
        try {
            in = IoUtil.toStream(file);
            return getFileStreamHexHead(in);
        } finally {
            IoUtil.close(in);
        }
    }


    /**
     * 根据文件流的头部信息获得文件类型
     *
     * @param fileStreamHexHead 文件流头部16进制字符串
     * @return 文件类型，未找到为<code>null</code>
     */
    public static String getType(String fileStreamHexHead) {
        for (Map.Entry<String, String> fileTypeEntry : fileTypeMap.entrySet()) {
            if(StrUtil.startWithIgnoreCase(fileStreamHexHead, fileTypeEntry.getKey())) {
                return fileTypeEntry.getValue();
            }
        }
        return null;
    }


    public static String getType(InputStream in) throws IORuntimeException {
        return getType(IoUtil.readHex28Upper(in));
    }


    public static String getType(File file) throws IORuntimeException {
        FileInputStream in = null;
        try {
            in = IoUtil.toStream(file);
            return getType(in);
        } finally {
            IoUtil.close(in);
        }
    }




}
