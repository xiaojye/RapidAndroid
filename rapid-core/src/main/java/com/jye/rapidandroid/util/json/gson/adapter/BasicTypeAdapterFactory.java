package com.jye.rapidandroid.util.json.gson.adapter;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.jye.rapidandroid.util.json.gson.util.GsonReadUtils;
import com.jye.rapidandroid.util.json.gson.util.StringUtils;

import java.io.IOException;

/**
 * 描述：基础类型解析适配器工厂
 * </p>
 * 创建人：jye-ixiaojye@163.com
 */
public class BasicTypeAdapterFactory {

    /**
     * 处理字符的适配器
     */
    public static TypeAdapter<String> stringTypeAdapter() {
        return new TypeAdapter<String>() {
            @Override
            public String read(JsonReader in)
                    throws IOException {
                JsonToken peek = in.peek();
                if (peek == JsonToken.NULL) {
                    in.nextNull();
                    return "";
                }
                //增加判断是错误的ARRAY的类型（应该是STRING）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    GsonReadUtils.readArray(in);
                    return "";
                }
                //增加判断是错误的BEGIN_OBJECT的类型（应该是STRING）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    GsonReadUtils.readObject(in);
                    return "";
                }
                //增加判断是错误的NAME的类型（应该是STRING）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.NAME) {
                    in.nextName();
                    return "";
                }
                //增加判断是错误的BOOLEAN的类型（应该是STRING）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.BOOLEAN) {
                    in.nextBoolean();
                    return "";
                }

                return in.nextString();
            }

            @Override
            public void write(JsonWriter out, String value)
                    throws IOException {
                out.value(value);
            }
        };
    }

    /**
     * 处理数字的适配器
     *
     * @param type 0(int.class, Integer.class )
     *             1(long.class, Long.class)
     *             2(short.class, Short.class)
     *             3(double.class, Double.class)
     *             4(float.class, Float.class)
     * @return TypeAdapter
     */
    public static TypeAdapter<Number> numberAdapter(final int type) {
        return new TypeAdapter<Number>() {
            @Override
            public Number read(JsonReader in)
                    throws IOException {
                boolean isError = false;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    isError = true;
                }
                //增加判断是错误的ARRAY的类型（应该是NUMBER）,移动in的下标到结束，移动下标的代码在下方
                else if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    GsonReadUtils.readArray(in);
                    isError = true;
                }
                //增加判断是错误的BEGIN_OBJECT的类型（应该是NUMBER）,移动in的下标到结束，移动下标的代码在下方
                else if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    GsonReadUtils.readObject(in);
                    isError = true;
                }
                //增加判断是错误的NAME的类型（应该是NUMBER）,移动in的下标到结束，移动下标的代码在下方
                else if (in.peek() == JsonToken.NAME) {
                    in.nextName();
                    isError = true;
                }
                //增加判断是错误的BOOLEAN的类型（应该是NUMBER）,移动in的下标到结束，移动下标的代码在下方
                else if (in.peek() == JsonToken.BOOLEAN) {
                    in.nextBoolean();
                    isError = true;
                }

                if (isError) {
                    switch (type) {
                        case 0:
                            return 0;
                        case 1:
                            return (long) 0;
                        case 2:
                            return (short) 0;
                        case 3:
                            return (double) 0;
                        case 4:
                            return (float) 0;
                        default:
                            return 0;
                    }
                }
                try {
                    switch (type) {
                        case 0:
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return StringUtils.toInt(in.nextString());
                            }
                            return in.nextInt();
                        case 1:
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return StringUtils.toLong(in.nextString());
                            }
                            return in.nextLong();
                        case 2:
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return StringUtils.toInt(in.nextString()).shortValue();
                            }
                            return (short) in.nextInt();
                        case 3:
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return StringUtils.toDouble(in.nextString());
                            }
                            return in.nextDouble();
                        case 4:
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return StringUtils.toFloat(in.nextString());
                            }
                            return (float) in.nextDouble();
                    }
                    return in.nextLong();
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override
            public void write(JsonWriter out, Number value)
                    throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.value(value.toString());
            }
        };
    }

    /**
     * 处理boolean的适配器
     */
    public static TypeAdapter<Boolean> booleanTypeAdapter() {
        return new TypeAdapter<Boolean>() {
            @Override
            public Boolean read(JsonReader in)
                    throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return false;
                }
                //增加判断是错误的ARRAY的类型（应该是BOOLEAN）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    GsonReadUtils.readArray(in);
                    return false;
                }
                //增加判断是错误的BEGIN_OBJECT的类型（应该是BOOLEAN）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    GsonReadUtils.readObject(in);
                    return false;
                }
                //增加判断是错误的NAME的类型（应该是BOOLEAN）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.NAME) {
                    in.nextName();
                    return false;
                }
                //增加判断是错误的STRING的类型（应该是BOOLEAN）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.STRING) {
                    in.nextString();
                    return false;
                }
                //增加判断是错误的NUMBER的类型（应该是BOOLEAN）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.NUMBER) {
                    in.nextDouble();
                    return false;
                }

                return in.nextBoolean();
            }

            @Override
            public void write(JsonWriter out, Boolean value)
                    throws IOException {
                out.value(value);
            }
        };
    }

}
