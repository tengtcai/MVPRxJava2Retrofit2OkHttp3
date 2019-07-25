package com.android.aiziran.baicaoyuan.interfaces.view;

import com.android.aiziran.baicaoyuan.bean.MeiZiBean;

import java.util.List;

public interface HomeView extends IView{
    /**
     * 设置数据
     *
     * @param data
     */
    void setData(List<MeiZiBean> data);

    void showNoNet();  //显示没有网络的布局

    void showNoData(); //显示没有数据的布局

    void onRequestStart();//请求开始

    void onRequestEnd();//请求结束
}
