package com.android.aiziran.baicaoyuan.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;
import com.android.aiziran.baicaoyuan.rxbus.EventData;
import com.android.aiziran.baicaoyuan.rxbus.RxBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseFragment<M extends IModel, V extends IView, P extends BasePresenter> extends BaseMvpFragment<M, V, P>{
    private View view;
    protected boolean isLoad = false;//是否正在加载数据
    protected boolean isCreateView = false;//是否初始化了布局
    private Disposable disposable;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view ;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        isCreateView = true;
        isCanLoadData();
        disposable = RxBus.getInstance().toFlowable(EventData.class).subscribe(new Consumer<EventData>() {
            @Override
            public void accept(EventData eventData) throws Exception {
                getRxBus(eventData);
            }
        });
    }

    protected abstract int setLayoutView();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void getRxBus(EventData data);

    public View getView(){
        return view;
    }

    public void startActivity(Class<?> cla) {
        startActivity(new Intent(getActivity(), cla));
    }

    private void isCanLoadData() {
        if (!isLoad) {
            if (getUserVisibleHint()) {
                initData();
                isLoad = true;
            }
        }
    }

    /**
     * fragment可见的时候操作，取代onResume，且在可见状态切换到可见的时候调用
     */
    protected void onVisible() {
        isCanLoadData();
    }

    /**
     * fragment不可见的时候操作,onPause的时候,以及不可见的时候调用
     */
    protected void onHidden() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isCreateView) {
            if (isVisibleToUser) {
                onVisible();
            } else {
                onHidden();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoad = false;
        isCreateView = false;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
