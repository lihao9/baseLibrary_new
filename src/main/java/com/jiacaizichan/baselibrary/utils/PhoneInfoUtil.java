package com.jiacaizichan.baselibrary.utils;


import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 创建日期：2018/7/24 14:53
 * @author lihao
 * decs： 获取设备信息
 */
public class PhoneInfoUtil {


    /**
     * 获取手机厂商
     * @return
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机名称
     * @return
     */
    public static String getDeviceName() {
        return Build.DEVICE;
    }

    /**
     * 获取设备型号
     * @return
     */
    public static String getModel(){
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 获取手机rom
     * @return
     */
    public static String getRom(Context context){
        File path = Environment.getDataDirectory();
        StatFs mStatFs = new StatFs(path.getPath());
        long blockSize = mStatFs.getBlockSize();
        long totalBlocks = mStatFs.getBlockCount();

        Double rom1 = ((double) totalBlocks*blockSize/1024)/1024/1024;
        int rom = (int) Math.ceil(rom1);

        int i = 0;
        double pow = Math.pow(2, 2);
        while(rom>(int)Math.pow(2,i)){
            i++;
        }
        rom = (int) Math.pow(2,i);
//        return Formatter.formatFileSize(context, blockSize * totalBlocks);
        return rom+"G";
    }

    /**
     * 获取手机ram
     * @return
     */
    public static String getRam(){
        int ram = 0;
        try {
            FileInputStream fis = new FileInputStream(new File("/proc/meminfo"));
            //包装一个一行行读取的流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            //取到所有的内存信息
            String memTotal = bufferedReader.readLine();

            StringBuffer sb = new StringBuffer();

            for (char c : memTotal.toCharArray()) {

                if (c >= '0' && c <= '9') {
                    sb.append(c);
                }
            }
            //为了方便格式化 所以乘以1024
            double l = Double.parseDouble(sb.toString())/1024/1024;
            ram = (int) Math.ceil(l);
            return ram+"G";
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

}
