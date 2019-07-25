package com.android.aiziran.baicaoyuan.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.aiziran.baicaoyuan.R;
import com.android.aiziran.baicaoyuan.base.BaseFragment;
import com.android.aiziran.baicaoyuan.bean.MeiZiBean;
import com.android.aiziran.baicaoyuan.interfaces.models.HomeModel;
import com.android.aiziran.baicaoyuan.interfaces.view.HomeView;
import com.android.aiziran.baicaoyuan.net.obvable.HomeModelImpl;
import com.android.aiziran.baicaoyuan.presenter.HomePresenter;
import com.android.aiziran.baicaoyuan.rxbus.EventData;
import com.android.aiziran.baicaoyuan.rxbus.EventTag;
import com.android.aiziran.baicaoyuan.rxbus.RxBus;
import com.android.aiziran.baicaoyuan.utils.BufferDialogUtil;
import com.android.aiziran.baicaoyuan.utils.Constance;
import com.android.aiziran.baicaoyuan.utils.GsonUtils;
import com.jakewharton.rxbinding3.view.RxView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class HomeFragment extends BaseFragment<HomeModel, HomeView, HomePresenter> implements HomeView {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_error)
    LinearLayout llError;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    @BindView(R.id.tv_content)
    TextView tvContent;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        tvTitle.setText("首页");
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (presenter != null) {
                    presenter.getData(getActivity());
                }
            }
        });
        RxView.clicks(button)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        Log.e(Constance.TAG,"AAAAAAAAAAAAAAAAA");
                        RxBus.getInstance().post(new EventData(EventTag.SEND_MESSAGE_MAIN, "这是消息哦==============1"));
                    }
                });
    }

    @Override
    protected void initData() {
        srlRefresh.autoRefresh();
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
        Log.e(Constance.TAG,"showToast()");
    }

    @Override
    public void showNoNet() {
        tvError.setText("网络错误");
        llError.setVisibility(View.VISIBLE);
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {
        tvError.setText("暂无数据");
        llError.setVisibility(View.VISIBLE);
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void setData(List<MeiZiBean> data) {
        tvContent.setText(GsonUtils.toJson(data));
    }

    @Override
    public void onRequestStart() {
        BufferDialogUtil.showBufferDialog(getActivity());
        llError.setVisibility(View.GONE);
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestEnd() {
        BufferDialogUtil.dismissBufferDialog();
        llLoading.setVisibility(View.GONE);
        srlRefresh.finishRefresh();
    }

    @OnClick({R.id.button1,R.id.button2, R.id.ll_error,R.id.btn_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                initData();
                break;
            case R.id.btn_finish:
            case R.id.button2:
                RxBus.getInstance().post(new EventData(EventTag.FINISH_MAIN));
                break;
            case R.id.ll_error:
                initData();
                break;
        }
    }
}
