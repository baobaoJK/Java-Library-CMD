package com.ksamar.library.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author KSaMar
 * 图书管理系统 - 日期转换
 */
public class TimeConversion {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TimeConversion() { }

    public static String getFormatDate(Date date) {
        return formatter.format(date);
    }

    public static Date setFormatDate(String str) {
        Date date = new Date();
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
