package com.fancenxing.fanchen.mvppractice.base.annotation;

import com.fancenxing.fanchen.mvppractice.base.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 功能描述：用于创建presenter
 * Created by 孙中宛 on 2018/1/12.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BasePresenter> value();
}
