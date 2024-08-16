package com.example.tech4good_server.global.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateUtil {
    public static final long ONE_HOUR_IN_MILLISECONDS = 1000 * 60 * 60;
    public static final long ONE_MONTH_IN_MILLISECONDS = 1000L * 60 * 60 * 24 * 30;
    public static final ZoneId KST_ZONE = ZoneOffset.ofHours(9);

    /**
     * 특정 지역의 시간을 unix time 으로 변환
     */
    public static long dateToEpochMilli(LocalDateTime date){
        return date == null ? 0 : date.atZone(DateUtil.KST_ZONE).toInstant().toEpochMilli();
    }

    /**
     * unix time 을 특정 지역의 시간으로 변환
     */
    public static LocalDateTime epochToDate(long txTime) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(txTime), DateUtil.KST_ZONE);
    }
}
