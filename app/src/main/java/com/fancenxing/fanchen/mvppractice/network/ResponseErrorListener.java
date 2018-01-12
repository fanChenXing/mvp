package com.fancenxing.fanchen.mvppractice.network;

/**
 * 功能描述：服务器返回错误信息的监听
 * Created by 孙中宛 on 2017/8/16.
 */

public interface ResponseErrorListener {
    void onError(int code, String msg);
}
