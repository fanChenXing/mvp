package com.fancenxing.fanchen.mvppractice.network;


import com.fancenxing.fanchen.mvppractice.utilities.Constant;
import com.fancenxing.fanchen.mvppractice.utilities.JsonUtils;
import com.fancenxing.fanchen.mvppractice.utilities.LogUtils;

import io.reactivex.functions.Consumer;

/**
 * 功能描述：服务器成功返回后的处理
 * Created by 孙中宛 on 2017/8/16.
 */

public class NormalConsumer<T> implements Consumer<T> {

    private CommonResponseListener<T> responseListener;

    public NormalConsumer(CommonResponseListener<T> responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    public void accept(T t) throws Exception {
        if (t == null) {
            LogUtils.i(NormalConsumer.class, "response == null");
            responseListener.onError(Constant.ERROR_CODE, null);
        } else {
            LogUtils.i(NormalConsumer.class, JsonUtils.parse2Str(t));
            if (t instanceof ResponseModel) {
                ResponseModel model = (ResponseModel) t;
                if (Constant.STATUS_SUCCESS == model.getStatus()) {
                    responseListener.onSuccess(t);
                } else {
                    responseListener.onError(model.getStatus(),model.getMsg());
                }
            } else {
                responseListener.onSuccess(t);
            }
        }
    }
}
