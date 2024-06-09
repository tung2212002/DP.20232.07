package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    /**
     * Return a {@link String String} that represents the current time in the format
     * of yyyy-MM-dd HH:mm:ss.
     *
     * @return the current time as {@link String String}.
     */
    public static String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
