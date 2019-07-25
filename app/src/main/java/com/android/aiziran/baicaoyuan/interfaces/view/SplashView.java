package com.android.aiziran.baicaoyuan.interfaces.view;

import com.android.aiziran.baicaoyuan.bean.AdBean;

public interface SplashView extends IView {
    /**
     * 设置数据
     *
     * @param data
     */
    void setData(AdBean data);

    void onRequestStart();//请求开始

    void onRequestEnd();//请求结束

    void onGoMain();  //跳转首页

}
