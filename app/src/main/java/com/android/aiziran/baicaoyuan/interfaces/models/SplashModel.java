package com.android.aiziran.baicaoyuan.interfaces.models;

import android.content.Context;

import com.android.aiziran.baicaoyuan.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.AdBean;

import io.reactivex.Observable;

public interface SplashModel extends IModel {
    /**
     * 从网络获取数据
     *
     * @return
     */
    Observable<BaseResult<AdBean>> getAd(Context context);
}
