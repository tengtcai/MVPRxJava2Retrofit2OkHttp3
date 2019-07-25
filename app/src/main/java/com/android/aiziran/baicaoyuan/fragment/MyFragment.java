package com.android.aiziran.baicaoyuan.fragment;

import com.android.aiziran.baicaoyuan.R;
import com.android.aiziran.baicaoyuan.base.BaseFragment;
import com.android.aiziran.baicaoyuan.bean.MeiZiBean;
import com.android.aiziran.baicaoyuan.interfaces.models.HomeModel;
import com.android.aiziran.baicaoyuan.interfaces.view.HomeView;
import com.android.aiziran.baicaoyuan.net.obvable.HomeModelImpl;
import com.android.aiziran.baicaoyuan.presenter.HomePresenter;
import com.android.aiziran.baicaoyuan.rxbus.EventData;

import java.util.List;

public class MyFragment extends BaseFragment<HomeModel, HomeView, HomePresenter> implements HomeView {
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void getRxBus(EventData data) {
    }

    @Override
    public HomeModel createModel() {
        return new HomeModelImpl();
    }

    @Override
    public HomeView createView() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showToast(String info) {
    }

    @Override
    public void showNoNet() {
    }

    @Override
    public void showNoData() {
    }

    @Override
    public void setData(List<MeiZiBean> data) {
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

}
