package com.android.aiziran.baicaoyuan.activity;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.aiziran.baicaoyuan.R;
import com.android.aiziran.baicaoyuan.adapter.PageFragmentAdapter;
import com.android.aiziran.baicaoyuan.base.BaseActivity;
import com.android.aiziran.baicaoyuan.fragment.CardFragment;
import com.android.aiziran.baicaoyuan.fragment.HomeFragment;
import com.android.aiziran.baicaoyuan.fragment.MyFragment;
import com.android.aiziran.baicaoyuan.fragment.WashFragment;
import com.android.aiziran.baicaoyuan.interfaces.models.IModel;
import com.android.aiziran.baicaoyuan.interfaces.view.IView;
import com.android.aiziran.baicaoyuan.net.obvable.PublicModelImpl;
import com.android.aiziran.baicaoyuan.presenter.PublicPresenter;
import com.android.aiziran.baicaoyuan.rxbus.EventData;
import com.android.aiziran.baicaoyuan.rxbus.EventTag;
import com.android.aiziran.baicaoyuan.utils.Constance;
import com.android.aiziran.baicaoyuan.view.MainTab;
import com.android.aiziran.baicaoyuan.view.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<IModel, IView, PublicPresenter> implements IView {
    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;
    @BindView(R.id.mainTab)
    MainTab mainTab;

    private PageFragmentAdapter fragmentAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int setLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setStatusBar(R.color.color_ffffff, true);

        fragments.add(HomeFragment.newInstance());
        fragments.add(WashFragment.newInstance());
        fragments.add(CardFragment.newInstance());
        fragments.add(MyFragment.newInstance());
        fragmentAdapter = new PageFragmentAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(fragmentAdapter);
        viewpager.setScanScroll(false);
        mainTab.setTabListener(new MainTab.MainTabListener() {
            @Override
            public void onSelected(int index) {
                viewpager.setCurrentItem(index);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getRxBus(EventData data) {
        if(data.getTag() == EventTag.FINISH_MAIN){
            finish();
        }else if(data.getTag() == EventTag.SEND_MESSAGE_MAIN){
            Log.e(Constance.TAG,data.getObject().toString());
        }
    }

    @Override
    public IModel createModel() {
        return new PublicModelImpl();
    }

    @Override
    public IView createView() {
        return this;
    }

    @Override
    public PublicPresenter createPresenter() {
        return new PublicPresenter();
    }

    @Override
    public void showToast(String info) {

    }
}
