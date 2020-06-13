package com.jye.rapidandroid.ui.ext

import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * 描述：TextView相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

fun TextView.setDrawableLeft(@DrawableRes resId: Int) {
    val drawable = ContextCompat.getDrawable(this.context, resId)
    drawable?.let {
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        this.setCompoundDrawables(drawable, null, null, null)
    }
}

fun TextView.setDrawableRight(@DrawableRes resId: Int) {
    val drawable = ContextCompat.getDrawable(this.context, resId)
    drawable?.let {
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        this.setCompoundDrawables(null, null, drawable, null)
    }
}

fun TextView.setDrawableTop(@DrawableRes resId: Int) {
    val drawable = ContextCompat.getDrawable(this.context, resId)
    drawable?.let {
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        this.setCompoundDrawables(null, drawable, null, null)
    }
}

fun TextView.setDrawableBottom(@DrawableRes resId: Int) {
    val drawable = ContextCompat.getDrawable(this.context, resId)
    drawable?.let {
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        this.setCompoundDrawables(null, null, null, drawable)
    }
}