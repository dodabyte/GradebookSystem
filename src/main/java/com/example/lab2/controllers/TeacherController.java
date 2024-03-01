package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.Main;
import com.example.lab2.cells.CustomTextFieldTableCell;
import com.example.lab2.converters.LimitationIntegerConverter;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Group;
import com.example.lab2.objects.main.SemesterPerformance;
import com.example.lab2.objects.main.Student;
import com.example.lab2.stat.ExportData;
import com.example.lab2.utils.MarksUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    @FXML protected TableColumn<SemesterPerformance, Integer> semesterPerformanceMarkColumn;
    @FXML private TableView<Discipline> teacherDisciplineTable;
    @FXML private TableView<Group> teacherGroupsTable;

    @FXML private ComboBox<Discipline> teacherDisciplineComboBox = new ComboBox<>();;
    @FXML private ComboBox<Discipline> teacherDisciplineComboBox2 = new ComboBox<>();;
    @FXML private ComboBox<Group> teacherGroupComboBox = new ComboBox<>();

    @FXML private TableView<SemesterPerformance> teacherSemesterPerformanceTable;

    @FXML private Tab teacherSemesterPerformanceTab;
    @FXML private Tab teacherDisciplineTab;
    @FXML private Tab teacherGroupsTab;

    @FXML private TabPane tabPane;

    private Stage changePasswordStage;

    @FXML
    protected void onTeacherSemesterPerfomanceTabChanged() {
        if (teacherSemesterPerformanceTab.isSelected()) {
            onTeacherSemesterPerformanceRefreshButton();
        }
    }

    @FXML
    protected void onTeacherGroupsTabChanged() {
        if (teacherGroupsTab.isSelected()) {
            onTeacherGroupsRefreshButton();
        }
    }

    @FXML
    protected void onTeacherDisciplineTabChanged() {
        if (teacherDisciplineTab.isSelected()) {
            onTeacherDisciplineRefreshButton();
        }
    }

    @FXML
    protected void onTeacherSemesterPerformanceRefreshButton() {
        semesterPerformanceMarkColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new LimitationIntegerConverter(0, 100)));
        teacherDisciplineComboBox2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!teacherDisciplineComboBox2.getSelectionModel().isEmpty()) {
                    teacherGroupComboBox.setDisable(false);
                    teacherGroupComboBox.setItems(FXCollections.observableArrayList(
                            AppManager.getGroupsDao().findGroups(AppManager.getCurrentTeacher(),teacherDisciplineComboBox2.getValue())));
                    teacherSemesterPerformanceTable.setItems(null);
                }
                else {
                    teacherGroupComboBox.setDisable(true);
                }
            }
        });
        teacherGroupComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!teacherGroupComboBox.getSelectionModel().isEmpty()) {

                    teacherSemesterPerformanceTable.setItems(FXCollections.observableArrayList(
                            AppManager.getSemesterPerformanceDao().findSemesterPerformance(teacherDisciplineComboBox2.getValue(),teacherGroupComboBox.getValue())));
                }
            }
        });
    }

    @FXML
    protected void onTeacherDisciplineRefreshButton() {
        teacherDisciplineTable.setItems(FXCollections.observableArrayList(AppManager.getDisciplinesDao().findDisciplines(AppManager.getCurrentTeacher())));
    }

    @FXML
    protected void onTeacherGroupsRefreshButton() {
        teacherGroupsTable.setItems(FXCollections.observableArrayList(AppManager.getGroupsDao().findGroups(AppManager.getCurrentTeacher(),teacherDisciplineComboBox.getValue())));
    }

    @FXML
    protected void onDisciplineExportButton() {
        try {
            ExportData<Discipline> exportData = new ExportData<>("Дисциплины", AppManager.getDisciplinesDao().findDisciplines(AppManager.getCurrentTeacher()));
            exportData.exportList(0, "Дисциплины");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onGroupsExportButton() {
        try {
            List<Discipline> list = AppManager.getDisciplinesDao().findDisciplines(AppManager.getCurrentTeacher());
            ExportData<Group> exportData = new ExportData<>("Группы");
            int idx = 0;
            for (Discipline discipline : list) {
                exportData.setList(AppManager.getGroupsDao().findGroups(AppManager.getCurrentTeacher(), discipline));
                exportData.exportList(idx++, discipline.getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onSemesterPerformanceExportButton() {
        try {
            List<Discipline> listD = AppManager.getDisciplinesDao().findDisciplines(AppManager.getCurrentTeacher());
            ExportData<SemesterPerformance> exportData = new ExportData<>("Журнал");
            int idx = 0;
            for (Discipline discipline : listD) {
                List<Group> listG = AppManager.getGroupsDao().findGroups(AppManager.getCurrentTeacher(), discipline);
                for (Group group : listG) {
                    exportData.setList(AppManager.getSemesterPerformanceDao().findSemesterPerformance(discipline, group));
                    exportData.exportList(idx++, discipline.getName() + " - " + group.getName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void onSemesterPerformanceMarkColumnEditCommit(TableColumn.CellEditEvent<SemesterPerformance, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setMark(value);
        event.getRowValue().setEctsMark(MarksUtils.getEctsMark(value));
        event.getRowValue().setTraditionalMark(MarksUtils.getTraditionalMark(value));
        event.getRowValue().setTraditionalWordMark(MarksUtils.getTraditionalWordMark(value,
                event.getRowValue().getDiscipline().getTypeOfMark().getName()));
        teacherSemesterPerformanceTable.refresh();
        AppManager.getSemesterPerformanceDao().update(event.getRowValue());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllComboBox();
    }
    public void initAllComboBox() {
        initTeacherDisciplineComboBox();
        initTeacherDisciplineComboBox2();
    }

    private void initTeacherDisciplineComboBox() {
        teacherDisciplineComboBox.setItems(FXCollections.observableArrayList(AppManager.getDisciplinesDao().findDisciplines(AppManager.getCurrentTeacher())));
        teacherDisciplineComboBox.getSelectionModel().selectFirst();
    }
    private void initTeacherDisciplineComboBox2() {
        teacherDisciplineComboBox2.setItems(FXCollections.observableArrayList(AppManager.getDisciplinesDao().findDisciplines(AppManager.getCurrentTeacher())));
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
