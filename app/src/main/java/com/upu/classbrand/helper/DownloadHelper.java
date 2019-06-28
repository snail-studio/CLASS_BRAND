package com.upu.classbrand.helper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class DownloadHelper {
    private static final String TAG="ApiHelper";
    private static DownloadHelper mInstance;
    private Retrofit mRetrofit;
    private OkHttpClient mHttpClient;

    private DownloadHelper(){
        this(10,10,10);
    }
    public DownloadHelper(int connTimeout,int readTimeout,int writeTimeout){
        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .connectTimeout(connTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout,TimeUnit.SECONDS)
                .writeTimeout(writeTimeout,TimeUnit.SECONDS);
        mHttpClient=builder.build();
    }
    public static DownloadHelper getmInstance(){
        if (mInstance==null){
            mInstance=new DownloadHelper();
        }
        return mInstance;
    }
    public DownloadHelper buildRetrofit(String baseUrl){
        mRetrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mHttpClient)
                .build();
        return this;
    }

    public <T> T createService(Class<T> serviceClass){
        return mRetrofit.create(serviceClass);
    }
}
