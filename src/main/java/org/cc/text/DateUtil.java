package org.cc.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DateUtil {
    public static String sfmt = "yyyyMMdd";
    public static String lfmt = "yyyyMMddHHmmss";
    public static String afmt = "yyyyMMddHHmmssSSS";
    public static String cstfmt = "EEE MMM dd HH:mm:ss zzz yyyy";
    
    public static Date _date(Object o) {
        if (o instanceof Date) {
            return (Date) o;
        } else if (o instanceof String) {
            return to_date((String) o);
        }
        return null;
    }

    public static Date to_date(String text) {
        if (text.contains("CST ")) {
            return to_cst(text);
        }
        String str = text.replaceAll("[^0-9\\.]+", "");
        int len = str.length();
        switch (len) {
            case 8:
                return to_date(sfmt, str);
            case 14:
                return to_date(lfmt, str);
        }
        return null;
    }

    public static Date to_date(String fmt, String text) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return sdf.parse(text);
        } catch (ParseException ex) {
            log.warn("Can't cast " + fmt+","+text);
        }
        return null;
    }

    public static Date to_cst(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat(cstfmt, Locale.US);
        try {
            return sdf.parse(text);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
