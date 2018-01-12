package com.fancenxing.fanchen.mvppractice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fancenxing.fanchen.mvppractice.base.factory.PresenterFactoryImpl;
import com.fancenxing.fanchen.mvppractice.base.proxy.PresenterProxy;
import com.fancenxing.fanchen.mvppractice.base.proxy.PresenterProxyImpl;

/**
 * 功能描述：activity的基类
 * Created by 孙中宛 on 2018/1/12.
 */

public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>> extends AppCompatActivity {

    private PresenterProxy<V, P> presenterProxy;

//    public void setPresenterProxy(PresenterProxy<V, P> presenterProxy) {
//        if (presenterProxy == null) {
//            throw new RuntimeException("请在getPresenterProxy之前调用");
//        }
//        this.presenterProxy = presenterProxy;
//    }

    public PresenterProxy<V, P> getPresenterProxy() {
        if (presenterProxy == null) {
            presenterProxy = new PresenterProxyImpl<>(PresenterFactoryImpl.<V, P>createPresenterFactory(getClass()));
        }
        return presenterProxy;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getPresenterProxy().onAttachView((V) this);
        init();
    }

    public abstract int getLayoutId();

    public abstract void init();

    @Override
    protected void onDestroy() {
        getPresenterProxy().onDestroy();
        super.onDestroy();
    }
}
