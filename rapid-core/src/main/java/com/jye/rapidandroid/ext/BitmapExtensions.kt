package com.jye.rapidandroid.ext

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.jye.rapidandroid.RapidAndroid
import com.jye.rapidandroid.util.RapidBitmapUtils
import java.io.ByteArrayOutputStream

/**
 * 描述：Bitmap相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

/**
 * Bitmap转字节数组
 *
 * @param format 图片格式
 * @return 字节数组
 */
fun Bitmap.toByteArray(format: Bitmap.CompressFormat): ByteArray {
    val baos = ByteArrayOutputStream()
    this.compress(format, 100, baos)
    return baos.toByteArray()
}

/**
 * Bitmap转Drawable
 *
 * @return Drawable
 */
fun Bitmap.toDrawable(): Drawable {
    return BitmapDrawable(RapidAndroid.getInstance().resources, this)
}

/**
 * 图片转换成base64字符串
 *
 * @return base64字符串
 */
fun Bitmap.toBase64String(): String {
    return RapidBitmapUtils.bitmapToBase64String(this)
}
