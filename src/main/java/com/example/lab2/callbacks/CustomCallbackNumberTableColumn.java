package com.example.lab2.callbacks;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CustomCallbackNumberTableColumn<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {
    @Override
    public TableCell<T, T> call(TableColumn<T, T> ttTableColumn) {
        return new TableCell<T, T>() {
            @Override protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (this.getTableRow() != null && item != null) {
                    setText(String.valueOf(this.getTableRow().getIndex() + 1));
                } else {
                    setText("");
                }
            }
        };
    }
}
