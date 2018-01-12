package com.fancenxing.fanchen.mvppractice.network;

/**
 * 功能描述：一般的服务器返回监听
 * Created by 孙中宛 on 2017/8/16.
 */

public interface CommonResponseListener<T> extends ResponseErrorListener, ResponseSuccessListener<T> {
}
