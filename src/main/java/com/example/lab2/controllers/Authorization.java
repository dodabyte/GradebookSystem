package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.Main;
import com.example.lab2.objects.AuthData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

public class Authorization {

    @FXML private Pane pane;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Label authErrorLabel;

    @FXML
    protected void onAuthButtonClick() throws IOException {
        authErrorLabel.setVisible(false);
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        if (!email.isEmpty() && !password.isEmpty()) {
            AuthData authData = AppManager.getAuthDataDao().findByEmail(email);
            if (authData != null) {
                try {
                    if (BCrypt.checkpw(password, authData.getPassword())) {
                        Stage stage = (Stage) pane.getScene().getWindow();
                        FXMLLoader fxmlLoader = switch (authData.getTypeOfUser()) {
                            // superuser
                            case 0 -> new FXMLLoader(Main.class.getResource("main-view.fxml"));
                            // teacher
                            case 1 -> new FXMLLoader(Main.class.getResource("main-view.fxml")); // TODO app for teachers
                            // student
                            case 2 -> new FXMLLoader(Main.class.getResource("main-view.fxml")); // TODO app for students
                            default -> new FXMLLoader(Main.class.getResource("authorization.fxml"));
                        };
                        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
                        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
                        stage.setScene(scene);
                        return;
                    }
                }
                catch (Exception ignore) {}
            }
            authErrorLabel.setVisible(true);
        }
    }

}