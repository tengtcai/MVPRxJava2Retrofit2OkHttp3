package com.android.aiziran.baicaoyuan.net.obvable;

import android.content.Context;
import android.util.Log;

import com.android.aiziran.baicaoyuan.base.BaseRequest;
import com.android.aiziran.baicaoyuan.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.MeiZiBean;
import com.android.aiziran.baicaoyuan.interfaces.models.HomeModel;
import com.android.aiziran.baicaoyuan.utils.Constance;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomeModelImpl implements HomeModel {
    @Override
    public Observable<BaseResult<List<MeiZiBean>>> getDataFromNet(Context context) {
        Observable<BaseResult<List<MeiZiBean>>> observable = BaseRequest.getInstance(context).getService().getMeiZi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
