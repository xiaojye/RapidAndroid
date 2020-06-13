package com.jye.rapidandroid.ext

import android.content.Context
import android.net.Uri
import com.jye.rapidandroid.util.RapidUriUtils
import java.io.File

/**
 * 描述：Uri相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

fun Uri.toFile(context: Context): File {
    return RapidUriUtils.getFileFromUri(context, this)
}

fun File.toUri(context: Context): Uri {
    return RapidUriUtils.getUriFromFile(context, this)
}