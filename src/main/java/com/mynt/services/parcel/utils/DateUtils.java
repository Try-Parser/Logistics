package com.mynt.services.parcel.utils;

import java.time.ZoneId;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * Date utilities
 */
public class DateUtils {
//    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss");
    private static ZoneId zone = ZoneId.systemDefault();

    /***
     * Check expiry
     * @param date voucher expiry
     * @return boolean
     */
    public static boolean isExpired(Date date) {
        LocalDate today = LocalDate.now(zone);
        LocalDate candidate = convertToLocalDateViaInstant(date);
        return candidate.isBefore(today);
    }

    /***
     * Convert Date to LocalDate
     * @param dateToConvert Date
     * @return LocalDate
     */
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
