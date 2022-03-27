package cn.dgut.edu.css.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @Author 古力
 * @Date 2018/9/27 下午3:59
 */
@Slf4j
public class DateUtil {
    private static final int STATISTIC_BEGIN_HOUR = 4;
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 凌晨0点
     */
    public static final String WEE_HOURS = "00:00:00";
    /**
     * 午夜
     */
    public static final String MIDNIGHT = "23:59:59";

    /**
     * 前一周起始时间（当前日期往前推7天的0:00）
     *
     * @return
     */
    public static Date workweekStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -7);
        return calendar.getTime();
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getNowDate() {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(nowDate);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getNowDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTime() {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(nowDate);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTime(String format) {
        if (format == null || format.length() == 0) {
            return getNowTime();
        }
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(nowDate);
    }

    /**
     * Date类型转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static String localDateTime2String(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

        return localDateTime == null ? null : dateTimeFormatter.format(localDateTime);
    }

    public static LocalDateTime string2LocalDateTime(String localDateTimeStr, String format) {
        if (StringUtils.isEmpty(localDateTimeStr)) {
            return null;
        }
        return LocalDateTime.parse(localDateTimeStr, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 将HH:mm格式的时间字符串转换为当天时间
     *
     * @param timeStr
     * @return
     */
    public static Date getDateTimeByTimeStr(String timeStr) throws ParseException {
        Calendar result = getCalendarByTimeStr(timeStr);
        return result.getTime();
    }

    /**
     * @desc 将时间转换为标准的yyyy-MM-dd HH:mm:ss格式
     * @params [date]
     * @returnDesc
     * @author 古力 2018/10/14 上午11:29
     */
    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * @desc 返回当天是一周中的第几天，1是周一，7是周日
     * @params []
     * @returnDesc
     * @author 古力 2018/10/18 下午7:45
     */
    public static int dayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == dayOfWeek) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    /**
     * 返回当天是一周中的第几天，1是周一，7是周日
     * 
     * @param zoneId
     *            时区
     * @return
     */

    public static int dayOfWeek(ZoneId zoneId) {

        zoneId = defaultZoneId();

        return LocalDateTime.now(zoneId).get(ChronoField.DAY_OF_WEEK);
    }

    /**
     * @desc 返回某天是一周中的第几天，1是周一，7是周日
     * @params []
     * @returnDesc
     * @author yangxin 2019/10/18 下午7:45
     */
    public static int dayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == dayOfWeek) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    /**
     * 获取时间戳
     *
     * @param dateTime
     * @return
     */
    public static long getTimestampByLocalDateTime(LocalDateTime dateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = dateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * String->LocalDateTime
     */
    public static LocalDateTime strToLocalDateTime(String dateTime) {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * String->LocalTime
     */
    public static LocalTime strToLocalTime(String localTime) {
        return LocalTime.parse(localTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * String->LocalDate
     */
    public static LocalDate strToLocalDate(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     *
     * 功能描述: 由于前端传过来的日期格式不符合yyyy-MM-dd，故需要转为该格式
     *
     * @param:
     * @return:
     * @auther: liuyangfu
     * @date: 2018/12/29 下午12:19
     */
    public static String timeFormatParse(String dateTime) {
        StringBuilder builder = new StringBuilder();

        String[] dates = dateTime.split("\\-");
        builder.append(dates[0]);

        for (int i = 1; i < dates.length; i++) {
            if (dates[i].length() == 1) {
                builder.append("-0" + dates[i]);
            } else {
                builder.append("-" + dates[i]);
            }
        }

        return builder.toString();
    }

    /**
     * localDateTime 转化为前端格式 yyyy-MM-dd HH:mm:ss author @yanyang
     */
    public static String timeToStr(LocalDateTime time) {
        DateTimeFormatter noTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String noStr = time.format(noTime);
        return noStr;
    }

    public static String localDate2String(LocalDate localDate, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatter.format(localDate);
    }

    /**
     *
     * 功能描述: 获取格式为yyyyMMdd的日期格式
     *
     * @param:
     * @return:
     * @auther: liuyangfu
     * @date: 2019/1/23 下午4:32
     */
    public static String parseTimeFormat(String dateTime) {
        StringBuilder builder = new StringBuilder();

        String[] dates = dateTime.split("\\-");
        builder.append(dates[0]);

        for (int i = 1; i < dates.length; i++) {
            if (dates[i].length() == 1) {
                builder.append("0" + dates[i]);
            } else {
                builder.append(dates[i]);
            }
        }

        return builder.toString();
    }

    /**
     * @desc: 比较两个时间的大小
     * @param:
     * @return:
     * @auther: AnYuan
     * @date: 2019-02-28
     */
    public static boolean compareDate(String DATE1, String DATE2) {
        Integer res = DATE1.compareTo(DATE2);
        return res > 0;
    }

    /**
     * 校验格式HH:mm的时间,小时符合00-24，分钟符合00-59
     *
     * @param dateTime
     * @return
     */
    public static boolean checkTimeValueFormat(String dateTime) {

        String[] dateTimeArr = dateTime.split(":");
        if (Integer.parseInt(dateTimeArr[0]) < 0 || Integer.parseInt(dateTimeArr[0]) > 24) {
            return false;
        }
        if (Integer.parseInt(dateTimeArr[1]) < 0 || Integer.parseInt(dateTimeArr[1]) > 59) {
            return false;
        }
        return true;
    }

    /**
     * 将HH:mm格式的时间字符串转换为当天时间 Calendar
     *
     * @param timeStr
     * @return
     * @throws ParseException
     */
    public static Calendar getCalendarByTimeStr(String timeStr) throws ParseException {
        Calendar result = Calendar.getInstance();
        result.setTime(new Date());
        Date date = new SimpleDateFormat("HH:mm").parse(timeStr);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH), result.get(Calendar.DATE),
            time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), 0);
        if ("24:00".equals(timeStr)) {
            result.add(Calendar.DATE, 1);
        }
        return result;
    }

    /**
     * 将calendar日期类型转换成HH:mm格式的字符串
     * 
     * @param calendar
     * @return
     */
    public static String getTimeStr(Calendar calendar) {
        Date date = calendar.getTime();
        return new SimpleDateFormat("HH:mm").format(date);
    }

    /**
     * 将calendar日期类型转换成yyyy-MM-dd的字符串
     * 
     * @param calendar
     * @return
     */
    public static String getCalendarStr(Calendar calendar) {
        Date date = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 判断格式为yyyy-MM-dd的timeStr是否为当天
     * 
     * @param timeStr
     * @return
     */
    public static boolean isToday(String timeStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(timeStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);

        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(new Date());

        if (calendar.get(Calendar.YEAR) != calendarToday.get(Calendar.YEAR)) {
            return false;
        }
        if (calendar.get(Calendar.MONTH) != calendarToday.get(Calendar.MONTH)) {
            return false;
        }
        if (calendar.get(Calendar.DATE) != calendarToday.get(Calendar.DATE)) {
            return false;
        }
        return true;
    }

    /**
     * 功能描述：判断该时间是否为今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(LocalDateTime time) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) != time.getYear()) {
            return false;
        }
        if ((calendar.get(Calendar.MONTH) + 1) != time.getMonthValue()) {
            return false;
        }
        if (calendar.get(Calendar.DATE) != time.getDayOfMonth()) {
            return false;
        }
        return true;
    }

    /**
     * 功能描述：判断该时间是否为今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(LocalDateTime time, ZoneId zoneId) {
        zoneId = defaultZoneId();
        LocalDate localDate = time.atZone(zoneId).toLocalDate();
        return LocalDate.now(zoneId).isEqual(localDate);
    }

    /**
     * 根据开始时间和结束时间判断是否为一整天(精确到秒)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Boolean isAllDay(LocalTime startTime, LocalTime endTime) {
        LocalTime startLocalTime = LocalTime.parse("00:00:00");
        LocalTime endLocalTime = LocalTime.parse("23:59:59");
        return startLocalTime.equals(startTime) && endLocalTime.equals(endTime);
    }

    public static ZoneId defaultZoneId() {
        return ZoneId.of("+8");
    }

    /**
     * zoneId 转换成 +08：00 String
     * 
     * @param zoneId
     * @return
     */
    public static String zoneIdToString(ZoneId zoneId) {
        if (Objects.isNull(zoneId)) {
            return null;
        }
        // LocalDateTime -> ZonedDateTime
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(zoneId);

        // ZonedDateTime -> ZoneOffset
        ZoneOffset zoneOffset = zonedDateTime.getOffset();

        // replace Z to +00:00
        return zoneOffset.getId().replaceAll("Z", "+00:00");
    }

    public static String localDateTimeFormatDate(LocalDateTime localDateTime) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD);
        return localDateTime.format(dtf2);
    }

    /**
     * 时间戳转
     * 
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
        return localDateTime;
    }
}
