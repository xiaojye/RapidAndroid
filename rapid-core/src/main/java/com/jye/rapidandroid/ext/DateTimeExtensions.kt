package com.jye.rapidandroid.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * 描述：时间戳相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

fun Long.toYYYYMMDDHHMMSS(separator1: CharSequence = "-", separator2: CharSequence = ":"): String {
    val dateFormat = SimpleDateFormat(
        "yyyy${separator1}MM${separator1}dd HH${separator2}mm${separator2}ss",
        Locale.CHINA
    )
    return dateFormat.format(this)
}

fun Int.toYYYYMMDDHHMMSS(separator1: CharSequence = "-", separator2: CharSequence = ":"): String {
    val dateFormat = SimpleDateFormat(
        "yyyy${separator1}MM${separator1}dd HH${separator2}mm${separator2}ss",
        Locale.CHINA
    )
    return dateFormat.format(this)
}

fun Long.toYYYYMMDDHHMM(separator1: CharSequence = "-", separator2: CharSequence = ":"): String {
    val dateFormat =
        SimpleDateFormat("yyyy${separator1}MM${separator1}dd HH${separator2}mm", Locale.CHINA)
    return dateFormat.format(this)
}

fun Int.toYYYYMMDDHHMM(separator1: CharSequence = "-", separator2: CharSequence = ":"): String {
    val dateFormat =
        SimpleDateFormat("yyyy${separator1}MM${separator1}dd HH${separator2}mm", Locale.CHINA)
    return dateFormat.format(this)
}

fun Long.toYYYYMMDD(separator: CharSequence = "-"): String {
    val dateFormat = SimpleDateFormat("yyyy${separator}MM${separator}dd", Locale.CHINA)
    return dateFormat.format(this)
}

fun Int.toYYYYMMDD(separator: CharSequence = "-"): String {
    val dateFormat = SimpleDateFormat("yyyy${separator}MM${separator}dd", Locale.CHINA)
    return dateFormat.format(this)
}

fun Long.formatTime(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.CHINA)
    return dateFormat.format(this)
}

fun Int.formatTime(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.CHINA)
    return dateFormat.format(this)
}