package com.jye.rapidandroid.http.retrofit.provider;

import com.jye.rapidandroid.http.RapidHttp;

import okhttp3.Interceptor;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public class DefaultHttpProvider implements IHttpProvider {

    private String mBaseUrl;

    public DefaultHttpProvider(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Override
    public String baseUrl() {
        return mBaseUrl;
    }

    @Override
    public int connectTimeout() {
        return RapidHttp.DEFAULT_CONNECT_TIMEOUT;
    }

    @Override
    public int readTimeout() {
        return RapidHttp.DEFAULT_READ_TIMEOUT;
    }

    @Override
    public int writeTimeout() {
        return RapidHttp.DEFAULT_WRITE_TIMEOUT;
    }

    @Override
    public Interceptor[] interceptors() {
        return new Interceptor[0];
    }
}
