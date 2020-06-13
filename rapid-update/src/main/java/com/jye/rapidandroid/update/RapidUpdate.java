//package com.jye.rapidandroid.update;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//
///**
// * 描述：[类描述]
// * 创建人：jye-ixiaojye@163.com
// */
//public class RapidUpdate {
//
//    private UpdateConfig mGlobalConfig;
//
//    private static volatile RapidUpdate sInstance;
//
//    private RapidUpdate() {
//    }
//
//    public static RapidUpdate getInstance() {
//        if (sInstance == null) {
//            synchronized (RapidUpdate.class) {
//                if (sInstance == null) {
//                    sInstance = new RapidUpdate();
//                }
//            }
//        }
//        return sInstance;
//    }
//
//    public static UpdateBuilder newBuilder(@NonNull Context context) {
//        return new UpdateBuilder();
//    }
//
//    public RapidUpdate setUpdateChecker()
//}
