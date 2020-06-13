package com.jye.rapidandroid.log;

import android.util.Log;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidLogger {

    private static final int STACK_TRACE_INDEX_4 = 4;

    private static LogConfig sLogConfig;

    private RapidLogger() {
    }

    /**
     * 设置日志配置
     *
     * @param logConfig 日志配置
     */
    public static void setLogConfig(LogConfig logConfig) {
        sLogConfig = logConfig;
    }

    public static LogConfig getLogConfig() {
        //判断如果没有设置日志配置的话，创建LogConfig实例（内部有默认配置）
        if (sLogConfig == null) {
            sLogConfig = new LogConfig();
        }
        return sLogConfig;
    }

    public static void d(Object obj) {
        print(LogLevel.DEBUG, null, obj, null);
    }

    public static void d(Object obj, Throwable e) {
        print(LogLevel.DEBUG, null, obj, e);
    }

    public static void d(String tag, Object obj) {
        print(LogLevel.DEBUG, tag, obj, null);
    }

    public static void d(String tag, Object obj, Throwable e) {
        print(LogLevel.DEBUG, tag, obj, e);
    }

    public static void i(Object obj) {
        print(LogLevel.INFO, null, obj, null);
    }

    public static void i(Object obj, Throwable e) {
        print(LogLevel.INFO, null, obj, e);
    }

    public static void i(String tag, Object obj) {
        print(LogLevel.INFO, tag, obj, null);
    }

    public static void i(String tag, Object obj, Throwable e) {
        print(LogLevel.INFO, tag, obj, e);
    }

    public static void w(Object obj) {
        print(LogLevel.WARN, null, obj, null);
    }

    public static void w(Object obj, Throwable e) {
        print(LogLevel.WARN, null, obj, e);
    }

    public static void w(String tag, Object obj) {
        print(LogLevel.WARN, tag, obj, null);
    }

    public static void w(String tag, Object obj, Throwable e) {
        print(LogLevel.WARN, tag, obj, e);
    }

    public static void e(Object obj) {
        print(LogLevel.ERROR, null, obj, null);
    }

    public static void e(Object obj, Throwable e) {
        print(LogLevel.ERROR, null, obj, e);
    }

    public static void e(String tag, Object obj) {
        print(LogLevel.ERROR, tag, obj, null);
    }

    public static void e(String tag, Object obj, Throwable e) {
        print(LogLevel.ERROR, tag, obj, e);
    }

    /**
     * 执行打印方法
     *
     * @param level 日志等级
     * @param tag   TAG
     * @param obj   打印对象
     */
    private static void print(LogLevel level, String tag, Object obj, Throwable e) {
        // 判断日志模块是否启用
        if (!getLogConfig().isLogEnable()) {
            return;
        }
        // 判断日志等级是否大于配置的日志等级
        if (level.getIndex() > getLogConfig().getLogLevel().getIndex()) {
            return;
        }

        String msg;

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String className = stackTrace[STACK_TRACE_INDEX_4].getFileName();
        String methodName = stackTrace[STACK_TRACE_INDEX_4].getMethodName();
        int lineNumber = stackTrace[STACK_TRACE_INDEX_4].getLineNumber();

        tag = (tag == null ? className : tag);
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        if (obj == null) {
            msg = "Log with null Object";
        } else {
            msg = obj.toString();
        }
        stringBuilder.append(msg);

        String message = stringBuilder.toString();

        //头部信息：[ (类名:行号)#函数名 ]
        String headInfo = "[" + "(" + className + ":" + lineNumber + ")" + "#" + methodName + "] ";

        switch (level) {
            case DEBUG:
                Log.d(tag, headInfo + message, e);
                break;
            case INFO:
                Log.i(tag, headInfo + message, e);
                break;
            case WARN:
                Log.w(tag, headInfo + message, e);
                break;
            case ERROR:
                Log.e(tag, headInfo + message, e);
                break;
        }
    }

}
