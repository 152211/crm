package com.hworld.utils;

import com.hworld.constants.DatePatternConstant;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by CAOZHIHUI on 2015/12/14.
 */
@Log4j2
public class DateUtil {

    public static final String DEFAULT_TIME_ZONE = "UTC";
    public static Long NANO_ONE_SECOND = 1000L;
    public static Long NANO_ONE_MINUTE = 60 * NANO_ONE_SECOND;
    public static Long NANO_ONE_HOUR = 60 * NANO_ONE_MINUTE;
    public static Long NANO_ONE_DAY = 24 * NANO_ONE_HOUR;

    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return DatePatternConstant.YYYY_MM_DD;
    }

    public static String getDateTimePattern() {
        return DatePatternConstant.YYYY_MM_DD_HH_MM_SS;
    }

    public static Date parse(String dateStr, String formatter) {
        if (StringUtils.isBlank(dateStr))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean dateIsAcross(String leftBeginTime, String leftEndTime, String rightBeignTime, String rightEndTime, String formatText) {
        SimpleDateFormat format = new SimpleDateFormat(formatText);
        Date leftStartDate = null;
        Date leftEndDate = null;
        Date rightStartDate = null;
        Date rightEndDate = null;
        try {
            leftStartDate = format.parse(leftBeginTime);
            leftEndDate = format.parse(leftEndTime);
            rightStartDate = format.parse(rightBeignTime);
            rightEndDate = format.parse(rightEndTime);
        } catch (ParseException e) {
            log.error("出现异常：{}", e);
        }
        if (rightStartDate == null || rightEndDate == null || leftStartDate == null || leftEndDate == null) {
            return false;
        }

        long d1 = rightStartDate.getTime();
        long d2 = rightEndDate.getTime();
        long start = leftStartDate.getTime();
        long end = leftEndDate.getTime();
        if (((d1 - start) >= 0) && ((end - d2) >= 0)) {
            return true;
        }
        return false;
    }

    public static Date parse(Date data, String formatter) {

        return data == null ? null : parse(format(data, formatter), formatter);
    }

    public static Date parse(String dateStr) {
        if (StringUtils.isBlank(dateStr))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(DatePatternConstant.YYYY_MM_DD_HH_MM_SS);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static final String format(Date date, String formatter) {
        if (date == null)
            return StringUtils.EMPTY;
        return new SimpleDateFormat(formatter).format(date);
    }

    public static final String format(String date, String formatter) {
        if (StringUtils.isBlank(date))
            return StringUtils.EMPTY;
        return new SimpleDateFormat(formatter).format(date);
    }

    public static final String format(Date date) {
        if (date == null)
            return StringUtils.EMPTY;
        return new SimpleDateFormat(DatePatternConstant.YYYY_MM_DD_HH_MM_SS).format(date);
    }

    /**
     * 获取日期，即 yyyy-MM-dd 00:00:00.000
     */
    public static final Date getDate(Date date) {
        return getDate(date, DatePatternConstant.YYYY_MM_DD);
    }

    public static final Date getDate(Date date, String formatter) {
        return parse(format(date, formatter), formatter);
    }

    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 增加日期的天数。失败返回null。
     */
    public static Date addDays(Date date, int dayAmount) {
        return addTime(date, Calendar.DAY_OF_YEAR, dayAmount);
    }

    public static final Date addTime(Date date, int timeType, int amount) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(timeType, amount);
        return cal.getTime();
    }

    public static Date addMonths(Date date, int monthAmount) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthAmount);
        return cal.getTime();
    }

    public static Date addYear(Date date, int value) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, value);
        return cal.getTime();
    }

    public static Date addMinites(Date date, int amount) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }

    /**
     * @param bDate 开始时间
     * @param eDate 结束时间
     * @return 两者时间天差
     */
    public static Integer getIntervalDays(Date bDate, Date eDate) {

        if (bDate == null || eDate == null) {
            return 0;
        }

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(bDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(eDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;
    }


    public static Integer compareDate(Date beginTime, Date endTime) {
        int flag = endTime.compareTo(beginTime);
        return flag;
    }

    /**
     * @param value 天数
     * @return 增加后的日期
     */
    public static Date addDay(int value) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_YEAR, value);
        return now.getTime();
    }

    /**
     * @param date  指定日期
     * @param value 天数
     * @return 增加后的日期
     */
    public static Date addDay(Date date, int value) {
        if (date == null) return null;
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DAY_OF_YEAR, value);
        return now.getTime();
    }

    public static Date today() {
        return getDate(new Date());
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static String getWeekNameOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取两个日期之间相差的天数
     */
    public static Long getDiffDays(Date startDate, Date endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return null;
        }
        return getDiffMillis(getDate(startDate), getDate(endDate)) / NANO_ONE_DAY;
    }

    public static Long getDiffMillis(Date startDate, Date endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return null;
        }
        return endDate.getTime() - startDate.getTime();
    }


    public static int getDateField(Date date, int field) {
        Calendar c = getCalendar();
        c.setTime(date);
        return c.get(field);
    }

    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static int getDaysBetweenDate(Date begin, Date end) {
        return (int) ((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 根据时间获取该月份的最大天数
     *
     * @param date
     * @return
     */
    public static int getMonthMaxDay(String date) {
        Calendar c = getCalendar();
        c.setTime(Objects.requireNonNull(parse(date, DatePatternConstant.YYYY_MM_DD)));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String format = format(c.getTime(), DatePatternConstant.YYYY_MM_DD);
        String substring = format.substring(8, 10);
        return Integer.parseInt(substring);
    }

    public static Date getMonthFirstDay(String date) {
        Calendar c = getCalendar();
        c.setTime(Objects.requireNonNull(parse(date, DatePatternConstant.YYYY_MM_DD)));
        c.set(Calendar.DAY_OF_MONTH, 1);

        return c.getTime();
    }

    public static Date getMonthLastDay(String date) {
        Calendar c = getCalendar();
        c.setTime(Objects.requireNonNull(parse(date, DatePatternConstant.YYYY_MM_DD)));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static String addDay(String date, int value) {

        return format(addDay(parse(date, DatePatternConstant.YYYY_MM_DD), value), DatePatternConstant.YYYY_MM_DD);
    }

    public static String addMonths(String date, int value) {

        return format(addMonths(parse(date, DatePatternConstant.YYYY_MM_DD), value), DatePatternConstant.YYYY_MM_DD);
    }

    /**
     * 通过年 周 获取该周开始时间
     */
    public static String getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 通过年 周 获取该周结束时间
     */
    public static String getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH);
    }


    public static int getWeeksByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getMonthByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getYearByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDayByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public static Date addWeek(Date date, int val) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, val);
        return cal.getTime();
    }

    public static int getTimestamp(Date date) {
        if (date == null) {
            return 0;
        }
        return Math.toIntExact(date.getTime() / 1000);
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, DatePatternConstant.YYYY_MM_DD);
        }
        return formatDate;
    }

    public static List<String> collectLocalDates(String timeStart, String timeEnd) {
        return collectLocalDates(LocalDate.parse(timeStart), LocalDate.parse(timeEnd));
    }

    public static List<String> collectLocalMonths(String timeStart, String timeEnd) {
        return collectLocalMonths(LocalDate.parse(timeStart), LocalDate.parse(timeEnd));
    }

    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年最后一天日期
     *
     * @paramyear年份
     * @returnDate
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }


    public static List<String> collectLocalDates(LocalDate start, LocalDate end) {
        // 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
        return Stream.iterate(start, localDate -> localDate.plusDays(1))
                // 截断无限流，长度为起始时间和结束时间的差+1个
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                // 由于最后要的是字符串，所以map转换一下
                .map(LocalDate::toString)
                // 把流收集为List
                .collect(Collectors.toList());
    }

    public static List<String> collectLocalMonths(LocalDate start, LocalDate end) {
        // 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
        return Stream.iterate(start, localDate -> localDate.plusMonths(1))
                // 截断无限流，长度为起始时间和结束时间的差+1个
                .limit(ChronoUnit.MONTHS.between(start, end) + 1)
                // 由于最后要的是字符串，所以map转换一下
                .map(l -> l.format(DateTimeFormatter.ofPattern("yyyy-MM")))
                // 把流收集为List
                .collect(Collectors.toList());
    }


    public static int getDiffHour(Date compareTime, Date now) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = compareTime.getTime() - now.getTime();
        // 计算差多少小时
        long hour = diff % nd / nh;
        return Integer.valueOf(String.valueOf(hour));

    }

    public static String getEngMonthByDate(Date date) {
        int month = getMonthByDate(date);
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return null;
        }
    }

    public static String getDHDate(Date date) {
        Date changeDate = DateUtil.parse(date, DatePatternConstant.YYYY_MM_DD);
        int year = DateUtil.getYearByDate(changeDate);
        String month = getEngMonthByDate(changeDate).substring(0, 3);
        int day = DateUtil.getDayByDate(changeDate);

        return day + " " + month + " " + year;
    }

    /**
     * 对日期(时间)中的日进行加减计算. <br>
     * 例子: <br>
     * 如果Date类型的d为 2005年8月20日,那么 <br>
     * calculateByDate(d,-10)的值为2005年8月10日 <br>
     * 而calculateByDate(d,+10)的值为2005年8月30日 <br>
     *
     * @param d      日期(时间).
     * @param amount 加减计算的幅度.+n=加n天;-n=减n天.
     * @return 计算后的日期(时间).
     */
    public static Date calculateByDate(Date d, int amount) {
        return calculate(d, GregorianCalendar.DATE, amount);
    }

    public static Date calculateByMinute(Date d, int amount) {
        return calculate(d, GregorianCalendar.MINUTE, amount);
    }

    public static Date calculateByYear(Date d, int amount) {
        return calculate(d, GregorianCalendar.YEAR, amount);
    }

    /**
     * 对日期(时间)中由field参数指定的日期成员进行加减计算. <br>
     * 例子: <br>
     * 如果Date类型的d为 2005年8月20日,那么 <br>
     * calculate(d,GregorianCalendar.YEAR,-10)的值为1995年8月20日 <br>
     * 而calculate(d,GregorianCalendar.YEAR,+10)的值为2015年8月20日 <br>
     *
     * @param d      日期(时间).
     * @param field  日期成员. <br>
     *               日期成员主要有: <br>
     *               年:GregorianCalendar.YEAR <br>
     *               月:GregorianCalendar.MONTH <br>
     *               日:GregorianCalendar.DATE <br>
     *               时:GregorianCalendar.HOUR <br>
     *               分:GregorianCalendar.MINUTE <br>
     *               秒:GregorianCalendar.SECOND <br>
     *               毫秒:GregorianCalendar.MILLISECOND <br>
     * @param amount 加减计算的幅度.+n=加n个由参数field指定的日期成员值;-n=减n个由参数field代表的日期成员值.
     * @return 计算后的日期(时间).
     */
    private static Date calculate(Date d, int field, int amount) {
        if (d == null)
            return null;
        GregorianCalendar g = new GregorianCalendar();
        g.setGregorianChange(d);
        g.add(field, amount);
        return g.getTime();
    }

    /**
     * 日期(时间)转化为字符串.
     *
     * @param formater 日期或时间的格式.
     * @param aDate    java.util.Date类的实例.
     * @return 日期转化后的字符串.
     */
    public static String date2String(String formater, Date aDate) {
        if (formater == null || "".equals(formater))
            return null;
        if (aDate == null)
            return null;
        return (new SimpleDateFormat(formater)).format(aDate);
    }

    /**
     * 当前日期(时间)转化为字符串.
     *
     * @param formater 日期或时间的格式.
     * @return 日期转化后的字符串.
     */
    public static String date2String(String formater) {
        return date2String(formater, new Date());
    }

    /**
     * 获取当前日期对应的星期数.
     * <br>1=星期天,2=星期一,3=星期二,4=星期三,5=星期四,6=星期五,7=星期六
     *
     * @return 当前日期对应的星期数
     */
    public static int dayOfWeek() {
        GregorianCalendar g = new GregorianCalendar();
        int ret = g.get(java.util.Calendar.DAY_OF_WEEK);
        g = null;
        return ret;
    }


    /**
     * 获取所有的时区编号. <br>
     * 排序规则:按照ASCII字符的正序进行排序. <br>
     * 排序时候忽略字符大小写.
     *
     * @return 所有的时区编号(时区编号已经按照字符[忽略大小写]排序).
     */
    public static String[] fecthAllTimeZoneIds() {
        Vector v = new Vector();
        String[] ids = TimeZone.getAvailableIDs();
        for (int i = 0; i < ids.length; i++) {
            v.add(ids[i]);
        }
        java.util.Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        v.copyInto(ids);
        v = null;
        return ids;
    }

//    /**
//     * 测试的main方法.
//     *
//     * @param argc
//     */
//    public static void main(String[] argc) {
//
//        String[] ids = fecthAllTimeZoneIds();
//        String nowDateTime =date2String("yyyy-MM-dd HH:mm:ss");
//        System.out.println("The time Asia/Shanhai is " + nowDateTime);//程序本地运行所在时区为[Asia/Shanhai]
//        //显示世界每个时区当前的实际时间
//        for(int i=0;i <ids.length;i++){
//            System.out.println(" * " + ids[i] + "=" + string2TimezoneDefault(nowDateTime,ids[i]));
//        }
//        //显示程序运行所在地的时区
//        System.out.println("TimeZone.getDefault().getID()=" +TimeZone.getDefault().getID());
//    }

    /**
     * 将日期时间字符串根据转换为指定时区的日期时间.
     *
     * @param srcFormater   待转化的日期时间的格式.
     * @param srcDateTime   待转化的日期时间.
     * @param dstFormater   目标的日期时间的格式.
     * @param timeZone      需要转换的时区编号.
     * @param dstTimeZoneId 目标的时区编号.
     * @return 转化后的日期时间.
     */
    public static String string2Timezone(String srcFormater,
                                         String srcDateTime, String dstFormater, String timeZone, String dstTimeZoneId) {
        if (srcFormater == null || "".equals(srcFormater))
            return null;
        if (srcDateTime == null || "".equals(srcDateTime))
            return null;
        if (dstFormater == null || "".equals(dstFormater))
            return null;
        if (timeZone == null || "".equals(timeZone))
            return null;
        if (dstTimeZoneId == null || "".equals(dstTimeZoneId))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(srcFormater);
        try {
            Date d = sdf.parse(srcDateTime);
            int diffTime = getDiffTimeZoneRawOffset(timeZone, dstTimeZoneId, d);
            long nowTime = d.getTime();
            long newNowTime = nowTime - diffTime;
            d = new Date(newNowTime);
            return date2String(dstFormater, d);
        } catch (ParseException e) {
//            Log.output(e.toString(), Log.STD_ERR);
            return null;
        } finally {
            sdf = null;
        }
    }

    /**
     * 获取系统当前默认时区与UTC的时间差.(单位:毫秒)
     *
     * @return 系统当前默认时区与UTC的时间差.(单位 : 毫秒)
     */
    private static int getDefaultTimeZoneRawOffset() {
        return TimeZone.getDefault().getRawOffset();
    }

    /**
     * 获取指定时区与UTC的时间差.(单位:毫秒)
     *
     * @param timeZoneId 时区Id
     * @return 指定时区与UTC的时间差.(单位 : 毫秒)
     */
    private static int getTimeZoneRawOffset(String timeZoneId) {
        return TimeZone.getTimeZone(timeZoneId).getRawOffset();
    }

    /**
     * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒)
     *
     * @param dstTimeZoneId 目标时区Id
     * @return 系统当前默认时区与指定时区的时间差.(单位 : 毫秒)
     */
    private static int getDiffTimeZoneRawOffset(String timeZone, String dstTimeZoneId, Date date) {

        if (timeZone.equalsIgnoreCase(dstTimeZoneId)) {
            return 0;
        }
        if ("Europe/Berlin".equalsIgnoreCase(timeZone)) {
            if (isSummerCampTime(date)) {
                //夏令时
                return TimeZone.getTimeZone(timeZone).getRawOffset()
                        - TimeZone.getTimeZone(dstTimeZoneId).getRawOffset() + 3600000;
            }
        }

        if ("Europe/Berlin".equalsIgnoreCase(dstTimeZoneId)) {
            if (isSummerCampTime(date)) {
                //夏令时
                return TimeZone.getTimeZone(timeZone).getRawOffset()
                        - TimeZone.getTimeZone(dstTimeZoneId).getRawOffset() - 3600000;
            }
        }

        return TimeZone.getDefault().getRawOffset()
                - TimeZone.getTimeZone(dstTimeZoneId).getRawOffset();
    }

    /**
     * 获取一个日期当月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);

        return calendar.getTime();
    }

    /**
     * 获取一个日期当月最后一个周日日期
     *
     * @param date
     * @return
     */
    public static Date getLastSunday(Date date) {
        Date lastDay = getLastDayOfMonth(date);
        int week = MyStringUtils.stringToInteger(getWeekOfDate(lastDay));
        Date lastSunday = week == 7 ? lastDay : new Date(lastDay.getTime() - 86400000 * week);

        return lastSunday;
    }

    /**
     * 获取指定日期的年
     *
     * @return
     */
    public static String getDateYear(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DatePatternConstant.YYYY);
        return df.format(date);
    }

    /**
     * 获取指定日期的时
     *
     * @return
     */
    public static String getDateHour(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DatePatternConstant.HH);
        return df.format(date);
    }

    /**
     * 判断是否是冬令时
     *
     * @param date
     * @return
     */
    public static Boolean isWinterCampTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DatePatternConstant.YYYY_MM_DD);
        try {
            date = df.parse(df.format(date));
            //冬令时 开始日期
            Date winterCampDate = getLastSunday(df.parse(getDateYear(date) + "-10-01 03"));
            //夏令时 开始日期
            Date summerCampDate = getLastSunday(df.parse(getDateYear(date) + "-03-01 02"));
            //当前时间大于等于3月 最后一个周日 并且小于10月最后一个周日 则不是冬令时
            if (date.getTime() >= summerCampDate.getTime() && date.getTime() < summerCampDate.getTime()) {
                return false;
            }
            if (MyStringUtils.stringToInteger(getDateHour(date)).intValue() >= 3) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否是夏令时
     *
     * @param date
     * @return
     */
    public static Boolean isSummerCampTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DatePatternConstant.YYYY_MM_DD_HH);
        try {
            //冬令时 开始日期
            Date winterCampDate = getLastSunday(df.parse(getDateYear(date) + "-10-01 03"));
            //夏令时 开始日期
            Date summerCampDate = getLastSunday(df.parse(getDateYear(date) + "-03-01 02"));

            if (date.getTime() < summerCampDate.getTime() || date.getTime() >= winterCampDate.getTime()) {

                return false;
            }

            if (MyStringUtils.stringToInteger(getDateHour(date)).intValue() >= 2) {
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    /**
     * 将日期时间字符串根据转换为指定时区的日期时间.
     *
     * @param srcDateTime   待转化的日期时间.
     * @param timeZone      需要转换的时区.
     * @param dstTimeZoneId 目标的时区编号.
     * @return 转化后的日期时间.
     * @see #string2Timezone(String, String, String, String, String)
     */
    public static String string2TimezoneDefault(String srcDateTime, String timeZone,
                                                String dstTimeZoneId) {
        return string2Timezone("yyyy-MM-dd HH:mm:ss", srcDateTime,
                "yyyy-MM-dd HH:mm:ss", timeZone, dstTimeZoneId);
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DatePatternConstant.YYYY_MM_DD_HH_MM_SS);
        Date date = df.parse("2021-03-01 01:59:59");
//        System.out.println("lastSunday = " + getDiffTimeZoneRawOffset("Europe/Berlin", date) / 3600000);
    }

}
