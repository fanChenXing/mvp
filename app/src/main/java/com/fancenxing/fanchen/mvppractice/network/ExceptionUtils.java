package com.fancenxing.fanchen.mvppractice.network;


import com.fancenxing.fanchen.mvppractice.utilities.Constant;

/**
 * 功能描述：创建异常的工具类
 * Created by 孙中宛 on 2017/8/26.
 */

public class ExceptionUtils {

    public static CustomException createExceptionInstance(ResponseModel responseModel) {
        int code;
        String msg;
        if (responseModel == null) {
            code = Constant.ERROR_CODE;
            msg = Constant.ERROR_SERVER;
        } else {
            code = responseModel.getStatus();
            msg = responseModel.getMsg();
        }
        return new CustomException(code, msg);
    }
}
