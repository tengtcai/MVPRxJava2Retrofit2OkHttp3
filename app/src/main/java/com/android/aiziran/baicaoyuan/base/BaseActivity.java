package com.android.aiziran.baicaoyuan.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;
import com.android.aiziran.baicaoyuan.rxbus.EventData;
import com.android.aiziran.baicaoyuan.rxbus.RxBus;
import com.android.aiziran.baicaoyuan.statusbar.StatusBarUtil;
import com.android.aiziran.baicaoyuan.utils.MyUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseActivity<M extends IModel, V extends IView, P extends BasePresenter> extends BaseMvpActivity<M, V, P> {
    private Disposable disposable;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutView());
        /**
         * 设置为竖屏
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //绑定activity
        unbinder = ButterKnife.bind(this) ;
        initView();
        initData();
        disposable = RxBus.getInstance().toFlowable(EventData.class).subscribe(new Consumer<EventData>() {
            @Override
            public void accept(EventData eventData) throws Exception {
                getRxBus(eventData);
            }
        });
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    protected abstract int setLayoutView();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void getRxBus(EventData data);

    public void startActivity(Class<?> cla) {
        startActivity(new Intent(this, cla));
    }

    /**
     *设置状态栏的颜色
     */
    public void setStatusBar(int color,boolean isExistence){
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,isExistence);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,0x55000000);//状态栏颜色
        }
        if(isExistence){
            StatusBarUtil.setStatusBarColor(this,getResources().getColor(color));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //不想要父视图拦截触摸事件
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (MyUtils.isShouldHideKeyboard(v, event)) {
                MyUtils.hideKeyboard(v.getWindowToken());
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        ///这里使用了解绑代码，如果要在子activity的onDestory()中销毁某些内容，记得将代码放在super.onDestroy();前面，不然会找不到指定的控件
        unbinder.unbind();
    }

}
