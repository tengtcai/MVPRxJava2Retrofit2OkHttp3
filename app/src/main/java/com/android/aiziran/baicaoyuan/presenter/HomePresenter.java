package com.android.aiziran.baicaoyuan.presenter;

import android.content.Context;
import android.util.Log;

import com.android.aiziran.baicaoyuan.base.BaseObserver;
import com.android.aiziran.baicaoyuan.base.BasePresenter;
import com.android.aiziran.baicaoyuan.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.MeiZiBean;
import com.android.aiziran.baicaoyuan.interfaces.models.HomeModel;
import com.android.aiziran.baicaoyuan.interfaces.view.HomeView;
import com.android.aiziran.baicaoyuan.utils.Constance;

import java.util.List;

import io.reactivex.Observable;

public class HomePresenter extends BasePresenter<HomeModel, HomeView> {
    public void getData(Context context) {//这里要注意判空（view和model可能为空）
        if (model != null) {
            Observable<BaseResult<List<MeiZiBean>>> observable = model.getDataFromNet(context);
            observable.subscribe(new BaseObserver<List<MeiZiBean>>() {
                @Override
                protected void onSuccees(BaseResult<List<MeiZiBean>> t) throws Exception {
                    List<MeiZiBean> results = t.getResults();
                    if (getView() != null) {
                        if (results.isEmpty()) {//没有数据 显示没有数据的布局
                            getView().showNoData();
                        } else {
                            getView().setData(t.getResults());
                        }
                    }
                }

                @Override
                protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                    if(isNetWorkError){//如果网络有问题就显示网络错误的布局
                        if (getView() != null) {
                            getView().showNoNet();
                            getView().showToast("请检查网络");
                        }
                    }
                }

                @Override
                protected void onCodeError(BaseResult<List<MeiZiBean>> t) throws Exception {
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

}
