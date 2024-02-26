package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.objects.main.*;
import com.example.lab2.utils.DateUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML private Label nameLabel;
    @FXML private Label courseLabel;
    @FXML private Label semestrLabel;
    @FXML private Label groupLabel;
    @FXML private Label studyDurationLabel;
    @FXML private Label formEducationLabel;
    @FXML private Label basisEducationLabel;
    @FXML private Label dataAdmissionLabel;
    @FXML private Label mailLabel;
    @FXML private Label cityLabel;
    @FXML private Label streetLabel;
    @FXML private Label homeNumberLabel;
    @FXML private Label apartmentNumberLabel;

    @FXML private ComboBox<Integer> semestersComboBox = new ComboBox<>();;

    @FXML private TableView<SemesterPerformance> semesterPerformanceTable;
    @FXML private TableView<Student> groupTable;

    @FXML private Tab semesterPerformanceTab;
    @FXML private Tab studentsTab;

    private Group groupCurrentStudent = AppManager.getCurrentStudent().getGroup();

    @FXML
    protected void onStudentsTabChanged() {
        if (studentsTab.isSelected()) {
            onStudentsRefreshButton();
        }
    }

    @FXML
    protected void onSemesterPerformanceTabChanged() {
        if (semesterPerformanceTab.isSelected()) {
            onSemesterPerformanceRefreshButton();
        }
    }

    @FXML
    protected void onStudentsRefreshButton() {
        groupTable.setItems(FXCollections.observableArrayList(AppManager.getStudentDao().findByCustomField("group","id",AppManager.getCurrentStudent().getGroup().getId())));
    }

    @FXML
    protected void onSemesterPerformanceRefreshButton() {
        semesterPerformanceTable.setItems(FXCollections.observableArrayList(searchSemesterPerformance()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllLabels();
    }

    private void initAllLabels() {
        nameLabel.setText(studentNameMerge());
        courseLabel.setText(String.valueOf(groupCurrentStudent.getCourse()));
        groupLabel.setText(groupCurrentStudent.getName());
        semestrLabel.setText(String.valueOf(DateUtils.getCurrentSemester(groupCurrentStudent.getDateFormation(),groupCurrentStudent.getCourse())));
        studyDurationLabel.setText(String.valueOf(groupCurrentStudent.getSpecialization().getStudyDuration()));
        formEducationLabel.setText(AppManager.getCurrentStudent().getFormOfEducation().getName());
        basisEducationLabel.setText(AppManager.getCurrentStudent().getBasisOfEducation().getName());
        dataAdmissionLabel.setText(String.valueOf(AppManager.getCurrentStudent().getDateAdmission()));
        mailLabel.setText(AppManager.getCurrentStudent().getAuthData().getEmail());
        cityLabel.setText(AppManager.getCurrentStudent().getAddress().getCity());
        streetLabel.setText(AppManager.getCurrentStudent().getAddress().getStreet());
        homeNumberLabel.setText(AppManager.getCurrentStudent().getAddress().getHouseNumber());
        apartmentNumberLabel.setText(String.valueOf(AppManager.getCurrentStudent().getAddress().getApartmentNumber()));
        semestersComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
        semestersComboBox.setValue(1);
    }

    protected List<SemesterPerformance> searchSemesterPerformance(){
        List<SemesterPerformance> list = new ArrayList<>();
        semesterPerformanceTable.setItems(FXCollections.observableArrayList(AppManager.getSemesterPerformanceDao().findByCustomField("student","id",AppManager.getCurrentStudent().getId())));
        list = (AppManager.getSemesterPerformanceDao().findByCustomField("student","id",AppManager.getCurrentStudent().getId()));

        List<SemesterPerformance> filteredList = new ArrayList<>();

        for (SemesterPerformance performance : list) {
            if (performance.getSemester() == semestersComboBox.getValue()) {
                filteredList.add(performance);
            }
        }
        return filteredList;
    }

    protected String studentNameMerge(){
        return AppManager.getCurrentStudent().getFirstName() + " " + AppManager.getCurrentStudent().getLastName() + " " + AppManager.getCurrentStudent().getPatronymic();
    }
}

