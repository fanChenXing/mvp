package com.fancenxing.fanchen.mvppractice.base.proxy;

import com.fancenxing.fanchen.mvppractice.base.BasePresenter;
import com.fancenxing.fanchen.mvppractice.base.IBaseView;
import com.fancenxing.fanchen.mvppractice.base.factory.PresenterFactory;

/**
 * 功能描述：presenter的代理类
 * Created by 孙中宛 on 2018/1/12.
 */

public interface PresenterProxy<V extends IBaseView,P extends BasePresenter<V>> {

    void setPresenterFactory(PresenterFactory<V,P> factory);

    void onAttachView(V view);

    void detachView();

    void onDestroy();
}
