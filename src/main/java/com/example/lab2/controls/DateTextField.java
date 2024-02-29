package com.example.lab2.controls;

import com.example.lab2.controls.global.AbstractTextField;
import com.example.lab2.converters.CustomDateStringConverter;
import com.example.lab2.utils.DateUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTextField extends AbstractTextField implements ChangeListener<String> {
    public DateTextField() {
        super();
        textProperty().addListener(this);
    }

    public DateTextField(String text) {
        super(text);
        textProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String previousValue, String newValue) {
        String currentValue = newValue.replaceAll("[^\\d.]|[.]{2,}", "").
                replaceAll("[.]{2,}", ".");
        setText(currentValue);

        setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) return;
            if (keyEvent.getCode().equals(KeyCode.BACK_SPACE) && currentValue.length() > 0 &&
                    currentValue.charAt(currentValue.length() - 1) == '.' &&
                    currentValue.length() < previousValue.length()) {
                setText(currentValue.substring(0, currentValue.length() - 2));
                positionCaret(currentValue.length() - 1);
            }
        });

        if (currentValue.length() == 2 || currentValue.length() == 5) {
            setText(currentValue + ".");
        }

        if (currentValue.length() > DateUtils.DATE_LIMIT) {
            setText(getText().substring(0, DateUtils.DATE_LIMIT));
        }
    }
}
