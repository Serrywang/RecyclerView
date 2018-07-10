package com.example.wydnn.userguide.HTTP;

import com.example.wydnn.userguide.Config.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {

    private static final int DEFAULT_TIME_OUT=5;
    private static final int DEFAULT_READ_OUT=10;
    private Retrofit mRetrofit=null;
    private static RetrofitServiceManager mRetrofitServiceManager=null;
    private RetrofitServiceManager(){
        //初始化Okhttp
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_OUT,TimeUnit.SECONDS);
        //初始化Retrofit
        mRetrofit=new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    //获取实例
    public static RetrofitServiceManager getInstance(){
        if(mRetrofitServiceManager==null){
            return new RetrofitServiceManager();
        }
        return mRetrofitServiceManager;
    }

    //生成接口实例的方法
  //  public <T> T create(Class<T> service){
    //    return mRetrofitServiceManager.create(service);
//    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
