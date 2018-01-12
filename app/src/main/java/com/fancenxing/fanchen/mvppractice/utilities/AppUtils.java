package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

/**
 * 功能描述:获取app相关的信息
 * Created by 孙中宛 on 2017/9/28.
 */

public class AppUtils {

    /**
     * 获取版本名
     *
     * @return
     */
    public static String getVersionName() {
        return getPackageInfo(ContextHolder.getContext()).versionName;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode() {
        return getPackageInfo(ContextHolder.getContext()).versionCode;
    }

    /**
     * 获取应用包信息
     *
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            PackageManager pm = context.getPackageManager();
            info = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }


    public static void installApk(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
