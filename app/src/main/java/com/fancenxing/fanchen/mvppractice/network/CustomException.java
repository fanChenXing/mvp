package com.fancenxing.fanchen.mvppractice.network;


import com.fancenxing.fanchen.mvppractice.utilities.Constant;

/**
 * 功能描述：
 * Created by 孙中宛 on 2017/8/17.
 */

public class CustomException extends Exception {
    private int status;
    private String data;

    public CustomException(int status, String data) {
        this.status = status;
        this.data = data;
    }

    public CustomException(Throwable cause) {
        super(cause);
        data = cause.getMessage();
        status = Constant.ERROR_CODE;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
