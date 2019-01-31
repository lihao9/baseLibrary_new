package com.jiacaizichan.baselibrary.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AlertDialogUtil {

    private View view;
    private AlertDialog alertDialog;
    private int resourceId;
    private Activity activity;
    private InitDialog initDialog;
    //显示等级（是否可以点击外面取消）
    private int showGrade;

    public int getShowGrade() {
        return showGrade;
    }

    public void setShowGrade(int showGrade) {
        this.showGrade = showGrade;
    }

    public InitDialog getInitDialog() {
        return initDialog;
    }

    public void setInitDialog(InitDialog initDialog) {
        this.initDialog = initDialog;
    }

    public AlertDialogUtil(Activity activity, int resourceId){
        this.activity = activity;
        this.resourceId = resourceId;
    }

    public void init(int flag){
        if (initDialog==null){
            throw new NullPointerException("请先实现接口");
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            view = LayoutInflater.from(activity).inflate(resourceId,null,false);
            initDialog.initDialog(view,flag);
            alertDialog = builder.create();
            switch (showGrade){
                case 0:
                    break;
                case 1:
                    alertDialog.setCanceledOnTouchOutside(false);
//                    alertDialog.setCancelable(false);
                    break;
                case 2:
//                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setCancelable(false);
                    break;
            }
//            alertDialog = (AlertDialog) new Dialog(activity);

            alertDialog.setView((activity).getLayoutInflater().inflate(resourceId, null));

            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.show();
            alertDialog.getWindow().setContentView(view);
            view.post(new Runnable() {
                @Override
                public void run() {
                    int width = view.getWidth();
                    WindowManager.LayoutParams attributes = alertDialog.getWindow().getAttributes();
                    attributes.width = width;
                    alertDialog.getWindow().setAttributes(attributes);
                    alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                }
            });
        }
    }

    public void setIsDismiss(boolean isDismiss){
        alertDialog.setCanceledOnTouchOutside(isDismiss);
    }

    public void showDialog(){
        alertDialog.show();
    }

    public void dismissDialog(){
        if (alertDialog!=null&&alertDialog.isShowing()){
            alertDialog.dismiss();
        }

    }

    public interface InitDialog{
        void initDialog(View view,int flag);
    }

}
