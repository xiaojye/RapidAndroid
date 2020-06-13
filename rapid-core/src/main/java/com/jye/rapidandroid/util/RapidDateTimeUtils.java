package com.jye.rapidandroid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述：时间相关工具类
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidDateTimeUtils {

    private RapidDateTimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取传入时间与当前时间的时间差提示
     *
     * @param timestamp 10/13位时间戳
     * @return 时间差提示文本，如刚刚，1分钟前等
     */
    public static String getTimeDiffTip(long timestamp) {
        long curTimestamp = System.currentTimeMillis() / 1000L;

        long timeDiff = curTimestamp - timestamp;
        if (String.valueOf(timestamp).length() == 13) {
            timeDiff = curTimestamp - timestamp / 1000L;
        }

        if (timeDiff < 60 && timeDiff >= 0) {
            return "刚刚";
        } else if (timeDiff >= 60 && timeDiff < 3600) {
            return timeDiff / 60 + "分钟前";
        } else if (timeDiff >= 3600 && timeDiff < 3600 * 24) {
            return timeDiff / 3600 + "小时前";
        } else if (timeDiff >= 3600 * 24 && timeDiff < 3600 * 24 * 30) {
            return timeDiff / 3600 / 24 + "天前";
        } else if (timeDiff >= 3600 * 24 * 30 && timeDiff < 3600 * 24 * 30 * 12) {
            return timeDiff / 3600 / 24 / 30 + "个月前";
        } else if (timeDiff >= 3600 * 24 * 30 * 12) {
            return timeDiff / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return timestampToString(timestamp, "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 获取传入时间与当前时间的时间差提示
     *
     * @param timeStr 日期字符串
     * @param pattern 时间格式
     * @return 时间差提示文本，如刚刚，1分钟前等
     */
    public static String getTimeDiffTip(String timeStr, String pattern) {
        return getTimeDiffTip(stringToTimestamp(timeStr, pattern));
    }

    /**
     * 时间戳转字符串
     *
     * @param timestamp 10/13位时间戳
     * @param pattern   时间格式
     * @return 按照指定格式转换的时间字符串
     */
    public static String timestampToString(long timestamp, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (String.valueOf(timestamp).length() == 10) {
            return simpleDateFormat.format(timestamp * 1000L);
        }
        return simpleDateFormat.format(timestamp);
    }

    /**
     * 字符串转时间戳
     *
     * @param timeStr 日期字符串
     * @param pattern 时间格式
     * @return 时间戳
     */
    public static long stringToTimestamp(String timeStr, String pattern) {
        Date date = stringToDate(timeStr, pattern);
        if (date != null) {
            return date.getTime();
        }
        return 0;
    }

    /**
     * 字符串转Date
     *
     * @param timeStr 日期字符串
     * @param pattern 时间格式
     * @return Date
     */
    public static Date stringToDate(String timeStr, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date转字符串
     *
     * @param date    Date
     * @param pattern 时间格式
     * @return 按照指定格式转换的时间字符串
     */
    public static String DateToString(Date date, String pattern) {
        return timestampToString(date.getTime(), pattern);
    }

    /**
     * 比较前面的时间是否大于后面的时间
     *
     * @param time1 时间1
     * @param time2 时间2
     * @return time1大于time2返回1，time1等于time2返回0，time1小于time2返回-1
     */
    public static int compare(Date time1, Date time2) {
        long timeMillis1 = time1.getTime();
        long timeMillis2 = time2.getTime();
        return Long.compare(timeMillis1, timeMillis2);
    }

    /**
     * 比较前面的时间是否大于后面的时间
     *
     * @param time1 时间1
     * @param time2 时间2
     * @return time1大于time2返回1，time1等于time2返回0，time1小于time2返回-1
     */
    public static int compare(long time1, long time2) {
        return Long.compare(time1, time2);
    }

    /**
     * 比较前面的时间是否大于后面的时间
     *
     * @param time1   时间1
     * @param time2   时间2
     * @param pattern 时间格式
     * @return time1大于time2返回1，time1等于time2返回0，time1小于time2返回-1
     * @throws ParseException 当时间格式解析失败时抛出该异常
     */
    public static int compare(String time1, String time2, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date1 = sdf.parse(time1);
        Date date2 = sdf.parse(time2);
        return Long.compare(date1.getTime(), date2.getTime());
    }

    /**
     * 比较前面的时间是否大于后面的时间
     *
     * @param time1        时间1
     * @param time2        时间2
     * @param time1Pattern 时间1格式
     * @param time2Pattern 时间2格式
     * @return time1大于time2返回1，time1等于time2返回0，time1小于time2返回-1
     * @throws ParseException 当时间格式解析失败时抛出该异常
     */
    public static int compare(String time1, String time2, String time1Pattern, String time2Pattern) throws ParseException {
        Date date1 = new SimpleDateFormat(time1Pattern).parse(time1);
        Date date2 = new SimpleDateFormat(time2Pattern).parse(time2);
        return Long.compare(date1.getTime(), date2.getTime());
    }

    /**
     * 得到昨天的日期
     *
     * @return 昨天的日期
     */
    public static Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 得到今天的日期
     *
     * @return 今天的日期
     */
    public static Date getTodayDate() {
        return new Date();
    }

    /**
     * 得到明天的日期
     *
     * @return 明天的日期
     */
    public static Date getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取下一个月的日期
     *
     * @return 基于系统时间获取下一个月的日期
     */
    public static Date getNextMonthDate() {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.add(Calendar.MONTH, +1); //月份加1
        return ca.getTime();
    }

    /**
     * 获取下一个月的日期
     *
     * @param curDate 当前日期
     * @return 根据传入的当前日期获取下一个月的日期
     */
    public static Date getNextMonthDate(Date curDate) {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(curDate);
        ca.add(Calendar.MONTH, +1); //月份加1
        return ca.getTime();
    }

    /**
     * 获取下N个月的日期
     *
     * @param nMonth 要增加的月数
     * @return 基于系统时间获取下N个月的日期
     */
    public static Date getNextNMonthDate(int nMonth) {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.add(Calendar.MONTH, +nMonth); //月份加n
        return ca.getTime();
    }

    /**
     * 获取下N个月的日期
     *
     * @param curDate 当前日期
     * @param nMonth  要增加的月数
     * @return 根据传入的当前日期获取下N个月的日期
     */
    public static Date getNextNMonthDate(Date curDate, int nMonth) {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(curDate);
        ca.add(Calendar.MONTH, +nMonth); //月份加n
        return ca.getTime();
    }

    /**
     * 获取前n天的日期
     *
     * @param n 具体前几天
     * @return 前n天的Date数组
     */
    public static Date[] getBeforeDays(int n) {
        Date[] days = new Date[n];

        for (int i = 0; i < n; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            days[i] = calendar.getTime();
        }

        return days;
    }

    /**
     * 获取前n天的日期
     *
     * @param curDate 当前日期
     * @param n       具体前几天
     * @return 前n天的Date数组
     */
    public static Date[] getBeforeDays(Date curDate, int n) {
        Date[] days = new Date[n];

        for (int i = 0; i < n; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            days[i] = calendar.getTime();
        }

        return days;
    }

    /**
     * 获取前n天的日期
     *
     * @param n       具体前几天
     * @param pattern 时间格式
     * @return 前n天的Date数组
     */
    public static String[] getBeforeDays(int n, String pattern) {
        String[] days = new String[n];

        for (int i = 0; i < n; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            days[i] = timestampToString(calendar.getTimeInMillis(), pattern);
        }

        return days;
    }

    /**
     * 获取前n天的日期
     *
     * @param curDate 当前日期时间戳
     * @param n       具体前几天
     * @param pattern 时间格式
     * @return 前n天的Date数组
     */
    public static String[] getBeforeDays(Date curDate, int n, String pattern) {
        String[] days = new String[n];

        for (int i = 0; i < n; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            days[i] = timestampToString(calendar.getTimeInMillis(), pattern);
        }

        return days;
    }

    /**
     * 获取前n天的日期
     *
     * @param n 具体前几天
     * @return 前n天的Date数组
     */
    public static long[] getBeforeDayTimestamps(int n) {
        long[] days = new long[n];

        for (int i = 0; i < n; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            days[i] = calendar.getTimeInMillis();
        }

        return days;
    }

    /**
     * 获取前n天的日期
     *
     * @param curDate 当前日期时间戳
     * @param n       具体前几天
     * @return 前n天的Date数组
     */
    public static long[] getBeforeDayTimestamps(Date curDate, int n) {
        long[] days = new long[n];

        for (int i = 0; i < n; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            days[i] = calendar.getTimeInMillis();
        }

        return days;
    }


    /**
     * 获取本周第一天
     *
     * @return 本周第一天Date
     */
    public static Date getWeekFirstDay() {
        Calendar firstCalendar = Calendar.getInstance();
        int firstDayOfWeek = firstCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (firstDayOfWeek == 0)
            firstDayOfWeek = 7;
        firstCalendar.add(Calendar.DATE, -firstDayOfWeek + 1);
        return firstCalendar.getTime();
    }

    /**
     * 获取本周最后一天
     *
     * @return 前周最后一天Date
     */
    public static Date getWeekLastDay() {
        Calendar lastCalendar = Calendar.getInstance();
        int lastDayOfWeek = lastCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (lastDayOfWeek == 0)
            lastDayOfWeek = 7;
        lastCalendar.add(Calendar.DATE, -lastDayOfWeek + 7);
        return lastCalendar.getTime();
    }


    /**
     * 获取本月第一天
     *
     * @return 本月第一天Date
     */
    public static Date getMonthFirstDay() {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return firstCalendar.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @return 前月最后一天Date
     */
    public static Date getMonthLastDay() {
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.set(Calendar.DAY_OF_MONTH, lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return lastCalendar.getTime();
    }

}