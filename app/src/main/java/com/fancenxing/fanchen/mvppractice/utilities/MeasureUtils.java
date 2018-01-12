package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 功能描述：尺寸转换的工具类
 * Created by 孙中宛 on 2017/8/17.
 */

public class MeasureUtils {

    public static int dp2px(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (metrics.density * dp);
    }


}
