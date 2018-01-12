package com.fancenxing.fanchen.mvppractice.base;

/**
 * 功能描述：presenter的基类
 * Created by 孙中宛 on 2018/1/12.
 */

public abstract class BasePresenter<V extends IBaseView> {

    private V mView;

    public BasePresenter(){

    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public void destroy(){
        detachView();
    }
}
