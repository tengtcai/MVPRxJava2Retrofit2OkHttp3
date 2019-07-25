package com.android.aiziran.baicaoyuan.net.obvable;

import android.content.Context;

import com.android.aiziran.baicaoyuan.base.BaseRequest;
import com.android.aiziran.baicaoyuan.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.AdBean;
import com.android.aiziran.baicaoyuan.interfaces.models.SplashModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashModelImpl implements SplashModel {
    @Override
    public Observable<BaseResult<AdBean>> getAd(Context context) {
        Observable<BaseResult<AdBean>> observable = BaseRequest.getInstance(context).getService().getAd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}