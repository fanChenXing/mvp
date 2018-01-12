package com.fancenxing.fanchen.mvppractice.network;

import android.text.TextUtils;

import com.fancenxing.fanchen.mvppractice.utilities.Constant;
import com.fancenxing.fanchen.mvppractice.utilities.JsonUtils;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 功能描述：
 * Created by 孙中宛 on 2017/8/24.
 */

public class MapObject<T> implements Function<ResponseModel, T> {

    private Class clazz;

    public MapObject(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public T apply(@NonNull ResponseModel responseModel) throws CustomException {
        if (responseModel == null) {
            throw ExceptionUtils.createExceptionInstance(null);
        } else {
            String data = responseModel.getResult();
            if (TextUtils.isEmpty(data) || Constant.STATUS_SUCCESS != responseModel.getStatus()) {
                throw ExceptionUtils.createExceptionInstance(responseModel);
            } else if (clazz == String.class) {
                return (T) data;
            } else {
                return JsonUtils.parse2Object(data, clazz);
            }
        }
    }
}
