package com.android.aiziran.baicaoyuan.interfaces;

import com.android.aiziran.baicaoyuan.base.BasePresenter;
import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;

public interface BaseMvp<M extends IModel, V extends IView, P extends BasePresenter> {
    M createModel();

    V createView();

    P createPresenter();
}
