package com.sgm.liteapp.cloudapi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class DateUtils {

    public static Date getCurrentDate() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Shanghai"));
        return calendar.getTime();
    }

    public static Integer getPartitionFlagBasedOnCurrentDate() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Shanghai"));
        return calendar.get(Calendar.YEAR) * 10000
                + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
    }
}
