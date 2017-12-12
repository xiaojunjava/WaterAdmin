package com.sinosoft.wateradmin.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换
 * Created by lvzhixue on 2017/11/3.
 */
public class DateConvert {

    private final static String  time_fmt ="HH:mm:ss";//时间格式
    private final static String  date_fmt ="yyyy-MM-dd";//日期格式
    private final static String  datetime_fmt ="yyyy-MM-dd HH:mm:ss";//日期时间格式


    /**
     * 日期转换成字符串
     * @param date
     * @return str
     *
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期时间转字符
     * @param d
     * @return
     */
    public static String dateTime2Str(Date d){
        return date2MyStr(d,datetime_fmt);
    }
    /**
     * 日期转字符
     * @param d
     * @return
     */
    public static String date2Str(Date d){
        return date2MyStr(d,date_fmt);
    }
    public static String date2MyStr(Date d,String fmt){
        if(d==null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(d);
    }


    public static void main(String[] args) {

        Date date = new Date();
        System.out.println("日期转字符串：" + DateConvert.DateToStr(date));
        System.out.println("字符串转日期：" + DateConvert.StrToDate(DateConvert.DateToStr(date)));

    }
}
