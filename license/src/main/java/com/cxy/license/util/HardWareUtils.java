
package com.cxy.license.util;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;


/*****
 *  Linux�?

command

0、check CPUID：dmidecode -t processor | grep 'ID'

1、check server type sn：dmidecode | grep 'Product Name'

2、check mother board SN：dmidecode |grep 'Serial Number'

3、check system SN：dmidecode -s system-serial-number

4、check memmory info：dmidecode -t memory

5、check OEM info：dmidecode -t 11
 * 
 *
 */
@Slf4j(topic = "Logger")
public class HardWareUtils {

    private HardWareUtils() {
        throw new IllegalStateException("不可实例化");
    }

    /**
     * mother board  主板
     * 
     * @return
     */
    public static String getMotherboardSN() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            log.error("主板SN读取失败",e.getMessage());
        }
        return result.trim();
    }

    /**
     * HardDisk
     *
     * @param drive   x盘 ,c盘，d盘
     *
     * @return
     */
    public static String getHardDiskSN(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            log.error("硬盘SN读取失败",e.getMessage());
        }
        return result.trim();
    }

    /**
     * CPU
     *
     * @return
     */
    public static String getCPUSerial() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
 
            // + "    exit for  \r\n" + "Next";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            log.error("CPU SN读取失败",e.getMessage());
        }
        if (result.trim().length() < 1 || result == null) {
            result="no cpu id could be read";
        }
        return result.trim();
    }
    /**
     * 获取本机MAC地址
     *
     * @return
     */
    public static final String getLocalMac() {
        String localMac = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            /**
             * 获取电脑网卡的AMC地址
             * 返回包含硬件地址的 byte 数组；如果地址不存在或不可访问，则返回 null
             * 如果电脑因为网卡被禁用，则这里获取会返回 null
             */
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            if (mac == null) {
                log.info("获取网卡 MAC 地址失败，网卡可能被禁用...");
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    stringBuffer.append("-");
                }
                /**
                 * 转换mac的字节数组
                 */
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    stringBuffer.append("0" + str);
                } else {
                    stringBuffer.append(str);
                }
            }
            localMac = stringBuffer.toString().toUpperCase();
        } catch (SocketException| UnknownHostException e) {
            log.error("本机Mac地址读取失败",e.getMessage());
        }
        return localMac;
    }

    /**
     * mac address
     */
    public static String getMac() {
        String result = "";
        try {
 
            Process process = Runtime.getRuntime().exec("ipconfig /all");
 
            InputStreamReader ir = new InputStreamReader(
                    process.getInputStream());
 
            LineNumberReader input = new LineNumberReader(ir);
 
            String line;
 
            while ((line = input.readLine()) != null)
 
                if (line.indexOf("Physical Address") > 0) {
 
                    String MACAddr = line.substring(line.indexOf("-") - 2);
 
                    result = MACAddr;
                    System.out.println("MAC  SN:" + result);
                }
 
        } catch (IOException e) {

            log.error("Mac SN读取失败",e.getMessage());
 
        }
        return result;
    }
 

 
}