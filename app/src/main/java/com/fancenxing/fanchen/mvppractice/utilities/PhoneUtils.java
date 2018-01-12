package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 功能描述：
 * Created by 孙中宛 on 2017/10/10.
 */

public class PhoneUtils {

    public static void dial(Context context,String phone){
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone));
        context.startActivity(intent);
    }
}
