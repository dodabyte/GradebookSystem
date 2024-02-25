package com.example.lab2.controls;

import com.example.lab2.utils.DateUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class DateTextField extends TextField implements ChangeListener<String> {
    public DateTextField() {
        super();
        textProperty().addListener(this);
    }
    @Override
    public void changed(ObservableValue<? extends String> observableValue, String enchantmentKey, String t1) {
        if (t1.length() == 2 || t1.length() == 5) {
            setText(t1 + ".");
        }
        if (t1.length() > DateUtils.DATE_LIMIT) {
            setText(getText().substring(0, DateUtils.DATE_LIMIT));
        }
    }
}
