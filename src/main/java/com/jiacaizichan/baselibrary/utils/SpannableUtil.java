package com.jiacaizichan.baselibrary.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

/**
 * 创建日期：2018/7/25 9:08
 * @author lihao
 * decs： 富文本工具类
 */
public class SpannableUtil {


    private SpannableString spannableString;

    public SpannableUtil(String content){
        this.spannableString = new SpannableString(content);
    }

    /**
     * set spannableString color
     * @param start
     * @param end
     * @param color
     * @return
     */
    public SpannableUtil setColor(int start, int end, String color){
        this.spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(color)),start,end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableUtil setSize(float size,int start,int end){
        this.spannableString.setSpan(new RelativeSizeSpan(size),start,end,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableUtil setStyle(StyleSpan styleSpan,int start,int end){
        this.spannableString.setSpan(styleSpan,start,end,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableUtil setOnClick(ClickableSpan clickableSpan,int start,int end){
        this.spannableString.setSpan(clickableSpan,start,end,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public int getStrLenght(){
        return TextUtils.isEmpty(spannableString)?0:spannableString.length();
    }


    public SpannableString getSpannableString() {
        return spannableString;
    }


}
