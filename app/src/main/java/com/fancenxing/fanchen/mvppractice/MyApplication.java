package com.fancenxing.fanchen.mvppractice;

import android.app.Application;

import com.fancenxing.fanchen.mvppractice.utilities.ContextHolder;

/**
 * 功能描述：
 * Created by 孙中宛 on 2018/1/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.initContext(this);
    }
}
