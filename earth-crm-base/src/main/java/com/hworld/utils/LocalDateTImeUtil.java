package com.hworld.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class LocalDateTImeUtil {


    /**
     * LocalDateTime转String
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String format(LocalDateTime localDateTime, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return df.format(localDateTime);
    }

    /**
     * String转LocalDateTime
     *
     * @param str
     * @param format
     * @return
     */
    public static LocalDateTime parse(String str, String format) {
        if (MyStringUtils.isNullParam(str) || MyStringUtils.isNullParam(format)) {
            return null;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(str, df);
    }

    /**
     * 将 Date 转为 LocalDateTime
     *
     * @param date
     * @return java.time.LocalDateTime;
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param beginDateTime
     * @param endDateTime
     * @return
     */
    public static Integer getDaySub(LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        return endDateTime.getDayOfYear() - beginDateTime.getDayOfYear();
    }

    /**
     * 获取一天最早的时间
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getFirstDateTimeOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取一天最晚的时间
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getLastDateTimeOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取一个月内的最早一天
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getFirstDateTimeOfMonth(LocalDateTime dateTime) {
        return LocalDateTime.of(LocalDate.from(dateTime.toLocalDate().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
    }

    /**
     * 获取一个月最后一天
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getLastDateTimeOfMonth(LocalDateTime dateTime) {
        return LocalDateTime.of(LocalDate.from(dateTime.toLocalDate().with(TemporalAdjusters.lastDayOfMonth())), LocalTime.MAX);
    }

    public static Date lastDateTimeToDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
