package com.jye.rapidandroid.util.json.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TypeAdapters;
import com.jye.rapidandroid.util.json.gson.adapter.BasicTypeAdapterFactory;
import com.jye.rapidandroid.util.json.gson.adapter.CollectionTypeAdapterFactory;
import com.jye.rapidandroid.util.json.gson.adapter.ReflectiveTypeAdapterFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 描述：Gson工厂类
 * </p>
 * 创建人：jye-ixiaojye@163.com
 */
public class GsonFactory {

    private static final Gson sGson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Class builder = gsonBuilder.getClass();
        try {
            //注册String处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(String.class, BasicTypeAdapterFactory.stringTypeAdapter()));
            //注册int处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(int.class, Integer.class, BasicTypeAdapterFactory.numberAdapter(0)));
            //注册long处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(long.class, Long.class, BasicTypeAdapterFactory.numberAdapter(1)));
            //注册short处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(short.class, Short.class, BasicTypeAdapterFactory.numberAdapter(2)));
            //注册double处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(double.class, Double.class, BasicTypeAdapterFactory.numberAdapter(3)));
            //注册float处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(float.class, Float.class, BasicTypeAdapterFactory.numberAdapter(4)));
            //注册boolean处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(boolean.class, Boolean.class, BasicTypeAdapterFactory.booleanTypeAdapter()));

            //通过反射得到构造器
            Field field = builder.getDeclaredField("instanceCreators");
            field.setAccessible(true);
            final Map<Type, InstanceCreator<?>> instanceCreators = (Map<Type, InstanceCreator<?>>) field.get(gsonBuilder);//得到此属性的值

            //注册反射对象的处理器
            gsonBuilder.registerTypeAdapterFactory(new ReflectiveTypeAdapterFactory(new ConstructorConstructor(instanceCreators), FieldNamingPolicy.IDENTITY, Excluder.DEFAULT));
            //注册集合的处理器
            gsonBuilder.registerTypeAdapterFactory(new CollectionTypeAdapterFactory(new ConstructorConstructor(instanceCreators)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sGson = gsonBuilder.create();
    }

    public static Gson getGson() {
        return sGson;
    }

}
