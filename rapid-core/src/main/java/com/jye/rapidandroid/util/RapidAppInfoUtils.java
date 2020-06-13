package com.jye.rapidandroid.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 描述：APP相关信息工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidAppInfoUtils {

    private RapidAppInfoUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取APP版本号
     *
     * @param context 上下文
     * @return 当前版本号，失败时返回0
     */
    public static synchronized int getVersionCode(Context context) {
        try {
            String packageName = context.getPackageName();
            return context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取APP版本名称
     *
     * @param context 上下文
     * @return 当前版本名称，失败时返回空字符串
     */
    public static synchronized String getVersionName(Context context) {
        try {
            String packageName = context.getPackageName();
            return context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @param context 上下文
     * @return 当前应用的版本名称，失败时返回空字符串
     */
    public static synchronized String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}

