package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.Main;
import com.example.lab2.dao.AuthDataDao;
import com.example.lab2.objects.AuthData;
import com.example.lab2.objects.Student;
import com.example.lab2.utils.AuthUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    private final int maxEmailLength = 40;

    @FXML private Pane pane;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Label authErrorLabel;
    @FXML private Label emailLabel;
    @FXML private Label passwordLabel;

    @FXML
    protected void onAuthButtonClick() {
        authErrorLabel.setVisible(false);

        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (email.isEmpty())
            emailLabel.setTextFill(Color.RED);
        if (password.isEmpty())
            passwordLabel.setTextFill(Color.RED);

        if (!email.isEmpty() && !password.isEmpty()) {
            emailLabel.setTextFill(Color.BLACK);
            passwordLabel.setTextFill(Color.BLACK);

            AuthData authData = AppManager.getAuthDataDao().findByEmail(email);
            if (authData != null) {
                try {
                    if (BCrypt.checkpw(password, authData.getPassword())) {
                        Stage stage = (Stage) pane.getScene().getWindow();
                        FXMLLoader fxmlLoader;
                        switch (authData.getTypeOfUser()) {
                            // superuser
                            case 0 -> fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
                            // teacher
                            case 1 -> {
//                                AppManager.setCurrentTeacher(authData.getTeacher());
                                fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml")); // TODO app for teachers
                            }
                            // student
                            case 2 -> {
                                AppManager.setCurrentStudent(authData.getStudent());
                                fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml")); // TODO app for students
                            }
                            default -> fxmlLoader = new FXMLLoader(Main.class.getResource("authorization-view.fxml"));
                        }
                        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
                        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
                        stage.setScene(scene);
                        return;
                    }
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            authErrorLabel.setVisible(true);
        }
    }

    @FXML
    protected void onAuthRegenerateButtonClick() {
        authErrorLabel.setVisible(true);
        authErrorLabel.setTextFill(Color.RED);
        String email = emailTextField.getText();
        AuthData authData = AppManager.getAuthDataDao().findByEmail(email);

        if (authData == null) {
            authErrorLabel.setText("Ошибка! Проверьте правильность введения электронной почты!");
            return;
        }

        if (authData.getTypeOfUser() == 0) {
            authErrorLabel.setText("Вам запрещено изменять пароль!");
            return;
        }

        AuthUtils.regeneratePassword(authData);
        authErrorLabel.setText("Новые данные для авторизации отправлены на вашу электронную почту.");
        authErrorLabel.setTextFill(Color.BLACK);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!AppManager.getAuthDataDao().isContainsAdmin()) {
            AuthUtils.generateAdminAuthData();
        }

        emailTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                onAuthButtonClick();
            }
        });

        passwordTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                onAuthButtonClick();
            }
        });

        emailTextField.textProperty().addListener((ov, oldValue, newValue) -> {
            if (emailTextField.getText().length() > maxEmailLength) {
                String s = emailTextField.getText().substring(0, maxEmailLength);
                emailTextField.setText(s);
            }
        });
    }
}