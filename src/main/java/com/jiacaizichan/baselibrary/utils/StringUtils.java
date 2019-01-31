package com.jiacaizichan.baselibrary.utils;

import android.text.TextUtils;

public class StringUtils {

    /**
     * 判断参数是不是都为空
     * @param arg
     * @return
     */
    public static boolean stringsIsEmpty(String ... arg){
        boolean isEmpty = false;
        for (int i = 0;i<arg.length;i++){
            if (TextUtils.isEmpty(arg[i])){
                isEmpty = true;
                return isEmpty;
            }
        }
        return isEmpty;
    }


    public static boolean stringsIsNotEmpty(String ... arg){
        boolean isEmpty = false;
        for (int i = 0;i<arg.length;i++){
            if (!TextUtils.isEmpty(arg[i])){
                isEmpty = true;
                return isEmpty;
            }
        }
        return isEmpty;
    }


    public static boolean isPassWord(String ... arg){
        boolean isPassword = true;
        for (int i = 0; i <arg.length; i++) {
            if (!MatcherUtil.isPassword(arg[i])){
                isPassword = false;
                return isPassword;
            }
        }
        return isPassword;
    }
}
