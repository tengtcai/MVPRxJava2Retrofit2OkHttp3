package com.android.aiziran.baicaoyuan.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by fg on 2018/7/25.
 * *@description 拦截器工具类
 */

public class InterceptorUtil {

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor(){
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    Log.e("OKHttp-----", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("OKHttp-----", message);
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

}