package com.jiacaizichan.baselibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建日期：2018/5/23 19:02
 * @author lihao
 * decs： 图片压缩工具类
 */
public class BitmapUtil {
    /**
     * 质量压缩方法---bitmap大小不变，保存的文件会变小适合图片上传
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        int flag = 1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 300&&flag==1) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
            if (options<=10){
                options = 10;
                flag = 0;
            }
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }


    /**
     * 质量压缩方法---bitmap大小不变，保存的文件会变小适合图片上传
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image,int size) {
        int flag = 1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > size&&flag==1) { //
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
            if (options<=10){
                options = 10;
                flag = 0;
            }
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }




    /**
     * 质量压缩方法，并保存为文件，返回文件路径
     * @param image
     * @return
     */
    public static String compressImage1(Bitmap image,String parentPath,String classPath) {
        int flag = 1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 90;
        while (baos.toByteArray().length / 1024 > 300&&flag==1) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
            if (options<=10){
                options = 10;
                flag = 0;
            }
        }
        //创建存储图片的文件

        try {
            File iconFile = FileUtil.createImageFile(parentPath,classPath);
            FileOutputStream fos = new FileOutputStream(iconFile);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            Log.d("info", "文件大小"+iconFile.length()/1024);
            return iconFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 保存bitmap到文件中去
     * @param bitmap
     * @return
     * @throws IOException
     */
    public static String saveBitmap(Bitmap bitmap,String parentPath,String classPath) throws IOException {

        File iconFile = FileUtil.createImageFile(parentPath,classPath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(iconFile));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
        return iconFile.getAbsolutePath();
    }


    /***
     * 图片的缩放方法
     * @param bgimage ：源图片资源
     */
    public static Bitmap zoomImage(Bitmap bgimage,int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bgimage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int newWidth = bgimage.getWidth();
        int newHeight = bgimage.getHeight();
        //原图与目标图大小的倍数
        if (baos.toByteArray().length/1024<=size){
            return bgimage;
        }
        float multiple = (baos.toByteArray().length/1024)/2048;
        if (multiple<1){
            multiple = 1;
        }
        double scale =  Math.sqrt(multiple);
        if (scale<=1)
            scale = 1;
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        int scaleWidth = (int) Math.floor((newWidth / scale));
        int scaleHeight = (int) Math.floor(newHeight / scale);

        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bm = Bitmap.createScaledBitmap(bgimage, scaleWidth, scaleHeight, true);
        return bm;
    }


    /***
     * 图片的缩放方法
     * @param bgimage ：源图片资源
     */
    public static Bitmap zoomImage(Bitmap bgimage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bgimage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int newWidth = bgimage.getWidth();
        int newHeight = bgimage.getHeight();
        //原图与目标图大小的倍数
        if (baos.toByteArray().length/1024<=2048){
            return bgimage;
        }
        float multiple = (baos.toByteArray().length/1024)/2048;
        if (multiple<1){
            multiple = 1;
        }
        double scale =  Math.sqrt(multiple);
        if (scale<=1)
            scale = 1;
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        int scaleWidth = (int) Math.floor((newWidth / scale));
        int scaleHeight = (int) Math.floor(newHeight / scale);

        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bm = Bitmap.createScaledBitmap(bgimage, scaleWidth, scaleHeight, true);
        return bm;
    }


    /**
     * 固定宽高压缩法
     * @param image
     * @param wight
     * @param height
     * @return
     */
    public static Bitmap fixedImage(Bitmap image,int wight,int height){
        Bitmap bm = null;
        bm = Bitmap.createScaledBitmap(image, wight, height, true);
        return bm;
    }


    /**
     * 采样率压缩方式
     * @param imagePath
     * @return
     */
    public static Bitmap optionBitmap(String imagePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(imagePath, options);
        return bm;
    }

    public static String savaBitmap(Bitmap bitmap,String parentPath,String classPath){
        try {
            File iconFile = FileUtil.createImageFile(parentPath,classPath);
            FileOutputStream fos = new FileOutputStream(iconFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
            return iconFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String saveBitmap(Bitmap bitmap,String parentPath,String classPath,String fileName){
        try {
            File iconFile = FileUtil.createCustomFile(parentPath,classPath,fileName);
            FileOutputStream fos = new FileOutputStream(iconFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
            return iconFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
