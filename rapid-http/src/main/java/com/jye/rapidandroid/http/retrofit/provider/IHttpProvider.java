package com.jye.rapidandroid.http.retrofit.provider;

import okhttp3.Interceptor;

/**
 * 描述：网络提供者接口
 * <p>
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public interface IHttpProvider {

    /**
     * 配置BaseUrl
     *
     * @return BaseUrl
     */
    String baseUrl();

    /**
     * 配置连接超时时间（单位：秒）
     *
     * @return 连接超时时间
     */
    int connectTimeout();

    /**
     * 配置读取超时时间（单位：秒）
     *
     * @return 连接读取时间
     */
    int readTimeout();

    /**
     * 配置写入超时时间（单位：秒）
     *
     * @return 连接写入时间
     */
    int writeTimeout();

    /**
     * 配置拦截器列表
     *
     * @return 拦截器列表
     */
    Interceptor[] interceptors();

}