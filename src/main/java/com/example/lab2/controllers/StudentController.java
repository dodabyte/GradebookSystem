package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.Main;
import com.example.lab2.objects.main.*;
import com.example.lab2.stat.ExportData;
import com.example.lab2.utils.DateUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static com.example.lab2.utils.DateUtils.DATE_PATTERN;

public class StudentController implements Initializable {
    @FXML private Label currentStudentGroupLabel;
    @FXML private Label specializationLabel;
    @FXML private Label nameLabel;
    @FXML private Label courseLabel;
    @FXML private Label semesterLabel;
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

    @FXML private TabPane tabPane;

    @FXML private Tab semesterPerformanceTab;
    @FXML private Tab studentsTab;
    private Stage changePasswordStage;
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
        List<SemesterPerformance> list = AppManager.getSemesterPerformanceDao().findByCustomField("student","id",AppManager.getCurrentStudent().getId());
        semesterPerformanceTable.setItems(FXCollections.observableArrayList(searchSemesterPerformance(list, semestersComboBox.getValue())));
    }

    @FXML
    protected void onStudentsExportButton() {
        try {
            ExportData<Student> exportData = new ExportData<>("Одногруппники", AppManager.getStudentDao().findByCustomField("group", "id", AppManager.getCurrentStudent().getGroup().getId()));
            exportData.exportList(0, AppManager.getCurrentStudent().getGroup().getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onSemesterPerformanceExportButton() {
        try {
            List<SemesterPerformance> list = AppManager.getSemesterPerformanceDao().findByCustomField("student", "id", AppManager.getCurrentStudent().getId());
            ExportData<SemesterPerformance> exportData = new ExportData<>("Успеваемость");
            for (int sem = 1; sem < 8; sem++) {
                exportData.setList(searchSemesterPerformance(list, sem));
                exportData.exportList(sem - 1, "Семестр " + sem);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllLabels();
        initComboBox();
    }

    private void initAllLabels() {
        nameLabel.setText(studentNameMerge());
        courseLabel.setText(String.valueOf(groupCurrentStudent.getCourse()));
        groupLabel.setText(groupCurrentStudent.getName());
        semesterLabel.setText(String.valueOf(DateUtils.getCurrentSemester(groupCurrentStudent.getDateFormation(),groupCurrentStudent.getCourse())));
        studyDurationLabel.setText(String.valueOf(groupCurrentStudent.getSpecialization().getStudyDuration()));
        formEducationLabel.setText(AppManager.getCurrentStudent().getFormOfEducation().getName());
        basisEducationLabel.setText(AppManager.getCurrentStudent().getBasisOfEducation().getName());
        specializationLabel.setText(groupCurrentStudent.getSpecialization().getName());
        dataAdmissionLabel.setText(DateUtils.getStringFromDate(AppManager.getCurrentStudent().getDateAdmission(),DATE_PATTERN));
        mailLabel.setText(AppManager.getCurrentStudent().getAuthData().getEmail());
        cityLabel.setText(AppManager.getCurrentStudent().getAddress().getCity());
        streetLabel.setText(AppManager.getCurrentStudent().getAddress().getStreet());
        homeNumberLabel.setText(AppManager.getCurrentStudent().getAddress().getHouseNumber());
        apartmentNumberLabel.setText(String.valueOf(AppManager.getCurrentStudent().getAddress().getApartmentNumber()));
        currentStudentGroupLabel.setText(groupCurrentStudent.getName());
    }

    private void initComboBox() {
        int studyduration = groupCurrentStudent.getSpecialization().getStudyDuration();
        for (int i = 1;i<=studyduration*2;i++){
            semestersComboBox.getItems().add(i);
        }
        semestersComboBox.setValue(1);
    }

    protected List<SemesterPerformance> searchSemesterPerformance(List<SemesterPerformance> list, int semester) {
        List<SemesterPerformance> filteredList = new ArrayList<>();
        for (SemesterPerformance performance : list) {
            if (performance.getSemester() == semester) {
                filteredList.add(performance);
            }
        }
        return filteredList;
    }

    protected String studentNameMerge(){
        return AppManager.getCurrentStudent().getFirstName() + " " + AppManager.getCurrentStudent().getLastName() + " " + AppManager.getCurrentStudent().getPatronymic();
    }

    @FXML
    private void onExitClick() throws IOException {
        AppManager.setCurrentStudent(null);
        Stage stage = (Stage) tabPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorization-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
    }

    @FXML
    private void onChangePasswordClick() throws IOException {
        changePasswordStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("change-password-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        changePasswordStage.setScene(scene);
        changePasswordStage.setTitle("Смена пароля");
        changePasswordStage.setResizable(false);
        changePasswordStage.initModality(Modality.WINDOW_MODAL);
        changePasswordStage.initOwner(tabPane.getScene().getWindow());
        changePasswordStage.show();
    }
}

