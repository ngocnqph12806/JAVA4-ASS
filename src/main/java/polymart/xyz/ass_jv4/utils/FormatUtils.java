package polymart.xyz.ass_jv4.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

    private static FormatUtils formatUtils;

    private static final String dateFormater = "yyyy-MM-dd";

    public String dateToString(Date date) {
        return new SimpleDateFormat(dateFormater).format(date);
    }

    public Date stringToDate(String date) {
        try {
            return new SimpleDateFormat(dateFormater).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FormatUtils getFormatUtils() {
        if (formatUtils == null) {
            formatUtils = new FormatUtils();
        }
        return formatUtils;
    }

}
