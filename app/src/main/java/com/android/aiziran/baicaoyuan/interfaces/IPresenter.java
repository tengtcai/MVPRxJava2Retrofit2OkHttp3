package com.android.aiziran.baicaoyuan.interfaces;

import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;

public interface IPresenter<M extends IModel, V extends IView> {
    /**
     * 注册Model层
     *
     * @param model
     */
    void registerModel(M model);

    /**
     * 注册View层
     *
     * @param view
     */
    void registerView(V view);

    /**
     * 获取View
     *
     * @return
     */
    V getView();

    /**
     * 销毁动作（如Activity、Fragment的卸载）
     */
    void destroy();
}
