package com.aguo.wxpush.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 将Date对象转换为指定格式的字符串
     *
     * @param date   Date对象
     * @param format 格式化字符串
     * @return Date对象的字符串表达形式
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 根据日期获取星期
     * liuzhu
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String daysBetween(String startDate,String endDate) throws ParseException {
        long nd = 1000 * 24 * 60 * 60;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置时间格式
        Date newStartDate=sdf.parse(startDate);
        Date newEndDate=sdf.parse(endDate);
        long diff = (newEndDate.getTime()) - (newStartDate.getTime()); //计算出毫秒差
        // 计算差多少天
        String day = diff / nd +"";
        return day;
    }


}
