package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.objects.main.*;
import com.example.lab2.objects.references.TeacherDiscipline;
import com.example.lab2.stat.ExportData;
import com.example.lab2.utils.DateUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class TeacherController {
    @FXML private TableView<Discipline> teacherDisciplineTable;
    @FXML private TableView<Group> teacherGroupsTable;
    @FXML private Tab teacherDisciplineTab;
    @FXML private Tab teacherGroupsTab;
    @FXML
    protected void onTeacherDisciplineTabChanged() {
        if (teacherDisciplineTab.isSelected()) {
            onTeacherDisciplineRefreshButton();
        }
    }

    @FXML
    protected void onTeacherGroupsTabChanged() {
        if (teacherGroupsTab.isSelected()) {
            onTeacherGroupsRefreshButton();
        }
    }
    @FXML
    protected void onTeacherDisciplineRefreshButton() {

        teacherDisciplineTable.setItems(FXCollections.observableArrayList(AppManager.getDisciplinesDao().findAll()));
    }

    @FXML
    protected void onTeacherGroupsRefreshButton() {
        //List<SemesterPerformance> list = AppManager.getSemesterPerformanceDao().findByCustomField("student","id",AppManager.getCurrentStudent().getId());
        //teacherGroupsTable.setItems(FXCollections.observableArrayList(searchSemesterPerformance(list, semestersComboBox.getValue())));
    }
}
