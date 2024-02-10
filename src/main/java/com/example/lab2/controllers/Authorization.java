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
            AuthData authData = AppManager.getAuthDataDao().findByFields(email, password);
            if (authData != null) {
                Stage stage = (Stage) pane.getScene().getWindow();
                FXMLLoader fxmlLoader;
                switch (authData.getTypeOfUser()) {
                    case 0: // superuser
                        fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
                        break;
                    case 1: // teacher
                        fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml")); // TODO app for teachers
                        break;
                    case 2: // student
                        fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml")); // TODO app for students
                        break;
                    default:
                        fxmlLoader = new FXMLLoader(Main.class.getResource("authorization.fxml"));
                }
                Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
                scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
                stage.setScene(scene);
            }
            else {
                authErrorLabel.setVisible(true);
            }
        }
    }

}