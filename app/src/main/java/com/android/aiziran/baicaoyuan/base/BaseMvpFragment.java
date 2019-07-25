package com.android.aiziran.baicaoyuan.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.aiziran.baicaoyuan.interfaces.BaseMvp;
import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;

public abstract class BaseMvpFragment<M extends IModel, V extends IView, P extends BasePresenter> extends Fragment implements BaseMvp<M, V, P> {
    protected P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter = createPresenter();
        if (presenter != null) {
            presenter.registerModel(createModel());
            presenter.registerView(createView());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.destroy();
        }
    }
}
