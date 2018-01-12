package com.fancenxing.fanchen.mvppractice.network;

import java.io.Serializable;

/**
 * 类功能描述：统一返回的实体类
 * Created by 孙中宛 on 2017/8/14.
 */

public class ResponseModel implements Serializable {

    public int status;
    public String result;
    public String msg;

    public ResponseModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
