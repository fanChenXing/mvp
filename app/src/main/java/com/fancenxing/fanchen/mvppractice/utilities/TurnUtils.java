package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * 功能描述：Activity跳转的工具类
 * Created by 孙中宛 on 2017/8/24.
 */

public class TurnUtils {

    public static void turn2NextActivity(Context context, @NonNull Class clazz) {
        turn2NextActivity(context, clazz,null);
    }

    public static void turn2NextActivity(Context context,@NonNull Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
