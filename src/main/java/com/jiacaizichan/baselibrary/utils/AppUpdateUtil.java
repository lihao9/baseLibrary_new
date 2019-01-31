package com.jiacaizichan.baselibrary.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.File;

/**
 * 创建日期：2018/5/24 9:22
 * @author lihao
 * decs： App升级工具类
 */
public class AppUpdateUtil {

//    private static String appUri =  "http://www.newxindai.com/file/薪信贷_1.4.0_180614_release.apk";
//    public static File downPath = Environment.getDownloadCacheDirectory();
    /**
     * 获取本地应用的版本号用于应用升级
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }


    /**
     *
     * @param context 上下文
     * @param appUrl apk本地下载路径
     * @param appName apk文件名称 xxx.apk
     * @param info apk下载信息
     */
//    public static void updateApp(Context context, String appUrl,String appName,String info){
//
//        DownloadManager.Request request;
//
//        try {
//            request = new DownloadManager.Request(Uri.parse(appUrl));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        //设置下载类型为.apk
//        request.setMimeType("application/vnd.android.package-archive");
//        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//        File downloadFile = new File(externalFilesDir, appName); //修改
//
//        request.setTitle(info);
//        request.setDescription("应用正在下载");
//        //在通知栏显示下载进度
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            request.allowScanningByMediaScanner();
//            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        }
//        //设置保存下载apk保存路径
//        request.setDestinationUri(Uri.fromFile(downloadFile));
//
//        Context appContext = context.getApplicationContext();
//        DownloadManager manager = (DownloadManager) appContext.getSystemService(Context.DOWNLOAD_SERVICE);
//
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        long requestId = manager.enqueue(request);
//
//        Intent intent = new Intent();
//        intent.setClass(context, MyService.class);
//        intent.putExtra("requestId",requestId);
//        context.startService(intent);
//        //进入下载队列
//        DownloadManager.Query query=new DownloadManager.Query();
//        query.setFilterById(requestId);
//
//
//    }


    /**
     * 从网页下载App
     * @param context
     */
    public static void updateAppForWeb(Context context,String url){
        if (TextUtils.isEmpty(url)){
            LogUtil.e("官网地址为空");
            return;
        }
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }catch (Exception e){
            LogUtil.e(e.getMessage());
        }
    }

    public static void updataAppByOkHttp(final Context context , final String filePath, final String fileName){

    }

    public static Intent getInstallAppIntent(final File file, final boolean isNewTask) {
        if (file == null) return null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        String type = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            data = Uri.fromFile(file);
        } else {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            String authority = Utils.getApp().getPackageName() + ".fileprovider";
            data = FileProvider.getUriForFile(Utils.getApp(), authority, file);
        }
        intent.setDataAndType(data, type);
        return getIntent(intent, isNewTask);
    }

    private static Intent getIntent(final Intent intent, final boolean isNewTask) {
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

    public static void installApk(Context context,File apkFile) {
        String type = "application/vnd.android.package-archive";
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            installApkIntent.setDataAndType(FileProvider.getUriForFile(context, Utils.getApp().getPackageName() + ".fileprovider", apkFile),type);
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), type);
        }

        if (context.getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            context.startActivity(installApkIntent);
        }
    }

}
