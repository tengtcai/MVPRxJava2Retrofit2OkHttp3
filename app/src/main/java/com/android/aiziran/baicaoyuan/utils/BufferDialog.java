package com.android.aiziran.baicaoyuan.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.aiziran.baicaoyuan.R;


/**
 * Created by Administrator on 2018/1/10.
 *
 * 缓冲效果的dialog
 */
public class BufferDialog extends Dialog implements DialogInterface.OnDismissListener{
    private Context mContext;
    private ImageView imageView;
    private AnimationDrawable loadAnimation;

    public BufferDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
        init();
    }

    public void canDissmis(boolean canDissmis){
        setCanceledOnTouchOutside(canDissmis);
        setCancelable(canDissmis);
    }

    private void init(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_buffer,null);
        imageView = view.findViewById(R.id.iv_progress);
        // 加载动画
        imageView.setImageResource(R.drawable.buffer_loading);
        loadAnimation = (AnimationDrawable) imageView.getDrawable(); // 使用ImageView显示动画
        loadAnimation.start();

        setContentView(view);
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(R.color.color_00000000);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;//参数为0到1之间。0表示完全透明，1就是不透明。按需求调整参数
        window.setAttributes(lp);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        //消失监听事件
        if(loadAnimation != null){
            loadAnimation.stop();
            loadAnimation = null;
        }
    }
}
