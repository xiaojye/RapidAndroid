package com.jye.rapidandroid.http.retrofit;

import com.jye.rapidandroid.http.retrofit.converter.MyGsonConverterFactory;
import com.jye.rapidandroid.http.retrofit.provider.IHttpProvider;
import com.jye.rapidandroid.util.json.gson.GsonFactory;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 描述：Retrofit工厂类
 * </p>
 * 作者：jye-ixiaojye@163.com
 */
public final class RetrofitManager {

    private HashMap<String, Retrofit> mRetrofitMap = new HashMap<>();

    private static RetrofitManager sInstance = null;

    public static RetrofitManager getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitManager.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitManager();
                }
            }
        }
        return sInstance;
    }

    private RetrofitManager() {
    }

    /**
     * 获取Retrofit对象
     *
     * @param httpProvider httpProvider
     * @return Retrofit
     */
    public Retrofit getRetrofit(@NotNull IHttpProvider httpProvider) {
        //判断baseUrl是否为空，为空时提示错误
        if (httpProvider.baseUrl().isEmpty()) {
            throw new IllegalStateException("baseUrl can not be null");
        }
        //根据BaseUrl查询是否已创建过Retrofit，如果存在则直接返回（避免重复创建，浪费资源）
        Retrofit retrofitInstance = mRetrofitMap.get(httpProvider.baseUrl());
        if (retrofitInstance != null) {
            return retrofitInstance;
        }

        //构建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpProvider.baseUrl())
                .client(buildOkHttpClient(httpProvider))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create(GsonFactory.getGson()))
                .build();

        mRetrofitMap.put(httpProvider.baseUrl(), retrofit);

        return retrofit;
    }

    /**
     * 构建OkHttpClient对象
     *
     * @param httpProvider httpProvider
     * @return OkHttpClient
     */
    private OkHttpClient buildOkHttpClient(@NotNull IHttpProvider httpProvider) {
        ///配置OkHttpClient
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        //配置读取超时时间
        httpBuilder.readTimeout(httpProvider.readTimeout(), TimeUnit.SECONDS);
        //配置写入超时时间
        httpBuilder.writeTimeout(httpProvider.writeTimeout(), TimeUnit.SECONDS);
        //配置连接超时时间
        httpBuilder.connectTimeout(httpProvider.connectTimeout(), TimeUnit.SECONDS);
        //配置拦截器（数组）
        for (Interceptor interceptor : httpProvider.interceptors()) {
            httpBuilder.addInterceptor(interceptor);
        }

        //添加日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpBuilder.addInterceptor(loggingInterceptor);

        //构建OkHttpClient并返回
        return httpBuilder.build();
    }

}