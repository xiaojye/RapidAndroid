package com.jye.rapidandroid.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 描述：金额工具类
 * <p>
 * 作者：jye-ixiaojye@163.com
 */
public final class RapidAmountUtils {

    private RapidAmountUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额浮点数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(long amount) {
        return format(String.valueOf(amount));
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额浮点数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(double amount) {
        return format(String.valueOf(amount));
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额字符串
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(String amount) {
        return format(amount, 2);
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额浮点数
     * @param digit  保留小数位数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(long amount, int digit) {
        return format(String.valueOf(amount), digit);
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额浮点数
     * @param digit  保留小数位数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(double amount, int digit) {
        return format(String.valueOf(amount), digit);
    }

    /**
     * 格式化金额字符串
     *
     * @param amount 金额字符串
     * @param digit  保留小数位数
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(String amount, int digit) {
        return format(amount, digit, RoundingMode.FLOOR);
    }

    /**
     * 格式化金额字符串
     *
     * @param amount       金额字符串
     * @param digit        保留小数位数
     * @param roundingMode 舍入模式（默认ROUND_FLOOR，截断）
     * @return 格式化后的金额字符串（保留两位小数）
     */
    public static String format(String amount, int digit, RoundingMode roundingMode) {
       return RapidBigDecimalUtils.format(amount, digit, roundingMode);
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount 分
     * @return 元
     */
    public static String changeF2Y(long amount) {
        return changeF2Y(String.valueOf(amount));
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount 分
     * @return 元
     */
    public static String changeF2Y(double amount) {
        return changeF2Y(String.valueOf(amount));
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount 分
     * @return 元
     */
    public static String changeF2Y(String amount) {
        return changeF2Y(amount, 2);
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount 分
     * @param digit  保留小数位数（默认向下取整/截取模式）
     * @return 元
     */
    public static String changeF2Y(long amount, int digit) {
        return changeF2Y(String.valueOf(amount), digit);
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount 分
     * @param digit  保留小数位数（默认向下取整/截取模式）
     * @return 元
     */
    public static String changeF2Y(double amount, int digit) {
        return changeF2Y(String.valueOf(amount), digit);
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount 分
     * @param digit  保留小数位数（默认向下取整/截取模式）
     * @return 元
     */
    public static String changeF2Y(String amount, int digit) {
        return changeF2Y(amount, digit, BigDecimal.ROUND_DOWN);
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount       分
     * @param digit        保留小数位数
     * @param roundingMode 格式化模式
     * @return 元
     */
    public static String changeF2Y(long amount, int digit, int roundingMode) {
        return changeF2Y(String.valueOf(amount), digit, roundingMode);
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount       分
     * @param digit        保留小数位数
     * @param roundingMode 格式化模式
     * @return 元
     */
    public static String changeF2Y(double amount, int digit, int roundingMode) {
        return changeF2Y(String.valueOf(amount), digit, roundingMode);
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount       分
     * @param digit        保留小数位数
     * @param roundingMode 格式化模式
     * @return 元
     */
    public static String changeF2Y(String amount, int digit, int roundingMode) {
        return format(new BigDecimal(amount).divide(new BigDecimal(100), digit, roundingMode).doubleValue(), digit);
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount 元
     * @return 分
     */
    public static String changeY2F(long amount) {
        return changeY2F(String.valueOf(amount));
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount 元
     * @return 分
     */
    public static String changeY2F(double amount) {
        return changeY2F(String.valueOf(amount));
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount 元
     * @return 分
     */
    public static String changeY2F(String amount) {
        return stripTrailingZeros(new BigDecimal(amount).multiply(new BigDecimal(100)).doubleValue());
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(long val) {
        return RapidBigDecimalUtils.stripTrailingZeros(val);
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(double val) {
        return RapidBigDecimalUtils.stripTrailingZeros(val);
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(String val) {
        return RapidBigDecimalUtils.stripTrailingZeros(val);
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(BigDecimal val) {
        return RapidBigDecimalUtils.stripTrailingZeros(val);
    }

    public static void main(String[] args) {
        System.out.println("----- 金额工具类 -----");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入操作（1-格式化金额 2-元转分 3-分转元 4-去除末尾多余的0）：");
            String inputOption = scanner.next();
            switch (inputOption) {
                case "1":
                    System.out.println("请输入金额（设置精度0.00,2表示保留两位小数）：");
                    String input1 = scanner.next();
                    if (input1.contains(",")) {
                        System.out.println(RapidAmountUtils.format(input1.split(",")[0], Integer.parseInt(input1.split(",")[1])));
                    } else {
                        System.out.println(RapidAmountUtils.format(input1));
                    }
                    break;
                case "2":
                    System.out.println("请输入金额 ：");
                    String input2 = scanner.next();
                    System.out.println(RapidAmountUtils.changeY2F(input2));
                    break;
                case "3":
                    System.out.println("请输入金额（设置精度0.00,2表示保留两位小数）：");
                    String input3 = scanner.next();
                    if (input3.contains(",")) {
                        System.out.println(RapidAmountUtils.changeF2Y(input3.split(",")[0], Integer.parseInt(input3.split(",")[1])));
                    } else {
                        System.out.println(RapidAmountUtils.changeF2Y(input3));
                    }
                    break;
                case "4":
                    System.out.println("请输入金额 ：");
                    String input4 = scanner.next();
                    System.out.println(RapidAmountUtils.stripTrailingZeros(input4));
                    break;
                default:
                    System.out.println("输入错误！");
                    break;
            }
        }
    }

}