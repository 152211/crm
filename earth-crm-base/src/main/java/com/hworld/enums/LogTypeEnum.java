package com.hworld.enums;

public enum LogTypeEnum {
    RUN_LOG("run_log", "运行日志"),
    ERROR_LOG("error_log", "错误日志"),
    ;

    LogTypeEnum() {

    }

    LogTypeEnum(String logType, String logName) {
        this.logType = logType;
        this.logName = logName;
    }

    public String getLogType() {
        return logType;
    }

    public String getLogName() {
        return logName;
    }

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 日志类型名称
     */
    private String logName;

}
