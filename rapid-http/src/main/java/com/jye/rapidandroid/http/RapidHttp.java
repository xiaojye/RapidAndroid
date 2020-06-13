package com.jye.rapidandroid.http;

import com.jye.rapidandroid.http.retrofit.RetrofitManager;
import com.jye.rapidandroid.http.retrofit.provider.DefaultHttpProvider;
import com.jye.rapidandroid.http.retrofit.provider.IHttpProvider;

import org.jetbrains.annotations.NotNull;

import retrofit2.Retrofit;

/**
 * 描述：http管理类
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidHttp {

    public static final int DEFAULT_CONNECT_TIMEOUT = 10;
    public static final int DEFAULT_READ_TIMEOUT = 10;
    public static final int DEFAULT_WRITE_TIMEOUT = 10;

    private static volatile RapidHttp sInstance;

    private RapidHttp() {
    }

    public static RapidHttp getInstance() {
        if (sInstance == null) {
            synchronized (RapidHttp.class) {
                if (sInstance == null) {
                    sInstance = new RapidHttp();
                }
            }
        }
        return sInstance;
    }

    public Retrofit retrofit(@NotNull IHttpProvider httpProvider) {
        return RetrofitManager.getInstance().getRetrofit(httpProvider);
    }

    public Retrofit retrofit(@NotNull String baseUrl) {
        return RetrofitManager.getInstance().getRetrofit(new DefaultHttpProvider(baseUrl));
    }

}
