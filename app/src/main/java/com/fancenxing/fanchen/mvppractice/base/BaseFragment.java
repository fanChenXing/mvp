package com.fancenxing.fanchen.mvppractice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancenxing.fanchen.mvppractice.base.factory.PresenterFactoryImpl;
import com.fancenxing.fanchen.mvppractice.base.proxy.PresenterProxy;
import com.fancenxing.fanchen.mvppractice.base.proxy.PresenterProxyImpl;

/**
 * 功能描述：
 * Created by 孙中宛 on 2018/1/12.
 */

public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>> extends Fragment {

    private PresenterProxy<V, P> presenterProxy;

    public PresenterProxy<V, P> getPresenterProxy() {
        if (presenterProxy == null) {
            presenterProxy = new PresenterProxyImpl<>(PresenterFactoryImpl.<V, P>createPresenterFactory(getClass()));
        }
        return presenterProxy;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getPresenterProxy().onAttachView((V) this);
        View view = inflater.inflate(getLayoutId(), container, false);
        init(getArguments());
        return view;
    }

    public abstract void init(Bundle bundle);

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenterProxy().onDestroy();
    }
}
