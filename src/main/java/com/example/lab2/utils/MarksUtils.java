package com.example.lab2.utils;

public class MarksUtils {
    public static String getEctsMark(int mark) {
        if (mark < 0 || mark > 100) return "unknown";
        if (mark >= 98) return "A+";
        else if (mark >= 93) return "A";
        else if (mark >= 90) return "A-";
        else if (mark >= 87) return "B+";
        else if (mark >= 83) return "B";
        else if (mark >= 80) return "B-";
        else if (mark >= 77) return "C+";
        else if (mark >= 73) return "C";
        else if (mark >= 70) return "C-";
        else if (mark >= 67) return "D+";
        else if (mark >= 63) return "D";
        else if (mark >= 60) return "D-";
        else if (mark >= 50) return "E";
        else if (mark >= 25) return "FX";
        else return "F";
    }

    public static int getTraditionalMark(int mark) {
        if (mark < 0 || mark > 100) return 0;
        if (mark >= 87) return 5;
        else if (mark >= 73) return 4;
        else if (mark >= 50) return 3;
        else return 2;
    }

    public static String getTraditionalWordMark(int mark, String typeOfMark) {
        if (mark < 0 || mark > 100) return "unknown";
        if (typeOfMark.equalsIgnoreCase("зачёт")) {
            if (mark >= 50) return "Зачтено";
            else return "Не зачтено";
        } else {
            if (mark >= 87) return "Отлично";
            else if (mark >= 73) return "Хорошо";
            else if (mark >= 50) return "Удовлетворительно";
            else return "Неудовлетворительно";
        }
    }
}
