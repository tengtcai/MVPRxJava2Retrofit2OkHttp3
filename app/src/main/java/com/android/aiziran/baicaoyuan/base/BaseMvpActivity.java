package com.android.aiziran.baicaoyuan.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.aiziran.baicaoyuan.interfaces.BaseMvp;
import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;

public abstract class BaseMvpActivity<M extends IModel, V extends IView, P extends BasePresenter> extends AppCompatActivity implements BaseMvp<M, V, P> {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        presenter = createPresenter();
        if (presenter != null) {
            //将Model层注册到Presenter中
            presenter.registerModel(createModel());
            //将View层注册到Presenter中
            presenter.registerView(createView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            //Activity销毁时的调用，让具体实现BasePresenter中onViewDestroy()方法做出决定
            presenter.destroy();
        }
    }
}
