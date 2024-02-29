package com.example.lab2.utils;

import com.example.lab2.AppManager;

public class TypesUtils {
    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isDate(String date) {
        return date.matches("\\d{1,2}.\\d{1,2}.\\d{4}");
    }

    public static boolean isTimestamp(String date) {
        return isDate(date) || date.matches("\\d{2}.\\d{2}.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");
    }

    public static boolean isFloat(String number) {
        try {
            Float.parseFloat(number);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isMark(String number) {
        try {
            int value = Integer.parseInt(number);
            return value >= 0 && value <= 100;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isCourse(String number) {
        try {
            int value = Integer.parseInt(number);
            return value >= 1 && value <= AppManager.getSpecializationsDao().findMaxStudyDuration();
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isSemester(String number) {
        try {
            int value = Integer.parseInt(number);
            return value >= 1 && value <= AppManager.getSpecializationsDao().findMaxStudyDuration() * 2;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
