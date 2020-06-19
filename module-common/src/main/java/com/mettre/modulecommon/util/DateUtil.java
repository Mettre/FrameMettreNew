package com.mettre.modulecommon.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public DateUtil() {
    }

    public static Long getCurrentTimeStr() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return Long.parseLong(now.format(formatter));
    }

    public static String dataConverterStr(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    public static String getCurrentTimeStrByPattern(String pattern) {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now.format(formatter);
    }

    public static LocalDateTime dataConverter(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static LocalDateTime dataConverterTime(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(LocalDate.parse(dateStr, formatter).atStartOfDay());
    }

    public static LocalDateTime calculateEndDate(LocalDateTime startDate, int cycle, int quantity) {
        return startDate.plus((long) (cycle * quantity), ChronoUnit.MONTHS);
    }

    public static LocalDate calculateEndDate(LocalDate startDate, int cycle) {
        return startDate.plus((long) cycle, ChronoUnit.MONTHS);
    }

    public static String MonthStatistics(String year, String month) {
        String betweenTime = "";
        if (StrUtil.isEmpty(month)) {
            betweenTime = year + "-01-01 00:00:00 between " + year + "-12-31 23:59:59";
        }
        switch (month) {
            case "1":
                betweenTime = year + "-01-01 00:00:00 between " + year + "-01-31 23:59:59";
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                break;
            case "9":
                break;
            case "10":
                break;
            case "11":
                break;
            case "12":
                break;
        }
        return betweenTime;
    }
}
