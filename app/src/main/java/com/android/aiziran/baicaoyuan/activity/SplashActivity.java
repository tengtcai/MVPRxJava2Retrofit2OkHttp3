package com.android.aiziran.baicaoyuan.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.aiziran.baicaoyuan.R;
import com.android.aiziran.baicaoyuan.base.BaseActivity;
import com.android.aiziran.baicaoyuan.bean.AdBean;
import com.android.aiziran.baicaoyuan.interfaces.models.SplashModel;
import com.android.aiziran.baicaoyuan.interfaces.view.SplashView;
import com.android.aiziran.baicaoyuan.net.obvable.SplashModelImpl;
import com.android.aiziran.baicaoyuan.presenter.SplashPresenter;
import com.android.aiziran.baicaoyuan.rxbus.EventData;
import com.android.aiziran.baicaoyuan.utils.Constance;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 韩健强 on 2018-03-21.
 * 开屏页
 */

public class SplashActivity extends BaseActivity<SplashModel, SplashView, SplashPresenter> implements SplashView {
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;
    @BindView(R.id.btn_skip)
    TextView btnSkip;

    @Override
    protected int setLayoutView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        // 避免从桌面启动程序后，会重新实例化入口类的activity
        if (!this.isTaskRoot()) { // 当前类不是该Task的根部，那么之前启动
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) { // 当前类是从桌面启动的
                    finish(); // finish掉该类，直接打开该Task中现存的Activity
                    return;
                }
            }
        }
        setStatusBar(R.color.color_00000000,false);
        ivSplash.setMaxWidth(ScreenUtils.getScreenWidth());

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    @Override
    protected void initData() {
        if(presenter != null){
            presenter.getAd(this);
        }
    }

    @Override
    protected void getRxBus(EventData data) {

    }

    private void saveImage() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        switch(requestCode) {
            case Constance.PERMISSION_WRITE_FILE:
                if(grantResults.length>0){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        saveImage();
                    }else {
                        ToastUtils.showShort("请在“系统设置-应用管理-权限管理”中打开文件读写权限");
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            startActivity(MainActivity.class);
            finish();
        }
    }

    @OnClick(R.id.btn_skip)
    public void onClick() {
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public SplashModel createModel() {
        return new SplashModelImpl();
    }

    @Override
    public SplashView createView() {
        return this;
    }

    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void setData(AdBean data) {

    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onGoMain() {

    }

    @Override
    public void showToast(String info) {

    }
}
