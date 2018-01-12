package com.fancenxing.fanchen.mvppractice.network;


import com.fancenxing.fanchen.mvppractice.utilities.LogUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 功能描述：统一错误处理
 * Created by 孙中宛 on 2017/8/16.
 */

public class CommonErrorHandle<T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull Throwable t) throws Exception {
        return Observable.error(handleThrowable(t));
    }

    private CustomException handleThrowable(Throwable throwable) {
        CustomException exception;
        if (throwable instanceof CustomException) {
            exception = (CustomException) throwable;
        } else {
            exception = new CustomException(throwable);
        }
        LogUtils.e("error", "status--  " + exception.getStatus() + "--msg---" + exception.getData());
        return exception;
    }
}
