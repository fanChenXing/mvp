package com.fancenxing.fanchen.mvppractice.base.factory;

import com.fancenxing.fanchen.mvppractice.base.BasePresenter;
import com.fancenxing.fanchen.mvppractice.base.IBaseView;

/**
 * 功能描述：用于创建presenter的工厂
 * Created by 孙中宛 on 2018/1/12.
 */

public interface PresenterFactory<V extends IBaseView, P extends BasePresenter<V>> {

    //创建presenter
    P createPresenter();

}
