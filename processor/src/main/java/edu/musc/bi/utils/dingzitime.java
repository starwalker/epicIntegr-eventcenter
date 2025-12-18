package edu.musc.bi.utils;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
//import com.google.protobuf.Timestamp;

//import java.time.LocalDate;
//import java.time.Period;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class dingzitime {
    private static final Logger LOGGER = Logger.getLogger(dingzitime.class);

    public static final int calculateAge(final String dob) {
        int iResp = -1;
        try {
            if (!StringUtils.isBlank(dob)) {
                LocalDate birthDate = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate currentDate = LocalDate.now();
                Period dateDiff = Period.between(birthDate, currentDate);
                iResp = dateDiff.getYears();
            }
        } catch (Exception e) {
            LOGGER.errorv(
                    "calculateAge has an parse issue, check your dob's date format!! check your"
                            + " dob: {0}, error message: {1}",
                    dob, e);
        }
        return iResp;
    }

    /*
     *
    public static final Timestamp convertLocalDateTimeToGoogleTimestamp(LocalDateTime localDateTime) {
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Timestamp result =
                Timestamp.newBuilder()
                        .setSeconds(instant.getEpochSecond())
                        .setNanos(instant.getNano())
                        .build();

        return result;
    }
     */

    /*
     *
     */
    public static final String convertDateToString(LocalDate date) {
        String strDate = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        strDate = formatter.format(date);

        return strDate;
    }

    /*
     *
     */
    public static final String convertDateTimeToString(LocalDateTime dt) {
        String strDate = null;
        //
        // Inbuilt format
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        // Format LocalDateTime
        strDate = dt.format(formatter);

        return strDate;
    }

    /*
     *
     */
    public static final String getCurrTS() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        final long currentTimestamp = zonedDateTime.toInstant().toEpochMilli();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final String ts = localDateTime.format(formatter);
        return ts;
    }

    public static final LocalDateTime convertStringToLocalDT(final String strDate) {
        try {
            // 2022.02.23 09:33:22
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            // String text = "2011-10-02 18:48:05.123";
            LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
            // System.out.println(dateTime);

            return dateTime;
        } catch (Exception e) {
            // System.out.println("Exception :" + e);
            e.printStackTrace();
            return null;
        }
    }

    public static final String convertISO8601toISO8601noT(final String strDate) {
        // 2022-02-23T09:33:22Z
        final DateTimeFormatter formatter_t = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        // 2022-02-23 09:33:22
        final DateTimeFormatter formatter_no_t = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strDT = "";
        try {

            if (StringUtils.isNotBlank(strDate)) {
                LocalDateTime dateTime;
                if (StringUtils.containsIgnoreCase(strDate, "T")) {
                    dateTime = LocalDateTime.parse(strDate, formatter_t);
                    strDT = dateTime.format(formatter_no_t);
                } else {
                    dateTime = LocalDateTime.parse(strDate, formatter_no_t);
                    strDT = dateTime.format(formatter_no_t);

                }
                return strDT;
            }
        } catch (Exception e) {
            // System.out.println("Exception :" + e);
            e.printStackTrace();
        }
        return  strDT;
    }

    /**
     * @param input String representation of a date: YYYY-MM-DD
     * @return LocalDateTime result from converting the string. If the string is malformed, print an
     *     error and return null.
     */
    public static Optional<LocalDate> parsePossibleNullStringtoDate(final String input) {
        LocalDate ld = null;
        if (Objects.isNull(input)) {
            return Optional.ofNullable(ld);
        } else {
            try {
                ld = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return Optional.of(ld);
            } catch (DateTimeParseException e) {
                return Optional.ofNullable(ld);
            }
        }
    }
    /**
     * @param input String representation of a date: YYYY-MM-DD
     * @return LocalDateTime result from converting the string. If the string is malformed, print an
     *     error and return null.
     */
    public static LocalDateTime parsePossibleNullStringtoDateTime(final String input) {
        LocalDateTime ld = null;
        if (Objects.isNull(input) || StringUtils.isBlank(input)) {
            return ld;
        } else {
            try {
                ld = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return ld;
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    /**
     * @param ldDate LocalDate representation of a date: YYYY-MM-DD
     * @param strDate String representation of a date: YYYY-MM-DD
     * @return boolean result from converting the string. If the string is malformed, print an error
     *     and return null.
     */
    public static boolean isLater(final String dt1, final String dt2) {
        LocalDateTime firstDT = parsePossibleNullStringtoDateTime(dt1);
        LocalDateTime secondDT = parsePossibleNullStringtoDateTime(dt2);
        // System.out.println(String.format("%s - %s\r\n\r\n", firstDT, secondDT));
        boolean rst = false;

        if (!Objects.isNull(firstDT)) {
            if (!Objects.isNull(secondDT)) {
                final int diff = firstDT.compareTo(secondDT);
                if (diff>=0) {
                    rst = true;
                }
            } else {
                rst = true;
            }
        }
        return rst;
    }


}
