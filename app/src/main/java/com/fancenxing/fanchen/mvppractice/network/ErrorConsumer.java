package com.fancenxing.fanchen.mvppractice.network;


import com.fancenxing.fanchen.mvppractice.utilities.Constant;
import com.fancenxing.fanchen.mvppractice.utilities.JsonUtils;
import com.fancenxing.fanchen.mvppractice.utilities.LogUtils;

import io.reactivex.functions.Consumer;

/**
 * 功能描述：服务器返回错误的处理
 * Created by 孙中宛 on 2017/8/16.
 */

public class ErrorConsumer implements Consumer<Throwable> {

    private ResponseErrorListener errorListener;

    public ErrorConsumer(ResponseErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void accept(Throwable t) throws Exception {
        String msg;
        int code;
        if (t instanceof CustomException) {
            msg = ((CustomException) t).getData();
            code = ((CustomException) t).getStatus();
        } else {
            msg = t.getMessage();
            code = Constant.ERROR_CODE;
        }
        LogUtils.e("consumer", "error" + code + "---" + msg + "--" + JsonUtils.parse2Str(t));
        errorListener.onError(code, msg);
    }
}
