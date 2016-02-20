package com.lapeevvd.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {
    public static final LocalDate MIN_DATE = LocalDate.of(0, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    public static boolean isBetween(LocalTime localTime, LocalTime start, LocalTime end){
        return localTime.compareTo(start) >=0 && localTime.compareTo(end) <=0;
    }

    public static boolean isBetween(LocalDateTime localDateTime, LocalDateTime start, LocalDateTime end){
        return localDateTime.compareTo(start) >=0 && localDateTime.compareTo(end) <=0;
    }

    // парсинг LocalDate & LocalTime

    public static LocalDate parseLocalDate(String respParamValue, LocalDate def){
        return StringUtils.isEmpty(respParamValue) ? def : LocalDate.parse(respParamValue);
    }

    public static LocalTime parseLocalTime(String respParamValue, LocalTime def){
        return StringUtils.isEmpty(respParamValue) ? def : LocalTime.parse(respParamValue);
    }

}
