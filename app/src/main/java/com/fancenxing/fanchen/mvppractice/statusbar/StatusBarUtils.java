package com.fancenxing.fanchen.mvppractice.statusbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * 功能描述：
 * Created by 孙中宛 on 2018/1/15.
 */

public class StatusBarUtils {

    public void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        } else {
            ViewGroup contentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStartBarHeight(activity);
            View statusBarView = contentView.getChildAt(0);
            if (statusBarView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) statusBarView.getLayoutParams();
                if (lp != null) {
                    if (lp.height != statusBarHeight) {
                        ViewCompat.setFitsSystemWindows(statusBarView, false);
                        lp.topMargin += statusBarHeight;
                        statusBarView.setLayoutParams(lp);
                    } else {
                        statusBarView.setBackgroundColor(color);
                        return;
                    }
                }
            }
            statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, 0, lp);
        }
    }

    public int getStartBarHeight(Context context) {
        Resources res = context.getResources();
        return res.getDimensionPixelSize(res.getIdentifier("status_bar_height", "dimen", "android"));
    }
}
