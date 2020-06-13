package com.jye.rapidandroid.log;

/**
 * 描述：日志等级常量配置类
 * 创建人：jye-ixiaojye@163.com
 */
public enum LogLevel {

    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    private int index;

    LogLevel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
