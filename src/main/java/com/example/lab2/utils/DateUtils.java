package com.example.lab2.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
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
}
