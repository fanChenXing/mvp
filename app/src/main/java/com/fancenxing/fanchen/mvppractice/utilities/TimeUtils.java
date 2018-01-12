package com.fancenxing.fanchen.mvppractice.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述：格式化时间
 * Created by 孙中宛 on 2017/8/19.
 */

public class TimeUtils {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_MINUTE = "yyyy年MM月dd日  HH:mm";

    public static final String FORMAT_DAY = "yyyy 年 MM 月 dd 日";

    /**
     * @param secondTime 秒
     * @param format
     * @return
     */
    public static String formatSecondTime(Long secondTime, String format) {
        if (secondTime == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(secondTime * 1000));
    }

    /**
     * @param millis 秒
     * @param format
     * @return
     */
    public static String formatTime(Long millis, String format) {
        if (millis == null) {
            return "";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String str = dateFormat.format(new Date(millis));
        return str;
    }

    /**
     * @param millis 秒
     * @return
     */
    public static String getMonth(Long millis) {
        if (millis == null) {
            return "";
        }
        return getMonth(new Date(millis));
    }

    /**
     * @param date 秒
     * @return
     */
    public static String getMonth(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        String str = dateFormat.format(date);
        if (str.startsWith("0")) {
            str = str.substring(1);
        }
        return str;
    }

    /**
     * @param time   秒
     * @param format
     * @return
     */
    public static String formatTime(Date time, String format) {
        if (time == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }

    //获取Calendar对应的日期是星期几
    public static String getDayOfWeek(Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String str;
        switch (day) {
            case 1:
                str = "日";
                break;
            case 2:
                str = "一";
                break;
            case 3:
                str = "二";
                break;
            case 4:
                str = "三";
                break;
            case 5:
                str = "四";
                break;
            case 6:
                str = "五";
                break;
            case 7:
                str = "六";
                break;
            default:
                str = "";
        }
        return str;
    }

    //获取date是星期几
    public static String getDayOfWeek(Date date) {
        if (date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getDayOfWeek(calendar);
    }

    //判断是否是同一天
    public static boolean isSameDay(long firstTime, long secondTime) {
        if (formatTime(firstTime, FORMAT_DAY).equals(formatTime(secondTime, FORMAT_DAY))) {
            return true;
        } else {
            return false;
        }
    }

//    public static int getHourOfStage(String stage) {
//        if (TextUtils.isEmpty(stage)) {
//            return 0;
//        }
//        int hour;
//        switch (stage) {
//            case Constant.AM:
//                hour = 8;
//                break;
//            case Constant.PM:
//                hour = 14;
//                break;
//            case Constant.NIGHT:
//                hour = 18;
//                break;
//            default:
//                hour = 0;
//        }
//        return hour;
//    }

//    /**
//     * 获取上课时间millis
//     *
//     * @param year
//     * @param month
//     * @param day
//     * @param stage 上午／下午／晚上
//     * @return
//     */
//    public static long getTimeMillis(int year, int month, int day, String stage) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month - 1);
//        calendar.set(Calendar.DAY_OF_MONTH, day);
//        calendar.set(Calendar.HOUR_OF_DAY, getHourOfStage(stage));
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTimeInMillis();
//    }

    /**
     * 上课时间是否超过当前时间
     *
     * @param second 秒
     * @return
     */
    public static boolean isOutOfDate(long second) {
        if (System.currentTimeMillis() >= getMillis(second)) {
            return true;
        } else {
            return false;
        }
    }

    public static long getMillis(long second) {
        return second * 1000;
    }
}
