package com.lapeevvd.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {

    public static boolean isBetween(LocalTime localTime, LocalTime start, LocalTime end){
        return localTime.compareTo(start) >=0 && localTime.compareTo(end) <=0;
    }

    public static boolean isBetween(LocalDateTime localDateTime, LocalDateTime start, LocalDateTime end){
        return localDateTime.compareTo(start) >=0 && localDateTime.compareTo(end) <=0;
    }

    // TODO: 24.12.2015 парсинг LocalDate b & LocalTime вместе с StringUtils.isEmpty ? : ;

}
