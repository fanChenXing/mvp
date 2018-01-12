package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 功能描述：软键盘的工具类
 * Created by 孙中宛 on 2017/8/26.
 */

public class SoftInputUtils {

    //隐藏软键盘
    public static void hideSoftInput(Context context, EditText editText) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //隐藏软键盘
    public static void hideSoftInput(Context context, IBinder windowToken) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //显示软键盘
    public static void showSoftInput(Context context, EditText editText) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }
}
