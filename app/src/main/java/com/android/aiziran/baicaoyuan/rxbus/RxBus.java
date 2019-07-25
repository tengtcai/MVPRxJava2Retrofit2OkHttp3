package com.android.aiziran.baicaoyuan.rxbus;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

public class RxBus {
    private static volatile RxBus instance;
    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = InstanceHolder.INSTANCE;
                }
            }
        }
        return instance;
    }

    private static class InstanceHolder{
        private static final RxBus INSTANCE = new RxBus();
    }

    public void post(Object o){
        mBus.onNext(o);
    }

    public <T> Flowable<T> toFlowable(Class<T> eventType){
        return mBus.ofType(eventType);
    }
}
