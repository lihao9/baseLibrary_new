package com.jiacaizichan.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * 创建日期：2018/7/24 11:45
 * @author lihao
 * decs： sp和文件存储工具类
 */
public class FileUtil {

    /**
     * 根据父路径与文件类型创建文件
     * @param parentFilePath
     * @param classFile
     * @return
     */
    public static File createParentFile(String parentFilePath,String classFile){
        File iconDir;
        String rootDir = "/" + parentFilePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            iconDir = new File(Environment.getExternalStorageDirectory(), rootDir + "/"+classFile);
        }else {
            iconDir = new File(Environment.getRootDirectory(),rootDir + "/"+classFile);
        }
        if (iconDir.exists()){
            return iconDir;
        }else {
            iconDir.mkdirs();
            return iconDir;
        }
    }

    /**
     * 创建随机图片文件
     * @param parentFilePath
     * @param classFile
     * @return
     */
    public static File createImageFile(String parentFilePath,String classFile) throws IOException {
        File targetFile = createParentFile(parentFilePath,classFile);
        String fileName = "";
        if (targetFile != null) {
            fileName = UUID.randomUUID().toString() + ".png";
        }
        File file = new File(targetFile, fileName);
        if(file.exists()){
            return file;
        }else {
            file.createNewFile();
            return file;
        }
    }

    /**
     * 获取自定义文件
     * @param parentFilePath
     * @param classFile
     * @param fileName
     * @return
     */
    public static File createCustomFile(String parentFilePath,String classFile,String fileName) {
        File targetFile = createParentFile(parentFilePath,classFile);
        File file = new File(targetFile, fileName);
        if(file!=null){
            return file;
        }else {
            file.mkdir();
            return file;
        }
    }

}
