package com.simile.plan.gdb.janus;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 * created by yitao on 2020/05/25
 */
public class DateUtils {

    public static Date utc2Local(String utcTime, String utcPatten) {
        SimpleDateFormat utc = new SimpleDateFormat(utcPatten);
        utc.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;

        try {
            utcDate = utc.parse(utcTime);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return utcDate;
    }

    public static Date parseDate(String time) throws ParseException {
        if (StringUtils.isBlank(time)) {
            return null;
        } else {
            if (time.contains("T")) {
                Date date = utc2Local(time, "yyyy-MM-dd'T'HH:mm:ss'Z'");
                if (date == null) {
                    return null;
                }
            }

            String format = "";
            time = time.replaceAll("[-|:| ]", "");
            if (time.length() == 8) {
                format = "yyyyMMdd";
            } else if (time.length() == 14) {
                format = "yyyyMMddHHmmss";
            }

            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(time);
            return date;
        }
    }

    public static boolean isDate(String time) throws ParseException {
        Date date = parseDate(time);
        return !Objects.isNull(date);
    }
}
