package com.jye.rapidandroid;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.jye.rapidandroid.log.RapidLogger;
import com.jye.rapidandroid.util.RapidActivityManager;

import org.jetbrains.annotations.NotNull;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidAndroid {

    /**
     * 全局上下文
     */
    private Application mContext;

    /**
     * 生命周期管理
     */
    private ActivityLifecycleHelper mLifecycleHelper;

    /**
     * 主线程Handler
     */
    private final Handler mMainHandler;

    private static RapidAndroid sInstance;

    private RapidAndroid(Application context, boolean enableLog) {
        mContext = context;
        mLifecycleHelper = new ActivityLifecycleHelper();
        mMainHandler = new Handler(Looper.getMainLooper());

        //初始化日志
        RapidLogger.getLogConfig().setLogEnable(enableLog);

        //注册Activity生命回调
        registerLifecycleCallbacks(context, mLifecycleHelper);
    }

    public static RapidAndroid getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RapidAndroid.init() 初始化！");
        }
        return sInstance;
    }

    /**
     * 初始化
     *
     * @param application Application对象
     */
    public static void init(@NotNull Application application) {
        init(application, true);
    }

    /**
     * 初始化
     *
     * @param application Application对象
     * @param enableLog   是否开启日志功能（默认开启）
     */
    public static void init(@NotNull Application application, boolean enableLog) {
        sInstance = new RapidAndroid(application, enableLog);
    }

    /**
     * 获取全局上下文
     *
     * @return 全局上下文
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取Application实例
     *
     * @return Application实例
     */
    public Application getApplication() {
        return mContext;
    }

    /**
     * 获取全局ContentResolver
     *
     * @return ContentResolver
     */
    public ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    /**
     * 获取全局资源
     *
     * @return 全局资源
     */
    public Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取全局Asset管理
     *
     * @return 全局Asset管理
     */
    public AssetManager getAssetManager() {
        return getContext().getAssets();
    }

    /**
     * 获取包管理
     *
     * @return 包管理
     */
    public PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }

    /**
     * 获取系统服务
     *
     * @param name  服务名
     * @param clazz 服务类
     * @param <T>
     * @return 系统服务
     */
    public <T> T getSystemService(String name, Class<T> clazz) {
        return getSystemService(getContext(), name, clazz);
    }

    /**
     * 获取系统服务
     *
     * @param context 上下文
     * @param name    服务名
     * @param clazz   服务类
     * @param <T>
     * @return 系统服务
     */
    public <T> T getSystemService(Context context, String name, Class<T> clazz) {
        if (!TextUtils.isEmpty(name) && clazz != null && context != null) {
            Object obj = context.getSystemService(name);
            return clazz.isInstance(obj) ? (T) obj : null;
        } else {
            return null;
        }
    }

    /**
     * 注册activity的生命回调
     *
     * @param application
     * @param lifecycleHelper activity生命周期管理
     */
    public void registerLifecycleCallbacks(Application application, ActivityLifecycleHelper lifecycleHelper) {
        mLifecycleHelper = lifecycleHelper;
        application.registerActivityLifecycleCallbacks(mLifecycleHelper);
    }

    /**
     * 获取生命周期管理
     *
     * @return 生命周期管理
     */
    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mLifecycleHelper;
    }

    /**
     * 获取主线程的Handler
     *
     * @return 主线程Handler
     */
    public Handler getMainHandler() {
        return mMainHandler;
    }

    /**
     * 在主线程中执行
     *
     * @param runnable
     * @return
     */
    public boolean runOnUiThread(Runnable runnable) {
        return getMainHandler().post(runnable);
    }

    /**
     * 退出程序
     */
    public void exitApp() {
//        ServiceUtils.stopAllRunningService(getContext());
//        ProcessUtils.killBackgroundProcesses(XUtil.getContext().getPackageName());
        RapidActivityManager.getInstance().finishAllActivity();
        System.exit(0);
    }

    /**
     * Activity生命周期管理
     */
    public static class ActivityLifecycleHelper implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
            RapidLogger.i("[onActivityCreated]:" + activity.getClass().getName());
            RapidActivityManager.getInstance().pushActivity(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            RapidLogger.i("[onActivityStarted]:" + activity.getClass().getName());
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
            RapidLogger.i("[onActivityResumed]:" + activity.getClass().getName());
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
            RapidLogger.i("[onActivityPaused]:" + activity.getClass().getName());
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            RapidLogger.i("[onActivityStopped]:" + activity.getClass().getName());
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            RapidLogger.i("[onActivitySaveInstanceState]:" + activity.getClass().getName());
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            RapidLogger.i("[onActivityDestroyed]:" + activity.getClass().getName());
            RapidActivityManager.getInstance().popActivity(activity);
        }
    }
}
