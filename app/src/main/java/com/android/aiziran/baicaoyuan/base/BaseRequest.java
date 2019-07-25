package com.android.aiziran.baicaoyuan.base;

import android.content.Context;

import com.android.aiziran.baicaoyuan.interfaces.RetrofitService;
import com.android.aiziran.baicaoyuan.net.Api;
import com.android.aiziran.baicaoyuan.utils.Constance;
import com.android.aiziran.baicaoyuan.utils.FileUtils;
import com.android.aiziran.baicaoyuan.utils.InterceptorUtil;
import com.android.aiziran.baicaoyuan.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fg on 2018/7/25.
 */

public class BaseRequest {
    private OkHttpClient client;
    private Retrofit retrofit;
    private Context mContext;

    public BaseRequest(Context context){
        this.mContext = context;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);//设置连接超时时间
        builder.readTimeout(30, TimeUnit.SECONDS);//设置读取超时时间
        builder.writeTimeout(30, TimeUnit.SECONDS);//设置写入超时时间
        builder.addNetworkInterceptor(NetCacheInterceptor);
        builder.addInterceptor(OfflineCacheInterceptor);
        builder.addInterceptor(InterceptorUtil.LogInterceptor());//添加日志拦截器
        builder.retryOnConnectionFailure(true);//连接失败后是否重新连接
        builder.cookieJar(cookieJar);//设置cookie的持久化
        builder.cache(new Cache(new File(FileUtils.getDiskCacheDir(mContext),"okHttpCache"), 1024*100*1024));
        client = builder.build();

        Retrofit.Builder rBuilder = new Retrofit.Builder();
        rBuilder.addConverterFactory(GsonConverterFactory.create()); //添加Gson转换器
        rBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());//添加Rx转换器
        rBuilder.baseUrl(Api.BASE_HOST); //baseurl
        rBuilder.client(client);
        retrofit = rBuilder.build();
    }

    final CookieJar cookieJar = new CookieJar() {
        private  final HashMap<HttpUrl,List<Cookie>> cookieStore = new HashMap<>();
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieStore.put(HttpUrl.parse(url.host()),cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url.host());
            return cookies != null?cookies:new ArrayList<Cookie>();
        }
    };

    /**
     * 有网时候的不缓存
     */
    final Interceptor NetCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            String cacheControl =request.cacheControl().toString();
            Response.Builder builder = response.newBuilder();
            if (!cacheControl.isEmpty()) {//如果单独配置
                builder.header("Cache-Control", cacheControl);
            } else {
                builder.addHeader("Cache-Control", "no-cache");
            }
            builder.removeHeader("Pragma");
            return builder.build();
        }
    };
    /**
     * 没有网的时候使用缓存
     */
    final Interceptor OfflineCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.isNetworkAvailable()) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + Constance.OKHTTP_CATCHE_TIME_OFFLINE)
                        .build();
            }
            return chain.proceed(request);
        }
    };

    private static BaseRequest instance;

    public static synchronized BaseRequest getInstance(Context context){
        if(instance == null)
            instance = new BaseRequest(context);
        return instance;
    }

    public RetrofitService getService() {
        return retrofit.create(RetrofitService.class);
    }


}