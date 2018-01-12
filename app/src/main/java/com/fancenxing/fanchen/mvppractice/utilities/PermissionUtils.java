package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * 功能描述：检查是否有权限
 * Created by 孙中宛 on 2017/11/6.
 */

public class PermissionUtils {

    public static boolean havePermission(Context context, String permission) {
        boolean isHave;
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            isHave = true;
        } else {
            isHave = false;
        }
        return isHave;
    }

}
