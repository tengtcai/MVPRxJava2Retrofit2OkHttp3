package com.android.aiziran.baicaoyuan.base;

import android.accounts.NetworkErrorException;

import com.android.aiziran.baicaoyuan.utils.NetUtils;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Observer 返回的请求结果统一处理类
 * @param <T>
 */
public abstract  class BaseObserver<T> implements Observer<BaseResult<T>> {
    //订阅器
    protected Disposable disposable;

    //开始
    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        //请求开始
        onRequestStart();
    }

    //获取数据
    @Override
    public void onNext(BaseResult<T> t) {
        if (t.isSuccess()) {
            try {
                onSuccees(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //失败
    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);  //网络错误
            } else {
                if(NetUtils.isNetworkAvailable()) {
                    onFailure(e, false);
                }else{
                    onFailure(e, true);  //网络错误
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    //结束
    @Override
    public void onComplete() {
        onRequestEnd();
    }

    /**
     * 返回失败,外部类要继承实现的
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseResult<T> t) throws Exception { }

    /**
     * 返回成功 外部类要继承实现的
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseResult<T> t) throws Exception;


    /**
     * 请求开始 如果外部类要继承实现,就加上修饰符  abstract
     */
    protected void onRequestStart() {
        //开始进度条

    }

    /**
     * 请求结束  如果外部类要继承实现,就加上修饰符  abstract
     */
    protected void onRequestEnd() {
        //取消订阅
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        //结束进度条

    }
}

