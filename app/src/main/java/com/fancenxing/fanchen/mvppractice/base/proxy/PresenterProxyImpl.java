package com.fancenxing.fanchen.mvppractice.base.proxy;

import com.fancenxing.fanchen.mvppractice.base.BasePresenter;
import com.fancenxing.fanchen.mvppractice.base.IBaseView;
import com.fancenxing.fanchen.mvppractice.base.factory.PresenterFactory;

/**
 * 功能描述：
 * Created by 孙中宛 on 2018/1/12.
 */

public class PresenterProxyImpl<V extends IBaseView, P extends BasePresenter<V>> implements PresenterProxy<V, P> {

    private PresenterFactory<V, P> factory;
    private P presenter;

    public PresenterProxyImpl(PresenterFactory<V, P> factory) {
        setPresenterFactory(factory);
    }

    @Override
    public void setPresenterFactory(PresenterFactory<V, P> factory) {
        if (presenter != null) {
            throw new RuntimeException("请在getPresenter方法之前调用");
        }
        this.factory = factory;
    }

    public P getPresenter() {
        if (factory != null && presenter == null) {
            presenter = factory.createPresenter();
        }
        return presenter;
    }

    @Override
    public void onAttachView(V view) {
        getPresenter().attachView(view);
    }

    @Override
    public void detachView() {
        getPresenter().detachView();
    }

    @Override
    public void onDestroy() {
        getPresenter().destroy();
    }
}
