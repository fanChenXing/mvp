package com.fancenxing.fanchen.mvppractice.network;

import android.text.TextUtils;

import com.fancenxing.fanchen.mvppractice.utilities.Constant;
import com.fancenxing.fanchen.mvppractice.utilities.JsonUtils;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 功能描述：
 * Created by 孙中宛 on 2017/8/17.
 */

public class MapList<T> implements Function<ResponseModel, List<T>> {

    private Class clazz;

    public MapList(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> apply(@NonNull ResponseModel responseModel) throws CustomException {
        if (responseModel == null) {
            throw ExceptionUtils.createExceptionInstance(null);
        } else {
            String data = responseModel.getResult();
            if (TextUtils.isEmpty(data) || Constant.STATUS_SUCCESS != responseModel.getStatus()) {
                throw ExceptionUtils.createExceptionInstance(responseModel);
            } else {
                List<T> list = JsonUtils.parse2List(data, clazz);
                if (list == null || list.size() == 0) {
                    throw ExceptionUtils.createExceptionInstance(responseModel);
                } else {
                    return JsonUtils.parse2List(data, clazz);
                }
            }
        }
    }
}
