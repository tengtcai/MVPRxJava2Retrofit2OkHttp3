package com.android.aiziran.baicaoyuan.presenter;

import android.content.Context;

import com.android.aiziran.baicaoyuan.net.base.BaseObserver;
import com.android.aiziran.baicaoyuan.base.BasePresenter;
import com.android.aiziran.baicaoyuan.net.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.AdBean;
import com.android.aiziran.baicaoyuan.interfaces.models.SplashModel;
import com.android.aiziran.baicaoyuan.interfaces.view.SplashView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class SplashPresenter extends BasePresenter<SplashModel, SplashView> {
    public void getAd(Context context) {//这里要注意判空（view和model可能为空）
        if (model != null) {
            Observable<BaseResult<AdBean>> observable = model.getAd(context);
            observable.timeout(3, TimeUnit.SECONDS)
            .subscribe(new BaseObserver<AdBean>() {
                @Override
                protected void onSuccees(BaseResult<AdBean> t) throws Exception {
                    AdBean results = t.getResults();
                    if (getView() != null) {
                        if(results != null) {//含有广告
                            getView().setData(results);
                        }else {//没有广告
                            getView().onGoMain();
                        }
                    }
                }

                @Override
                protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                    getView().onGoMain();
                }

                @Override
                protected void onCodeError(BaseResult<AdBean> t) throws Exception {
                    super.onCodeError(t);
                    if (getView() != null) {
                        getView().showToast(t.getMessage());//发生错误提示用户
                    }
                }

                @Override
                protected void onRequestStart() {
                    super.onRequestStart();
                    if (getView() != null) {
                        getView().onRequestStart();
                    }
                }

                @Override
                protected void onRequestEnd() {
                    super.onRequestEnd();
                    if (getView() != null) {
                        getView().onRequestEnd();
                    }
                }
            });
        }
    }

    @Override
    protected void onViewDestroy() {//销毁Activity时的操作，可以停止当前的model
        if (model != null) {
            model.stopRequest();
        }
    }
}
