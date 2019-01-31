package com.jiacaizichan.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;



/**
 * 创建日期：2018/7/24 11:31
 * @author lihao
 * decs： 倒计时工具类
 */
public class CountDownTimerUtils extends CountDownTimer {

    private TextView mTv;
    private int flag;
    private Activity activity;
    private Activity activity1;

    private String tvColor;

    public String getTvColor() {
        return tvColor;
    }

    public void setTvColor(String tvColor) {
        this.tvColor = tvColor;
    }

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */

    public CountDownTimerUtils(Activity activity,Activity activity1,View view, int flag,
                               long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.flag = flag;
        this.activity = activity;
        this.activity1 = activity1;
        mTv = (TextView) view;
    }


    @Override
    public void onTick(long millisUntilFinished) {
        switch (flag){
            case 1:
                //引导页面快速进入主页
                mTv.setText(millisUntilFinished / 1000 + "跳过");
                break;
            case 2:
                //获取验证码的倒计时
                mTv.setClickable(false);
                mTv.setTextColor(Color.parseColor("#a0a0a0"));
                mTv.setText(millisUntilFinished/1000+"后重新获取");
                break;
        }
    }

    @Override
    public void onFinish() {
        switch (flag){
            case 1:
                //进入主页
                Intent intent = new Intent(activity,activity1.getClass());
                activity.startActivity(intent);
                activity.finish();
                break;
            case 2:
                mTv.setClickable(true);
                mTv.setText("获取验证码");
                mTv.setTextColor(Color.parseColor(tvColor));
                break;

        }

    }

    public void release(){
        if (this!=null){
            this.cancel();
        }
    }

}
