package com.jye.rapidandroid.http.retrofit.converter;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 描述：Gson响应体变换器
 * </p>
 * 作者：jye-ixiaojye@163.com
 */
final class GsonResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        return gson.fromJson(response, type);
    }
}