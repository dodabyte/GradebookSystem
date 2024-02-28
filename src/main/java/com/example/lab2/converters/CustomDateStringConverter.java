package com.example.lab2.converters;

import javafx.util.converter.DateStringConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class CustomDateStringConverter extends DateStringConverter {
    public CustomDateStringConverter(final String pattern) {
        super(pattern);
    }

    @Override
    public Date fromString(String value) {
        try {
            if (value == null) {
                return null;
            } else {
                value = value.trim();
                if (value.length() < 1) {
                    return null;
                } else {
                    DateFormat formatter = this.getDateFormat();
                    Date currentDate = new Date();
                    if (formatter.parse(value).after(currentDate)) {
                        return null;
                    }
                    return formatter.parse(value);
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
