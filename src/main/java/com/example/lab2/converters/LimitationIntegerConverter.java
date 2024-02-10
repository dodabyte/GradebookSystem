package com.example.lab2.converters;

import javafx.util.converter.IntegerStringConverter;

public class LimitationIntegerConverter extends IntegerStringConverter {
    private final int leftValue;
    private final int rightValue;

    public LimitationIntegerConverter(int leftValue, int rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public Integer fromString(String string) {
        if (string == null) {
            return null;
        } else {
            string = string.trim();
            int value = string.length() < 1 ? null : Integer.valueOf(string);
            return value >= leftValue && value <= rightValue ? null : value;
        }
    }
}
