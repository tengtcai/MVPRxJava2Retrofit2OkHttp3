package com.android.aiziran.baicaoyuan.presenter;

import com.android.aiziran.baicaoyuan.base.BasePresenter;
import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;

public class PublicPresenter extends BasePresenter<IModel, IView> {
    @Override
    protected void onViewDestroy() {//销毁Activity时的操作，可以停止当前的model
        if (model != null) {
            model.stopRequest();
        }
    }
}
