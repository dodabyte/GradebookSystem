package com.example.lab2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String DATABASE_PATTERN = "yyyy-MM-dd";
    public static final String TIMESTAMP_PATTERN = "dd.MM.yyyy HH:mm:ss";

    public static final int DATE_LIMIT = 10;
    public static final int TIMESTAMP_LIMIT = 19;

    public static int getCurrentCourse(Date dateAdmission) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateAdmission);
        Period period = Period.between(LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)), LocalDate.now());
        int course = period.getYears() + 1;
        return course >= 1 && course <= 4 ? course : -1;
    }

    public static int getCurrentSemester(Date dateAdmission, int course) {
        if (course < 1) return -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateAdmission);
        return LocalDate.now().isAfter(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))) &&
                LocalDate.now().isBefore(LocalDate.of(calendar.get(Calendar.YEAR) + 1, 1, 31))
                ? course * 2 - 1 : course * 2;
    }

    public static String getStringFromDate(Date date, String pattern) {
        return (new SimpleDateFormat(pattern)).format(date);
    }

    public static Date getDateFromString(String date, String pattern) {
        try {
            return (new SimpleDateFormat(pattern)).parse(date);
        } catch (ParseException ignored) { return null; }
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
}
