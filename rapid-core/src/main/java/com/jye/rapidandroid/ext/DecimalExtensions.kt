package com.jye.rapidandroid.ext

import com.jye.rapidandroid.util.RapidBigDecimalUtils
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 描述：小数相关扩展函数
 * 创建人：jye-ixiaojye@163.com
 */

fun Double.formatDecimal(digit: Int, roundingMode: RoundingMode = RoundingMode.FLOOR): String {
    return RapidBigDecimalUtils.format(this.toString(), digit, roundingMode)
}

fun Long.formatDecimal(digit: Int, roundingMode: RoundingMode = RoundingMode.FLOOR): String {
    return RapidBigDecimalUtils.format(this.toString(), digit, roundingMode)
}

fun String.formatDecimal(digit: Int, roundingMode: RoundingMode = RoundingMode.FLOOR): String {
    return RapidBigDecimalUtils.format(this, digit, roundingMode)
}

fun Double.toTwoDecimal(roundingMode: RoundingMode = RoundingMode.FLOOR): String {
    return this.formatDecimal(2, roundingMode)
}

fun Long.toTwoDecimal(roundingMode: RoundingMode = RoundingMode.FLOOR): String {
    return this.formatDecimal(2, roundingMode)
}

fun String.toTwoDecimal(roundingMode: RoundingMode = RoundingMode.FLOOR): String {
    return this.formatDecimal(2, roundingMode)
}

fun String.toDoubleTryCatch(normal: Double = 0.0): Double {
    return try {
        this.toDouble()
    } catch (e: Exception) {
        normal
    }
}

fun Double.toBigDecimalTryCatch(normal: Double = 0.0): BigDecimal {
    return try {
        this.toBigDecimal()
    } catch (e: Exception) {
        normal.toBigDecimal()
    }
}

fun Long.toBigDecimalTryCatch(normal: Double = 0.0): BigDecimal {
    return try {
        this.toBigDecimal()
    } catch (e: Exception) {
        normal.toBigDecimal()
    }
}

fun String.toBigDecimalTryCatch(normal: Double = 0.0): BigDecimal {
    return try {
        this.toBigDecimal()
    } catch (e: Exception) {
        normal.toBigDecimal()
    }
}

fun Double.add(number: Double): String {
    return RapidBigDecimalUtils.add(this, number).toPlainString()
}

fun Long.add(number: Long): String {
    return RapidBigDecimalUtils.add(this, number).toPlainString()
}

fun String.add(number: String): String {
    return RapidBigDecimalUtils.add(this, number).toPlainString()
}

fun Double.sub(number: Double): String {
    return RapidBigDecimalUtils.sub(this, number).toPlainString()
}

fun Long.sub(number: Long): String {
    return RapidBigDecimalUtils.sub(this, number).toPlainString()
}

fun String.sub(number: String): String {
    return RapidBigDecimalUtils.sub(this, number).toPlainString()
}

fun Double.mul(number: Double): String {
    return RapidBigDecimalUtils.mul(this, number).toPlainString()
}

fun Long.mul(number: Long): String {
    return RapidBigDecimalUtils.mul(this, number).toPlainString()
}

fun String.mul(number: String): String {
    return RapidBigDecimalUtils.mul(this, number).toPlainString()
}

fun Double.divide(number: Double, scale: Int): String {
    return RapidBigDecimalUtils.divide(this, number, scale).toPlainString()
}

fun Long.divide(number: Long, scale: Int): String {
    return RapidBigDecimalUtils.divide(this, number, scale).toPlainString()
}

fun String.divide(number: String, scale: Int): String {
    return RapidBigDecimalUtils.divide(this, number, scale).toPlainString()
}

fun Double.stripTrailingZeros(): String {
    return RapidBigDecimalUtils.stripTrailingZeros(this)
}

fun Long.stripTrailingZeros(): String {
    return RapidBigDecimalUtils.stripTrailingZeros(this)
}

fun String.stripTrailingZeros(): String {
    return RapidBigDecimalUtils.stripTrailingZeros(this)
}