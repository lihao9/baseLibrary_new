package com.jiacaizichan.baselibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建日期：2018/5/23 19:01
 * @author lihao
 * decs： 正则匹配工具类
 */
public class MatcherUtil {
    public static final String PHONE = "^((13[0-9])|(19[0-9])|(14[0-9])|(16([0-9]))|(15([0-9]))|(17[0-9])|(18[0-9]))\\d{8}$";
    public static final String PASSWORD = "^[a-zA-Z0-9]{6,12}$";
    public static final String BANKCARD = "^\\d{15,19}$";
    public static final String IDCARD = "^\\d{15}$|^\\d{17}[0-9Xx]$";


    public static final String QQNUM = "^\\d{6,12}$";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    static Pattern p;
    static Matcher m;

    public static boolean isPhone(String value){
        p = Pattern.compile(PHONE);
        m = p.matcher(value);
        return m.matches();
    }

    public static boolean isEmail(String value){
        p = Pattern.compile(REGEX_EMAIL);
        m = p.matcher(value);
        return m.matches();
    }

    public static boolean isIDCard(String value) {
        p = Pattern.compile(IDCARD);
        m = p.matcher(value);
        return m.matches();
    }

    public static boolean isBankCard(String value) {
        p = Pattern.compile(BANKCARD);
        m = p.matcher(value);
        return m.matches();
    }

    public static boolean isPassword(String value) {
        p = Pattern.compile(PASSWORD);
        m = p.matcher(value);
        return m.matches();
    }

    public static boolean isLegalName(String name){
        if (name.contains("·") || name.contains("•")){
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }
    }

}
