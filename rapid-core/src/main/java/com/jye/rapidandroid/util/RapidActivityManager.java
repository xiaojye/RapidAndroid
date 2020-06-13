package com.jye.rapidandroid.util;

import android.app.Activity;

import com.jye.rapidandroid.log.RapidLogger;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述：Activity管理工具类
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidActivityManager {
    private static final String TAG = RapidActivityManager.class.getSimpleName();

    //维护Activity 的list
    private List<Activity> mActivityList = Collections.synchronizedList(new LinkedList<>());

    private static RapidActivityManager sInstance;

    private RapidActivityManager() {
    }

    public static RapidActivityManager getInstance() {
        if (sInstance == null) {
            synchronized (RapidActivityManager.class) {
                sInstance = new RapidActivityManager();
            }
        }
        return sInstance;
    }

    /**
     * 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (mActivityList.isEmpty()) {
            return null;
        } else {
            return mActivityList.get(mActivityList.size() - 1);
        }
    }

    /**
     * 添加一个activity到管理里
     *
     * @param activity Activity
     */
    public void pushActivity(Activity activity) {
        mActivityList.add(activity);
        RapidLogger.d(TAG, "activityList:size:" + mActivityList.size());
    }

    /**
     * 删除一个activity在管理里
     *
     * @param activity Activity
     */
    public void popActivity(Activity activity) {
        if (activity == null || !mActivityList.contains(activity)) {
            return;
        }
        mActivityList.remove(activity);
        RapidLogger.d(TAG, "activityList:size:" + mActivityList.size());
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (mActivityList.isEmpty()) {
            return;
        }
        Activity activity = mActivityList.get(mActivityList.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity == null || !mActivityList.contains(activity)) {
            return;
        }
        mActivityList.remove(activity);
        activity.finish();
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityList.isEmpty()) {
            return;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass() == cls) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束（除了指定的Activity之外的）所有Activity
     *
     * @param classes 指定的Activity数组
     */
    public void finishAllActivityExcept(Class<?>... classes) {
        if (mActivityList.isEmpty()) {
            return;
        }
        Iterator<Activity> activityIterator = mActivityList.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            boolean hasExcept = false;
            for (Class exceptCls : classes) {
                if (activity.getClass() == exceptCls) {
                    hasExcept = true;
                    break;
                }
            }
            if (!hasExcept) {
                activity.finish();
                activityIterator.remove();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (mActivityList.isEmpty()) {
            return;
        }
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        mActivityList.clear();
    }
}
