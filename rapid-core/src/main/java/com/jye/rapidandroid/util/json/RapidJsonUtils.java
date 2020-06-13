package com.jye.rapidandroid.util.json;

import com.jye.rapidandroid.util.json.gson.GsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 描述：Json转换器封装类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidJsonUtils {

    //默认使用Gson转换器
    private static IConverter sConverter = new GsonConverter();

    /**
     * 设置Json转换器实例
     *
     * @param converter Json转换器实例
     */
    public static void setConvertImpl(IConverter converter) {
        sConverter = converter;
    }

    /**
     * 将json转成特定的cls的对象
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        return sConverter.jsonToBean(json, cls);
    }

    /**
     * 将对象转成json字符串
     */
    public static String beanToJson(Object object) {
        return sConverter.beanToJson(object);
    }


    /**
     * 将一个json字符串，转换成一个集合
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        return sConverter.jsonToList(json, cls);
    }

    /**
     * 将集合转换成json字符串
     */
    public static String listToJson(List<?> list) {
        return beanToJson(list);
    }

    /**
     * 将json字符串转换成JSONObject对象
     *
     * @param json json字符串
     * @return JSONObject
     */
    public static JSONObject jsonToJSONObject(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JSONObject对象转换成json字符串
     *
     * @param jsonObject JSONObject
     * @return json字符串
     */
    public static String jsonObjectToJson(JSONObject jsonObject) {
        return jsonObject.toString();
    }

}
