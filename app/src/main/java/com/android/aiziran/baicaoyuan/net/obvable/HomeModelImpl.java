package com.android.aiziran.baicaoyuan.net.obvable;

import android.content.Context;

import com.android.aiziran.baicaoyuan.net.base.BaseRequest;
import com.android.aiziran.baicaoyuan.net.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.MeiZiBean;
import com.android.aiziran.baicaoyuan.interfaces.models.HomeModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModelImpl implements HomeModel {
    @Override
    public Observable<BaseResult<List<MeiZiBean>>> getDataFromNet(Context context) {
        Observable<BaseResult<List<MeiZiBean>>> observable = BaseRequest.getInstance(context).getService().getMeiZi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    @Override
    public void stopRequest() {

    }

}
