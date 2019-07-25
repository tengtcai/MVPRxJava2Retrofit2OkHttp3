package com.android.aiziran.baicaoyuan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.aiziran.baicaoyuan.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainTab extends FrameLayout {
    @BindView(R.id.img_home)
    ImageView mImgHome;
    @BindView(R.id.img_classify)
    ImageView mImgClassify;
    @BindView(R.id.img_car)
    ImageView mImgCar;
    @BindView(R.id.img_mine)
    ImageView mImgMine;
    private final ArrayList<ImageView> mTabs;

    public MainTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_main_tab, this);
        ButterKnife.bind(this, view);
        mTabs = new ArrayList<>();
        mTabs.add(mImgHome);
        mTabs.add(mImgClassify);
        mTabs.add(mImgCar);
        mTabs.add(mImgMine);
        mImgHome.setSelected(true);
    }

    @OnClick({R.id.layout_home, R.id.layout_classify, R.id.layout_car, R.id.layout_mine})
    public void onViewClicked(View view) {
        for (ImageView tab : mTabs) {
            tab.setSelected(false);
        }
        switch (view.getId()) {
            case R.id.layout_home:
                mTabs.get(0).setSelected(true);
                mListener.onSelected(0);
                break;
            case R.id.layout_classify:
                mTabs.get(1).setSelected(true);
                mListener.onSelected(1);
                break;
            case R.id.layout_car:
                mTabs.get(2).setSelected(true);
                mListener.onSelected(2);
                break;
            case R.id.layout_mine:
                mTabs.get(3).setSelected(true);
                mListener.onSelected(3);
                break;
        }
    }

    private MainTabListener mListener;

    public void setTabListener(MainTabListener listener) {
        mListener = listener;
    }

    public void setChoosed(int position) {
        for (ImageView tab : mTabs) {
            tab.setSelected(false);
        }
        mTabs.get(position).setSelected(true);
        mListener.onSelected(position);
    }

    public interface MainTabListener {
        void onSelected(int index);
    }
}
