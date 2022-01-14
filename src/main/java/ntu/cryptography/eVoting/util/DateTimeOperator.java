package ntu.cryptography.eVoting.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeOperator {

    public static LocalDateTime StringToIsoDate(String date){
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static String IsoDateToString(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
