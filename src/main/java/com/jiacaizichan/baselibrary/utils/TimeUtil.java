package com.jiacaizichan.baselibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 创建日期：2018/5/23 18:59 
 * @author lihao
 * decs： 时间转化工具类 
 */
public class TimeUtil {
    /**
     * 日期转为汉字.
     * 把日期转换成汉字 dateToChinese("2002/01/01","/") out 二零零二年一月一日
     * 或dateToChinese("2002-01-01","-") out 二零零二年一月一日
     *
     * @param sDate 日期字符串
     * @param DelimeterChar 分隔符
     * @return 中文日期字符串
     * @since BASE 0.1
     */

//    public static String dateToChinese(String sDate, String DelimeterChar) {
//        String restr = "";
//        String tmpArr[] = sDate.split(DelimeterChar);
//        String dArr[] = {
//                "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
//        for (int i = 0; i < 10; i++) {
//            Integer x = new Integer(i);
//            String temp = x.toString();
//            tmpArr[0] = tmpArr[0].replaceAll(temp, dArr[i]);
//        }
//        tmpArr[0] = tmpArr[0] + "年";
//        if (tmpArr[1].length() == 1) {
//            tmpArr[1] = dArr[Integer.parseInt(tmpArr[1])] + "月";
//        }
//        else {
//            if (tmpArr[1].substring(0, 1).equals("0")) {
//                tmpArr[1] =
//                        dArr[Integer.parseInt(tmpArr[1].substring(tmpArr[1].length() - 1,
//                                tmpArr[1].length()))]
//                                + "月";
//            }
//            else {
//                tmpArr[1] =
//                        "十"
//                                +
//                                dArr[Integer.parseInt(tmpArr[1].substring(tmpArr[1].length() - 1,
//                                        tmpArr[1].length()))]
//                                + "月";
//                tmpArr[1] = tmpArr[1].replaceAll("零", "");
//            }
//
//        }
//        if (tmpArr[2].length() == 1) {
//            tmpArr[2] = dArr[Integer.parseInt(tmpArr[2])] + "日";
//        }
//        else {
//            if (tmpArr[2].substring(0, 1).equals("0")) {
//                tmpArr[2] =
//                        dArr[Integer.parseInt(tmpArr[2].substring(tmpArr[2].length() - 1,
//                                tmpArr[2].length()))]
//                                + "日";
//            }
//            else {
//                tmpArr[2] =
//                        dArr[Integer.parseInt(tmpArr[2].substring(0, 1))]
//                                + "十"
//                                +
//                                dArr[Integer.parseInt(tmpArr[2].substring(tmpArr[2].length() - 1,
//                                        tmpArr[2].length()))]
//                                + "日";
//                tmpArr[2] = tmpArr[2].replaceAll("零", "");
//            }
//        }
//        return tmpArr[0] + tmpArr[1] + tmpArr[2];
//    }

    public static String DATEFOEMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static String DATEFOEMAT3 = "yyyy-MM-dd HH:mm";
    public static String DATEFOEMAT2 = "yyyy-MM-dd";


    /**
     * 毫秒转标准格式时间
     * @param _ms
     * @return
     */
    public static String ms2Date(long _ms){
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat(DATEFOEMAT1, Locale.getDefault());
        return format.format(date);
    }

    public static String ms2DateOnlyDay(long _ms){
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat(DATEFOEMAT2, Locale.getDefault());
        return format.format(date);
    }

    /**
     * 字符时间转时间戳
     * @param _data
     * @return
     */
    public static long Date2ms(String _data){
        SimpleDateFormat format = new SimpleDateFormat(DATEFOEMAT1);
        try {
            Date date = format.parse(_data);
            return date.getTime();
        }catch(Exception e){
            return 0;
        }
    }









    /**
     * 日期转换成格式化字符串.
     * @param date 日期字符串
     * @param format 有效的日期格式,如：yyyy-MM-dd
     * @return String 格式化字符串
     * @since BASE 0.1
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 格式化中文日期,转换成"-"分隔的日期格式.
     * <b>注意：</b>现不支持带时间的中文日期转换.
     *
     * @param strDate 中文日期，如：2000年10月01日
     * @return String "-"分隔的日期格式,如：2000-10-01
     * @since BASE 0.1
     */
    public static String formatChineseDate(String strDate) {
        strDate = strDate.replaceAll("[日]", "");

        if (strDate.endsWith("月"))
            strDate = strDate.substring(strDate.length() - 1, strDate.length());
        if (strDate.endsWith("年"))
            strDate = strDate.substring(strDate.length() - 1, strDate.length());

        return strDate.replaceAll("[年,月]", "-");
    }

    /**
     * 获得某一日期对应的星期五是哪一天.
     *
     * @param date 日期
     * @return Date 星期五的日期
     * @since BASE 0.1
     */
    public static Date getEndWeekDay(Date date) {
        Date dtRet = new Date();

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(GregorianCalendar.DATE, 6 - gc.get(GregorianCalendar.DAY_OF_WEEK));
        dtRet = gc.getTime();

        return dtRet;
    }

    /**
     * 获得某一日期对应的星期一是哪一天.
     *
     * @param date 日期
     * @return Date 星期一的日期
     * @since BASE 0.1
     */
    public static Date getFirstWeekDay(Date date) {
        Date dtRet = new Date();

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(GregorianCalendar.DATE,
                (-1) * (gc.get(GregorianCalendar.DAY_OF_WEEK) - 2));
        dtRet = gc.getTime();

        return dtRet;
    }

    /**
     * 计算某一日期之前或之后的日期.
     * @param date 参考日期
     * @param days 之前或之后的天数,可以为负值
     * @return 计算后的日期
     * @since BASE 0.1
     */
    public static Date relativeDate(Date date, int days) {
        String year, month, day;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * 根据当前日期与天数间隔，得到相对日期.
     * @param days 相对天数
     * @return Date 计算后的日期
     * @since BASE 0.1
     */
    public static Date relativeDate(int days) {
        return relativeDate(new Date(), days);
    }

    /**
     *  根据当前日期与月份间隔，得到相对日期.
     * @param months 相对月数
     * @return Date 计算后的日期
     * @since BASE 0.1
     */
    public static Date relativeMonth(int months) {
        return relativeDate(new Date(), months);
    }

    /**
     * 根据当前日期与月份间隔，得到相对日期.
     * @param date 日期
     * @param months 相对月数
     * @return 计算后的日期
     * @since BASE 0.1
     */
    public static Date relativeMonth(Date date, int months) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DAY_OF_MONTH, months);
        return gc.getTime();
    }

    /**
     * 字符串转换成日期.
     *
     * @param strDate 日期
     * @param strFormat 格式
     * @return Date 转换后的日期
     * @since BASE 0.1
     */
    public static Date toDate(String strDate, String strFormat) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            return sdf.parse(strDate);
        }
        catch (ParseException e) {
            return null;
        }
    }


    /**
     * 字符串转换成sql日期.
     *
     * @param strDate String 日期
     * @param strFormat String 格式
     * @return Date 转换后的日期类型
     * @since BASE 0.1
     */
    public static java.sql.Date toSqlDate(String strDate, String strFormat) {
        Date utilDate = toDate(strDate, strFormat);
        if (utilDate == null)
            return null;
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * 字符串转换成Timestamp日期.
     *
     * @param strDate String 日期
     * @param strFormat String 格式
     * @return Timestamp 转换后的日期类型
     * @since BASE 0.1
     */
    public static java.sql.Timestamp toSqlTimestamp(String strDate, String strFormat) {
        Date utilDate = toDate(strDate, strFormat);
        if (utilDate == null)
            return null;
        return new java.sql.Timestamp(utilDate.getTime());
    }
    /**
     * 得到当前日期前后n天日期
     *
     */
    public static String getDate(int num) {
        long time = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * num);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        Date date = new Date();
        if (time > 0) {
            date.setTime(time);
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
