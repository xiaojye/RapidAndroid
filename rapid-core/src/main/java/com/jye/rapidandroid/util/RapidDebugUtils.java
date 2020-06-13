package com.jye.rapidandroid.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * 描述：Debug工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidDebugUtils {

    /**
     * 当前是否为Debug模式
     */
    public static boolean isDebug(Context context) {
        return context.getApplicationInfo() != null
                && (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

}