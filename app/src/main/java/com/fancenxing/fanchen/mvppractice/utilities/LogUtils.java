package com.fancenxing.fanchen.mvppractice.utilities;

import android.util.Log;

/**
 * 类功能描述：Log的工具类
 * Created by 孙中宛 on 2017/8/14.
 */

public class LogUtils {


    public static void i(Class clazz, String info) {
        i(clazz.getName(), info);
    }

    public static void i(String tag, String info) {
        Log.i(tag, info);
    }

    public static void v(Class clazz, String info) {
        v(clazz.getName(), info);
    }

    public static void v(String tag, String info) {
        Log.v(tag, info);
    }

    public static void e(Class clazz, String info) {
        e(clazz.getName(), info);
    }

    public static void e(String tag, String info) {
        Log.e(tag, info);
    }

}
