package com.fancenxing.fanchen.mvppractice.base.factory;

import com.fancenxing.fanchen.mvppractice.base.BasePresenter;
import com.fancenxing.fanchen.mvppractice.base.IBaseView;
import com.fancenxing.fanchen.mvppractice.base.annotation.CreatePresenter;

/**
 * 功能描述：
 * Created by 孙中宛 on 2018/1/12.
 */

public class PresenterFactoryImpl<V extends IBaseView, P extends BasePresenter<V>> implements PresenterFactory<V, P> {

    private Class<P> mClass;

    private PresenterFactoryImpl(Class<P> mClass) {
        this.mClass = mClass;
    }

    @Override
    public P createPresenter() {
        try {
            return mClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("检查是否调用@CreatePresenter注解");
        }
    }

    public static <V extends IBaseView, P extends BasePresenter<V>> PresenterFactoryImpl<V, P> createPresenterFactory(Class<?> clazz) {
        CreatePresenter annotation = clazz.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null) {
            pClass = (Class<P>) annotation.value();
        }
        return pClass == null ? null : new PresenterFactoryImpl<>(pClass);
    }
}
