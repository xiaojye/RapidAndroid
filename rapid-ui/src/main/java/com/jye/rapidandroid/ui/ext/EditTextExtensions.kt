package com.jye.rapidandroid.ui.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.jye.rapidandroid.ui.util.RapidKeyboardUtils

/**
 * 描述：EditText相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

/**
 * 获取EditText内容文本
 */
fun EditText.content(): String {
    return this.text.toString()
}

/**
 * 获取EditText内容文本(去除左右空格)
 */
fun EditText.contentTrim(): String {
    return this.text.toString().trim()
}

/**
 * 显示软键盘
 */
fun EditText.showSoftInput() {
    RapidKeyboardUtils.showSoftInput(this)
}

/**
 * 设置EditText输入数值范围
 */
fun EditText.setNumericRegion(min: Long, max: Long) {
    this@setNumericRegion.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            var text = s
            if (start > 1) {
                if (min.toInt() != -1 && max.toInt() != -1) {
                    val num = java.lang.Double.parseDouble(text.toString())
                    if (num > max) {
                        text = max.toString()
                        this@setNumericRegion.setText(text)
                    } else if (num < min) {
                        text = min.toString()
                        this@setNumericRegion.setText(text)
                    }
                    this@setNumericRegion.setSelection(text.length)
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null && s.isNotEmpty()) {
                if (min.toInt() != -1 && max.toInt() != -1) {
                    val markVal: Double = try {
                        java.lang.Double.parseDouble(s.toString())
                    } catch (e: NumberFormatException) {
                        0.0
                    }

                    if (markVal > max) {
                        this@setNumericRegion.setText(max.toString())
                        this@setNumericRegion.setSelection(max.toString().length)
                    }
                    return
                }
            }
        }
    })
}
