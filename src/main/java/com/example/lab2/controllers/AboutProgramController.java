package com.example.lab2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AboutProgramController {
    @FXML
    protected void onAboutProgramCloseButton(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
}
