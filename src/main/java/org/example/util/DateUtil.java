package org.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 处理日期的工具类
 * @author qiufen
 * @date 2022-09-08
 */
public class DateUtil {
     private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 将Date ---> String
     * @param date
     * @return
     */
    public static String format(Date date){
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static String format(Date date,String pattern){
        if(Objects.nonNull(pattern)){
            sdf=new SimpleDateFormat(pattern);
        }
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static Date parse(String dateStr){
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Date parse(String dateStr,String pattern){
        if(Objects.nonNull(pattern)){
            sdf=new SimpleDateFormat(pattern);
        }
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) throws ParseException {
        String str = "2022-09-23";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = DateUtil.parse(str,"yyyy-MM-dd");
        Date date2 = sdf.parse(str);
        System.out.println("date = " + date);
        System.out.println("date2 = " + date2);
    }
}
