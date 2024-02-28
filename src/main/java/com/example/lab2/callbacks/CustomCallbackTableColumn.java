package com.example.lab2.callbacks;

import com.example.lab2.cells.CustomTextFieldTableCell;
import com.example.lab2.controls.CustomTextField;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class CustomCallbackTableColumn<S, T> implements Callback<TableColumn<S, T>, CustomTextFieldTableCell<S, T>> {

    public CustomCallbackTableColumn() {}

    @Override
    public CustomTextFieldTableCell<S, T> call(TableColumn<S, T> ttTableColumn) {
        return new CustomTextFieldTableCell<S, T>((StringConverter<T>) new DefaultStringConverter(), new CustomTextField()) {
            @Override
            public void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
            }
        };
    }
}
