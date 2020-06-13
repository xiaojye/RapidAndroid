package com.jye.rapidandroid.log;

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
public final class LogConfig {

    /**
     * 日志是否可用，默认可用
     */
    private boolean logEnable = true;

    /**
     * 日志等级，默认可打印 ERROR 级别的日志
     */
    private LogLevel logLevel = LogLevel.ERROR;

    public boolean isLogEnable() {
        return logEnable;
    }

    public void setLogEnable(boolean logEnable) {
        this.logEnable = logEnable;
    }

    public LogConfig setLogLevel(LogLevel level) {
        this.logLevel = level;
        return this;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

}
