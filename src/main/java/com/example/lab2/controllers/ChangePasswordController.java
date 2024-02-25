package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.objects.main.AuthData;
import com.example.lab2.utils.AuthUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class ChangePasswordController {

    @FXML private Label changePasswordErrorLabel;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField repeatPasswordField;
    @FXML private Label newPasswordLabel;
    @FXML private Label oldPasswordLabel;
    @FXML private Label repeatPasswordLabel;

    @FXML
    protected void onChangePasswordSaveButton(ActionEvent event) {
        changePasswordErrorLabel.setVisible(false);
        newPasswordLabel.setTextFill(Color.BLACK);
        oldPasswordLabel.setTextFill(Color.BLACK);
        repeatPasswordLabel.setTextFill(Color.BLACK);

        Integer typeOfUser = AppManager.getTypeOfUser();

        String newPassword = newPasswordField.getText();
        String oldPassword = oldPasswordField.getText();
        String repeatPassword = repeatPasswordField.getText();

        if (!newPassword.isEmpty() && !oldPassword.isEmpty() && !repeatPassword.isEmpty()) {
            AuthData authData = switch (typeOfUser) {
                //case 1 -> AppManager.getAuthDataDao().findByTeacher(AppManager.getCurrentTeacher());
                case 2 -> AppManager.getCurrentStudent().getAuthData();
                default -> null;
            };
            if (authData != null) {
                if (BCrypt.checkpw(oldPassword, authData.getPassword())) {
                    if (newPassword.equals(repeatPassword)) {
                        AuthUtils.setPassword(authData, newPassword);
                        onChangePasswordCancelButton(event);
                    }
                    else {
                        newPasswordLabel.setTextFill(Color.RED);
                        repeatPasswordLabel.setTextFill(Color.RED);
                        changePasswordErrorLabel.setText("Ошибка! Новый пароль не совпадает!");
                    }
                }
                else {
                    oldPasswordLabel.setTextFill(Color.RED);
                    changePasswordErrorLabel.setText("Ошибка! Старый пароль введен неверно!");
                }
            } else {
                changePasswordErrorLabel.setText("Ошибка! Вам запрещено изменять пароль!");
            }
        }
        else {
            changePasswordErrorLabel.setText("Ошибка! Заполните все пустые поля!");
        }
        changePasswordErrorLabel.setVisible(true);
    }

    @FXML
    protected void onChangePasswordCancelButton(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
}