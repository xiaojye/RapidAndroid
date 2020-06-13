package com.jye.rapidandroid.ui.ext

import android.graphics.Color

/**
 * 描述：颜色相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

/**
 * 判断是否为浅色
 */
fun Int.isLightColor(): Boolean {
    val r = 0.299 * Color.red(this)
    val g = 0.587 * Color.green(this)
    val b = 0.114 * Color.blue(this)
    val darkness = 1 - (r + g + b) / 255
    return darkness < 0.4
}