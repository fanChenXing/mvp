package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 功能描述：Toast显示提示信息
 * Created by 孙中宛 on 2017/8/15.
 */

public class ToastUtils {

    private static Toast toast = null;

    /**
     * 显示提示信息
     *
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示提示信息
     *
     * @param context
     * @param resId   要显示的String的资源ID
     */
    public static void show(Context context, int resId) {
        if (resId == -1) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(resId);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
