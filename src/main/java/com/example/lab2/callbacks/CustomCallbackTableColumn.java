package com.example.lab2.callbacks;

import com.example.lab2.cells.CustomTableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class CustomCallbackTableColumn<S, T> implements Callback<TableColumn<S, T>, CustomTableCell<S, T>> {

    public CustomCallbackTableColumn() {}

    @Override
    public CustomTableCell<S, T> call(TableColumn<S, T> ttTableColumn) {
        return new CustomTableCell<S, T>((StringConverter<T>) new DefaultStringConverter()) {
            @Override
            public void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
            }
        };
    }
}
