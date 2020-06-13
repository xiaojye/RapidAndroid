package com.jye.rapidandroid.util.json;

import java.util.List;

/**
 * 描述：Json转换器接口
 * </p>
 * 创建人：jye-ixiaojye@163.com
 */
public interface IConverter {

    /**
     * 将对象转成json格式
     */
    String beanToJson(Object object);

    /**
     * 将json转成特定的cls的对象
     */
    <T> T jsonToBean(String json, Class<T> cls);

    /**
     * 将一个json字符串，转换成一个集合
     */
    <T> List<T> jsonToList(String json, Class<T> cls);

}
