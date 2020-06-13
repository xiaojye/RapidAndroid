package com.jye.rapidandroid.ui;

import android.content.Context;

import com.jye.rapidandroid.RapidAndroid;

//import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
//import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public class RapidUI {

    private static Context mContext;

    static {
        mContext = RapidAndroid.getInstance().getContext();
    }

//    /**
//     * 设置默认字体
//     */
//    public static void setDefaultTypeface(String defaultFontAssetPath) {
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath(defaultFontAssetPath)
//                .setFontAttrId(R.attr.fontPath)
//                .build());
//    }
//
//    /**
//     * @return 获取默认字体
//     */
//    @Nullable
//    public static Typeface getDefaultTypeface() {
//        String fontPath = CalligraphyConfig.get().getFontPath();
//        if (!TextUtils.isEmpty(fontPath)) {
//            return TypefaceUtils.load(mContext.getAssets(), fontPath);
//        }
//        return null;
//    }
//
//    /**
//     * @param fontPath 字体路径
//     * @return 获取默认字体
//     */
//    @Nullable
//    public static Typeface getDefaultTypeface(String fontPath) {
//        if (TextUtils.isEmpty(fontPath)) {
//            fontPath = CalligraphyConfig.get().getFontPath();
//        }
//        if (!TextUtils.isEmpty(fontPath)) {
//            return TypefaceUtils.load(mContext.getAssets(), fontPath);
//        }
//        return null;
//    }

}
