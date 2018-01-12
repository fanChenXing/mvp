package com.fancenxing.fanchen.mvppractice.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 类功能描述：网络的工具类
 * Created by 孙中宛 on 2017/8/14.
 */

public class NetUtils {

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkReachable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        } else {
            return info.isAvailable();
        }
    }
}
