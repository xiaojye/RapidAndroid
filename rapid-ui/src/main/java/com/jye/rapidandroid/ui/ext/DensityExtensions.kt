package com.jye.rapidandroid.ui.ext

import com.jye.rapidandroid.RapidAndroid
import com.jye.rapidandroid.ui.util.RapidScreenUtils

/**
 * 描述：尺寸转换相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

fun Int.dp2px(): Int {
    return RapidScreenUtils.dp2px(RapidAndroid.getInstance().context, this)
}

fun Float.dp2px(): Int {
    return RapidScreenUtils.dp2px(RapidAndroid.getInstance().context, this)
}

fun Int.sp2px(): Int {
    return RapidScreenUtils.sp2px(RapidAndroid.getInstance().context, this)
}

fun Float.sp2px(): Int {
    return RapidScreenUtils.sp2px(RapidAndroid.getInstance().context, this)
}

fun Int.px2dp(): Int {
    return RapidScreenUtils.px2dp(RapidAndroid.getInstance().context, this)
}

fun Float.px2dp(): Int {
    return RapidScreenUtils.px2dp(RapidAndroid.getInstance().context, this)
}

fun Int.px2sp(): Int {
    return RapidScreenUtils.px2sp(RapidAndroid.getInstance().context, this)
}

fun Float.px2sp(): Int {
    return RapidScreenUtils.px2sp(RapidAndroid.getInstance().context, this)
}
