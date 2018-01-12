package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;

/**
 * 类功能描述：保存ApplicationContext,适应于需要全局Context的情景
 * Created by 孙中宛 on 2017/8/14.
 */

public class ContextHolder {


    private static Context applicationContext;

    private ContextHolder() {
    }

    public static void initContext(Context context) {
        applicationContext = context;
    }

    public static Context getContext() {
        return applicationContext;
    }
}
