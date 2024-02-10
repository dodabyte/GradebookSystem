package com.example.lab2.callbacks;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CustomCallbackNumberCellData<T> implements Callback<TableColumn.CellDataFeatures<T, T>, ObservableValue<T>> {
    public CustomCallbackNumberCellData() {}

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<T, T> ttCellDataFeatures) {
        return new ReadOnlyObjectWrapper(ttCellDataFeatures.getValue());
    }
}
