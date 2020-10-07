package helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    private final static String DATE_TIME_LOCAL_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static Date parseDateTimeLocal(String htmlDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATE_TIME_LOCAL_PATTERN);
        Date date = (Date)formatter.parse(htmlDate);
        return date;
    }
}
