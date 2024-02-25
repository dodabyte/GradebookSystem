package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AboutProgramController implements Initializable {
    @FXML private Label descriptionLabel;

    @FXML
    protected void onAboutProgramCloseButton(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initDescriptionLabel();
    }

    private void initDescriptionLabel() {
        try {
            descriptionLabel.setText(Files.readString(Paths.get(getClass().getResource(
                    "/description_" + AppManager.getLocalizationLoader().getLanguage() + ".txt").toURI()), StandardCharsets.UTF_8));
        } catch (IOException | NullPointerException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
