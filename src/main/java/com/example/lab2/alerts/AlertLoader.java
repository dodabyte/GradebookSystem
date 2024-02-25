package com.example.lab2.alerts;

import com.example.lab2.AppManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertLoader {
    public static final Alert AVAILABLE_ALERT = new Alert(Alert.AlertType.WARNING,
            AppManager.getLocalizationLoader().getMessage("Dialog.message.available"), ButtonType.OK);
    public static final Alert SUBAVAILABLE_ALERT = new Alert(Alert.AlertType.WARNING,
            AppManager.getLocalizationLoader().getMessage("Dialog.message.subavailable"), ButtonType.OK);
    public static final Alert ADDING_ALERT = new Alert(Alert.AlertType.CONFIRMATION,
            AppManager.getLocalizationLoader().getMessage("Dialog.message.adding"), ButtonType.YES, ButtonType.NO);
    public static final Alert DELETE_ALERT = new Alert(Alert.AlertType.ERROR,
            AppManager.getLocalizationLoader().getMessage("Dialog.message.delete"), ButtonType.OK);

    static {
        AVAILABLE_ALERT.setTitle(AppManager.getLocalizationLoader().getMessage("Dialog.warning.title"));
        AVAILABLE_ALERT.setHeaderText(AppManager.getLocalizationLoader().getMessage("Dialog.warning.header"));
        ((Button)AVAILABLE_ALERT.getDialogPane().lookupButton(ButtonType.OK)).setText(
                AppManager.getLocalizationLoader().getMessage("Dialog.ok.button"));

        SUBAVAILABLE_ALERT.setTitle(AppManager.getLocalizationLoader().getMessage("Dialog.warning.title"));
        SUBAVAILABLE_ALERT.setHeaderText(AppManager.getLocalizationLoader().getMessage("Dialog.warning.header"));
        ((Button)SUBAVAILABLE_ALERT.getDialogPane().lookupButton(ButtonType.OK)).setText(
                AppManager.getLocalizationLoader().getMessage("Dialog.ok.button"));

        ADDING_ALERT.setTitle(AppManager.getLocalizationLoader().getMessage("Dialog.confirm.title"));
        ADDING_ALERT.setHeaderText(AppManager.getLocalizationLoader().getMessage("Dialog.confirm.header"));
        ((Button)ADDING_ALERT.getDialogPane().lookupButton(ButtonType.YES)).setText(
                AppManager.getLocalizationLoader().getMessage("Dialog.yes.button"));
        ((Button)ADDING_ALERT.getDialogPane().lookupButton(ButtonType.NO)).setText(
                AppManager.getLocalizationLoader().getMessage("Dialog.no.button"));

        DELETE_ALERT.setTitle(AppManager.getLocalizationLoader().getMessage("Dialog.error.title"));
        DELETE_ALERT.setHeaderText(AppManager.getLocalizationLoader().getMessage("Dialog.error.header"));
        ((Button)DELETE_ALERT.getDialogPane().lookupButton(ButtonType.OK)).setText(
                AppManager.getLocalizationLoader().getMessage("Dialog.ok.button"));
    }

    public static void createAlert(Alert.AlertType alertType, String message, ButtonType... buttonTypes) {
        Alert alert = new Alert(alertType, message, buttonTypes);
        alert.showAndWait();
    }
}
