package com.android.aiziran.baicaoyuan.base;

import com.android.aiziran.baicaoyuan.interfaces.IPresenter;
import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<M, V> {
    /**
     * 使用弱引用来防止内存泄漏
     */
    private WeakReference<V> wrf;
    protected M model;

    @Override
    public void registerModel(M model) {
        this.model = model;
    }

    @Override
    public void registerView(V view) {
        wrf = new WeakReference<V>(view);
    }

    @Override
    public V getView() {
        return wrf == null ? null : wrf.get();
    }

    /**
     * 在Activity或Fragment卸载时调用View结束的方法
     */
    @Override
    public void destroy() {
        if (wrf != null) {
            wrf.clear();
            wrf = null;
        }
        onViewDestroy();
    }
    protected abstract void onViewDestroy();
}
