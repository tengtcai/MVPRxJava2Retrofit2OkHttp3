package com.android.aiziran.baicaoyuan.interfaces.models;

import android.content.Context;

import com.android.aiziran.baicaoyuan.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.MeiZiBean;

import java.util.List;

import io.reactivex.Observable;

public interface HomeModel extends IModel{
    /**
     * 从网络获取数据
     *
     * @return
     */
    Observable<BaseResult<List<MeiZiBean>>> getDataFromNet(Context context);

}
