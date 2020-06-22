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
}
