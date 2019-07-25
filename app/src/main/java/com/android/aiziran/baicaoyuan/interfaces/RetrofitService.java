package com.android.aiziran.baicaoyuan.interfaces;

import com.android.aiziran.baicaoyuan.base.BaseResult;
import com.android.aiziran.baicaoyuan.bean.AdBean;
import com.android.aiziran.baicaoyuan.bean.MeiZiBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitService {
//    @Headers("Cache-Control:public ,max-age=60")
    @GET("福利/10/1")
    Observable<BaseResult<List<MeiZiBean>>> getMeiZi();
    @GET("app/getAppPage")
    Observable<BaseResult<AdBean>> getAd();
}
