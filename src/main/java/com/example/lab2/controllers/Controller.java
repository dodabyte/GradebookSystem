package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.Main;
import com.example.lab2.cells.CustomTableCell;
import com.example.lab2.converters.AddressConverter;
import com.example.lab2.converters.CustomDateStringConverter;
import com.example.lab2.converters.LimitationIntegerConverter;
import com.example.lab2.objects.*;
import com.example.lab2.objects.old.ContractData;
import com.example.lab2.objects.old.CurrentPayment;
import com.example.lab2.objects.old.Status;
import com.example.lab2.utils.DateUtils;
import com.example.lab2.utils.MarksUtils;
import com.example.lab2.utils.AuthUtils;
import com.example.lab2.utils.TypesUtils;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Controller implements Initializable {
    @FXML private Button addressesAddButton;
    @FXML private Button addressesDeleteButton;
    @FXML private Button basisOfEducationAddButton;
    @FXML private Button basisOfEducationDeleteButton;
    @FXML private Button formsOfEducationAddButton;
    @FXML private Button formsOfEducationDeleteButton;
    @FXML private Button statusesAddButton;
    @FXML private Button statusesDeleteButton;
    @FXML private Button typesOfMarksAddButton;
    @FXML private Button typesOfMarksDeleteButton;
    @FXML private Button disciplinesAddButton;
    @FXML private Button disciplinesDeleteButton;
    @FXML private Button specializationsAddButton;
    @FXML private Button specializationsDeleteButton;
    @FXML private Button currentPaymentsAddButton;
    @FXML private Button currentPaymentsDeleteButton;
    @FXML private Button groupsAddButton;
    @FXML private Button groupsDeleteButton;
    @FXML private Button studentsAddButton;
    @FXML private Button studentsDeleteButton;
    @FXML private Button parentsAddButton;
    @FXML private Button parentsDeleteButton;
    @FXML private Button contractDataAddButton;
    @FXML private Button contractDataDeleteButton;
    @FXML private Button semesterPerformanceAddButton;
    @FXML private Button semesterPerformanceDeleteButton;

    @FXML private TextField addressApartmentNumberField;
    @FXML private TextField addressCityField;
    @FXML private TextField addressHouseNumberField;
    @FXML private TextField addressStreetField;
    @FXML private TextField basisOfEducationNameField;
    @FXML private TextField formsOfEducationNameField;
    @FXML private TextField statusesNameField;
    @FXML private TextField typesOfMarksNameField;
    @FXML private TextField disciplinesNameField;
    @FXML private TextField disciplinesTypeOfMarkNameField;
    @FXML private TextField specializationsNumberField;
    @FXML private TextField specializationsNameField;
    @FXML private TextField specializationsStudyDurationField;
    @FXML private TextField currentPaymentsAmountField;
    @FXML private TextField currentPaymentsDateField;
    @FXML private TextField currentPaymentsDocNumberField;
    @FXML private TextField groupsNameField;
    @FXML private TextField groupSpecializationsNumberField;
    @FXML private TextField groupSpecializationsNameField;
    @FXML private TextField groupSpecializationsStudyDurationField;
    @FXML private TextField groupDateFormationField;
    @FXML private TextField studentLastNameField;
    @FXML private TextField studentFirstNameField;
    @FXML private TextField studentPatronymicField;
    @FXML private TextField studentAddressCityField;
    @FXML private TextField studentAddressStreetField;
    @FXML private TextField studentAddressHouseNumberField;
    @FXML private TextField studentAddressApartmentNumberField;
    @FXML private TextField studentDateOfBirthField;
    @FXML private TextField studentGroupNameField;
    @FXML private TextField studentGroupDateFormationField;
    @FXML private TextField studentSpecializationNumberField;
    @FXML private TextField studentSpecializationNameField;
    @FXML private TextField studentSpecializationStudyDurationField;
    @FXML private TextField studentFormOfEducationNameField;
    @FXML private TextField studentBasisOfEducationNameField;
    @FXML private TextField studentDateAdmissionField;
    @FXML private TextField parentLastNameField;
    @FXML private TextField parentFirstNameField;
    @FXML private TextField parentPatronymicField;
    @FXML private TextField parentAddressCityField;
    @FXML private TextField parentAddressStreetField;
    @FXML private TextField parentAddressHouseNumberField;
    @FXML private TextField parentAddressApartmentNumberField;
    @FXML private TextField contractDataDateField;
    @FXML private TextField contractDataPaymentAmountField;
    @FXML private TextField contractDataCurrentPaymentAmountField;
    @FXML private TextField contractDataCurrentPaymentDateField;
    @FXML private TextField contractDataCurrentPaymentDocNumberField;
    @FXML private TextField contractDataStatusNameField;
    @FXML private TextField semesterPerformanceCourseField;
    @FXML private TextField semesterPerformanceSemesterField;
    @FXML private TextField semesterPerformanceDisciplineNameField;
    @FXML private TextField semesterPerformanceDisciplineTypeOfMarkNameField;
    @FXML private TextField semesterPerformanceMarkField;
    @FXML private TextField basisOfEducationSearchField;
    @FXML private TextField formsOfEducationSearchField;
    @FXML private TextField statusesSearchField;
    @FXML private TextField typesOfMarksSearchField;
    @FXML private TextField disciplinesSearchField;
    @FXML private TextField specializationsSearchField;
    @FXML private TextField groupsSearchField;
    @FXML private TextField addressesSearchField;
    @FXML private TextField currentPaymentsSearchField;
    @FXML private TextField parentsSearchField;
    @FXML private TextField contractDataSearchField;
    @FXML private TextField semesterPerformanceSearchField;
    @FXML private TextField studentsSearchField;

    @FXML private RadioButton groupSpecializationExitsRadioButton;
    @FXML private RadioButton groupSpecializationNewRadioButton;
    @FXML private RadioButton studentAddressExitsRadioButton;
    @FXML private RadioButton studentAddressNewRadioButton;
    @FXML private RadioButton studentGroupExitsRadioButton;
    @FXML private RadioButton studentGroupNewRadioButton;
    @FXML private RadioButton studentSpecializationExitsRadioButton;
    @FXML private RadioButton studentSpecializationNewRadioButton;
    @FXML private RadioButton studentFormOfEducationExitsRadioButton;
    @FXML private RadioButton studentFormOfEducationNewRadioButton;
    @FXML private RadioButton studentBasisOfEducationExitsRadioButton;
    @FXML private RadioButton studentBasisOfEducationNewRadioButton;
    @FXML private RadioButton parentAddressExitsRadioButton;
    @FXML private RadioButton parentAddressNewRadioButton;
    @FXML private RadioButton contractDataCurrentPaymentExitsRadioButton;
    @FXML private RadioButton contractDataCurrentPaymentNewRadioButton;
    @FXML private RadioButton contractDataStatusExitsRadioButton;
    @FXML private RadioButton contractDataStatusNewRadioButton;
    @FXML private RadioButton semesterPerformanceDisciplineExitsRadioButton;
    @FXML private RadioButton semesterPerformanceDisciplineNewRadioButton;
    @FXML private RadioButton semesterPerformanceDisciplineTypeOfMarkExitsRadioButton;
    @FXML private RadioButton semesterPerformanceDisciplineTypeOfMarkNewRadioButton;
    @FXML private RadioButton disciplineTypeOfMarkExitsRadioButton;
    @FXML private RadioButton disciplineTypeOfMarkNewRadioButton;

    @FXML private ComboBox<Specialization> groupSpecializationComboBox;
    @FXML private ComboBox<Address> studentAddressComboBox;
    @FXML private ComboBox<Group> studentGroupComboBox;
    @FXML private ComboBox<Specialization> studentSpecializationComboBox;
    @FXML private ComboBox<FormOfEducation> studentFormOfEducationComboBox;
    @FXML private ComboBox<BasisOfEducation> studentBasisOfEducationComboBox;
    @FXML private ComboBox<Address> parentAddressComboBox;
    @FXML private ComboBox<Student> parentStudentComboBox;
    @FXML private ComboBox<Student> contractDataStudentComboBox;
    @FXML private ComboBox<CurrentPayment> contractDataCurrentPaymentComboBox;
    @FXML private ComboBox<Status> contractDataStatusComboBox;
    @FXML private ComboBox<Student> semesterPerformanceStudentComboBox;
    @FXML private ComboBox<Discipline> semesterPerformanceDisciplineComboBox;
    @FXML private ComboBox<TypeOfMark> semesterPerformanceDisciplineTypeOfMarkComboBox;
    @FXML private ComboBox<TypeOfMark> disciplineTypeOfMarkComboBox;

    @FXML private TableView<Address> addressesTable;
    @FXML private TableView<BasisOfEducation> basisOfEducationTable;
    @FXML private TableView<Discipline> disciplinesTable;
    @FXML private TableView<FormOfEducation> formsOfEducationTable;
    @FXML private TableView<Status> statusesTable;
    @FXML private TableView<TypeOfMark> typesOfMarksTable;
    @FXML private TableView<Specialization> specializationsTable;
    @FXML private TableView<CurrentPayment> currentPaymentsTable;
    @FXML private TableView<Group> groupsTable;
    @FXML private TableView<Student> studentsTable;
    @FXML private TableView<Parent> parentsTable;
    @FXML private TableView<ContractData> contractDataTable;
    @FXML private TableView<SemesterPerformance> semesterPerformanceTable;

    @FXML protected TableColumn<Address, Integer> apartmentNumberColumn;
    @FXML protected TableColumn<CurrentPayment, Float> amountColumn;
    @FXML protected TableColumn<CurrentPayment, Date> paymentDateColumn;
    @FXML protected TableColumn<CurrentPayment, Integer> docNumberColumn;
    @FXML protected TableColumn<Group, Integer> courseColumn;
    @FXML protected TableColumn<Group, Integer> semesterColumn;
    @FXML protected TableColumn<Group, Specialization> specializationColumn;
    @FXML protected TableColumn<Group, Date> dateFormationColumn;
    @FXML protected TableColumn<Group, Date> dateGraduationColumn;
    @FXML protected TableColumn<Student, Address> studentAddressColumn;
    @FXML protected TableColumn<Student, Date> dateOfBirthColumn;
    @FXML protected TableColumn<Student, Group> groupColumn;
    @FXML protected TableColumn<Student, FormOfEducation> formOfEducationColumn;
    @FXML protected TableColumn<Student, BasisOfEducation> basisOfEducationColumn;
    @FXML protected TableColumn<Student, Date> dateAdmissionColumn;
    @FXML protected TableColumn<Parent, Student> parentStudentColumn;
    @FXML protected TableColumn<Parent, Address> parentAddressColumn;
    @FXML protected TableColumn<ContractData, Student> contractDataStudentColumn;
    @FXML protected TableColumn<ContractData, Date> contractDataDateColumn;
    @FXML protected TableColumn<ContractData, Float> contractDataPaymentAmountColumn;
    @FXML protected TableColumn<ContractData, CurrentPayment> contractDataCurrentPaymentColumn;
    @FXML protected TableColumn<ContractData, Status> contractDataStatusColumn;
    @FXML protected TableColumn<SemesterPerformance, Student> semesterPerformanceStudentColumn;
    @FXML protected TableColumn<SemesterPerformance, Integer> semesterPerformanceCourseColumn;
    @FXML protected TableColumn<SemesterPerformance, Integer> semesterPerformanceSemesterColumn;
    @FXML protected TableColumn<SemesterPerformance, Discipline> semesterPerformanceDisciplineColumn;
    @FXML protected TableColumn<SemesterPerformance, Integer> semesterPerformanceMarkColumn;
    @FXML protected TableColumn<Discipline, TypeOfMark> disciplineTypeOfMarkColumn;
    @FXML protected TableColumn<Specialization, Integer> studyDurationColumn;

    @FXML private RadioMenuItem advancedMode;

    @FXML private TabPane tabPane;

    @FXML private Tab studentsTab;
    @FXML private Tab addressesTab;
    @FXML private Tab basisOfEducationTab;
    @FXML private Tab disciplinesTab;
    @FXML private Tab formsOfEducationTab;
    @FXML private Tab statusesTab;
    @FXML private Tab typesOfMarksTab;
    @FXML private Tab specializationsTab;
    @FXML private Tab currentPaymentsTab;
    @FXML private Tab groupsTab;
    @FXML private Tab parentsTab;
    @FXML private Tab contractDataTab;
    @FXML private Tab semesterPerformanceTab;

    private Stage aboutProgramStage;

    private ToggleGroup groupSpecializationGroup = new ToggleGroup();
    private ToggleGroup studentAddressGroup = new ToggleGroup();
    private ToggleGroup studentGroupGroup = new ToggleGroup();
    private ToggleGroup studentSpecializationGroup = new ToggleGroup();
    private ToggleGroup studentFormOfEducationGroup = new ToggleGroup();
    private ToggleGroup studentBasisOfEducationGroup = new ToggleGroup();
    private ToggleGroup parentAddressGroup = new ToggleGroup();
    private ToggleGroup contractDataCurrentPaymentGroup = new ToggleGroup();
    private ToggleGroup contractDataStatusGroup = new ToggleGroup();
    private ToggleGroup semesterPerformanceDisciplineGroup = new ToggleGroup();
    private ToggleGroup semesterPerformanceDisciplineTypeOfMarkGroup = new ToggleGroup();
    private ToggleGroup disciplineTypeOfMarkGroup = new ToggleGroup();

    private final String readingAlertMessage = "Объект уже имеется в базе данных.";
    private final String addingAlertMessage = "Объект отсутствует в базе данных. Добавить его?";
    private final String deleteAlertMessage = "Удалить данный объект невозможно, так как он используется в других таблицах.";
    Alert readdingAlert = new Alert(Alert.AlertType.WARNING, readingAlertMessage, ButtonType.OK);
    Alert addingAlert = new Alert(Alert.AlertType.CONFIRMATION, addingAlertMessage, ButtonType.YES, ButtonType.NO);
    Alert deleteAlert = new Alert(Alert.AlertType.ERROR, deleteAlertMessage, ButtonType.OK);

    private final String datePattern = "dd.MM.yyyy";
    private final String dateTimePattern = "dd.MM.yyyy HH:mm:ss";

    @FXML
    protected void onAddressesRefreshButton() {
        addressesTable.setItems(FXCollections.observableArrayList(AppManager.getAddressesDao().findAll()));
        addressesSearchField.setText("");
    }

    @FXML
    protected void onAddressesAddButton() {
        Address address = new Address();
        address.setCity(addressCityField.getText());
        address.setStreet(addressStreetField.getText());
        address.setHouseNumber(addressHouseNumberField.getText());
        address.setApartmentNumber(Integer.parseInt(addressApartmentNumberField.getText()));
        if (AppManager.getAddressesDao().contains(address)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getAddressesDao().insert(address);
            addressCityField.setText("");
            addressStreetField.setText("");
            addressHouseNumberField.setText("");
            addressApartmentNumberField.setText("");
            onAddressesRefreshButton();
        }
    }

    @FXML
    protected void onAddressesDeleteButton() {
        try {
            AppManager.getAddressesDao().delete(addressesTable.getSelectionModel().getSelectedItem());
            onAddressesRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onAddressesSearch() {
        if (!addressesSearchField.getText().isEmpty()) {
            addressesTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getAddressesDao().findAll()).filtered(
                            createAddressesPredicate(addressesSearchField.getText()))));
        }
    }

    @FXML
    protected void onAddressesSearchClear() {
        addressesSearchField.setText("");
        onAddressesRefreshButton();
    }

    private Predicate<Address> createAddressesPredicate(String searchText){
        return address -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return address.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onBasisOfEducationRefreshButton() {
        basisOfEducationTable.setItems(FXCollections.observableArrayList(AppManager.getBasisOfEducationDao().findAll()));
        basisOfEducationSearchField.setText("");
    }

    @FXML
    protected void onBasisOfEducationAddButton() {
        BasisOfEducation basisOfEducation = new BasisOfEducation();
        basisOfEducation.setName(basisOfEducationNameField.getText());
        if (AppManager.getBasisOfEducationDao().contains(basisOfEducation)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getBasisOfEducationDao().insert(basisOfEducation);
            basisOfEducationNameField.setText("");
            onBasisOfEducationRefreshButton();
        }
    }

    @FXML
    protected void onBasisOfEducationDeleteButton() {
        try {
            AppManager.getBasisOfEducationDao().delete(basisOfEducationTable.getSelectionModel().getSelectedItem());
            onBasisOfEducationRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onBasisOfEducationSearch() {
        if (!basisOfEducationSearchField.getText().isEmpty()) {
            basisOfEducationTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getBasisOfEducationDao().findAll()).filtered(
                            createBasisOfEducationPredicate(basisOfEducationSearchField.getText()))));
        }
    }

    @FXML
    protected void onBasisOfEducationSearchClear() {
        basisOfEducationSearchField.setText("");
        onBasisOfEducationRefreshButton();
    }

    private Predicate<BasisOfEducation> createBasisOfEducationPredicate(String searchText){
        return basisOfEducation -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return basisOfEducation.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onFormsOfEducationRefreshButton() {
        formsOfEducationTable.setItems(FXCollections.observableArrayList(AppManager.getFormsOfEducationDao().findAll()));
        formsOfEducationSearchField.setText("");
    }

    @FXML
    protected void onFormsOfEducationAddButton() {
        FormOfEducation formOfEducation = new FormOfEducation();
        formOfEducation.setName(formsOfEducationNameField.getText());
        if (AppManager.getFormsOfEducationDao().contains(formOfEducation)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getFormsOfEducationDao().insert(formOfEducation);
            formsOfEducationNameField.setText("");
            onFormsOfEducationRefreshButton();
        }
    }

    @FXML
    protected void onFormsOfEducationDeleteButton() {
        try {
            AppManager.getFormsOfEducationDao().delete(formsOfEducationTable.getSelectionModel().getSelectedItem());
            onFormsOfEducationRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onFormsOfEducationSearch() {
        if (!formsOfEducationSearchField.getText().isEmpty()) {
            formsOfEducationTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getFormsOfEducationDao().findAll()).filtered(
                            createFormsOfEducationPredicate(formsOfEducationSearchField.getText()))));
        }
    }

    @FXML
    protected void onFormsOfEducationSearchClear() {
        formsOfEducationSearchField.setText("");
        onFormsOfEducationRefreshButton();
    }

    private Predicate<FormOfEducation> createFormsOfEducationPredicate(String searchText){
        return formOfEducation -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return formOfEducation.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onStatusesRefreshButton() {
        statusesTable.setItems(FXCollections.observableArrayList(AppManager.getStatusesDao().findAll()));
        statusesSearchField.setText("");
    }

    @FXML
    protected void onStatusesAddButton() {
        Status status = new Status();
        status.setName(statusesNameField.getText());
        if (AppManager.getStatusesDao().contains(status)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getStatusesDao().insert(status);
            statusesNameField.setText("");
            onStatusesRefreshButton();
        }
    }

    @FXML
    protected void onStatusesDeleteButton() {
        try {
            AppManager.getStatusesDao().delete(statusesTable.getSelectionModel().getSelectedItem());
            onStatusesRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onStatusesSearch() {
        if (!statusesSearchField.getText().isEmpty()) {
            statusesTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getStatusesDao().findAll()).filtered(
                            createStatusesPredicate(statusesSearchField.getText()))));
        }
    }

    @FXML
    protected void onStatusesSearchClear() {
        statusesSearchField.setText("");
        onStatusesRefreshButton();
    }

    private Predicate<Status> createStatusesPredicate(String searchText){
        return status -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return status.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onTypesOfMarksRefreshButton() {
        typesOfMarksTable.setItems(FXCollections.observableArrayList(AppManager.getTypesOfMarksDao().findAll()));
        typesOfMarksSearchField.setText("");
    }

    @FXML
    protected void onTypesOfMarksAddButton() {
        TypeOfMark typeOfMark = new TypeOfMark();
        typeOfMark.setName(typesOfMarksNameField.getText());
        if (AppManager.getTypesOfMarksDao().contains(typeOfMark)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getTypesOfMarksDao().insert(typeOfMark);
            typesOfMarksNameField.setText("");
            onTypesOfMarksRefreshButton();
        }
    }

    @FXML
    protected void onTypesOfMarksDeleteButton() {
        try {
            AppManager.getTypesOfMarksDao().delete(typesOfMarksTable.getSelectionModel().getSelectedItem());
            onTypesOfMarksRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onTypesOfMarksSearch() {
        if (!typesOfMarksSearchField.getText().isEmpty()) {
            typesOfMarksTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getTypesOfMarksDao().findAll()).filtered(
                            createTypesOfMarksPredicate(typesOfMarksSearchField.getText()))));
        }
    }

    @FXML
    protected void onTypesOfMarksSearchClear() {
        typesOfMarksSearchField.setText("");
        onTypesOfMarksRefreshButton();
    }

    private Predicate<TypeOfMark> createTypesOfMarksPredicate(String searchText){
        return typeOfMark -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return typeOfMark.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onDisciplinesRefreshButton() {
        disciplinesTable.setItems(FXCollections.observableArrayList(AppManager.getDisciplinesDao().findAll()));
        disciplinesSearchField.setText("");
    }

    @FXML
    protected void onDisciplinesAddButton() {
        Discipline discipline = new Discipline();
        discipline.setName(disciplinesNameField.getText());
        if (disciplineTypeOfMarkExitsRadioButton.isSelected()) {
            discipline.setTypeOfMark(disciplineTypeOfMarkComboBox.getValue());
        }
        else {
            TypeOfMark typeOfMark = new TypeOfMark();
            typeOfMark.setName(disciplinesTypeOfMarkNameField.getText());
            discipline.setTypeOfMark(typeOfMark);
            AppManager.getTypesOfMarksDao().insert(typeOfMark);
        }
        if (AppManager.getDisciplinesDao().contains(discipline)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getDisciplinesDao().insert(discipline);
            disciplinesNameField.setText("");
            disciplineTypeOfMarkComboBox.setValue(null);
            disciplineTypeOfMarkGroup.selectToggle(null);
            onDisciplinesRefreshButton();
        }
    }

    @FXML
    protected void onDisciplinesDeleteButton() {
        try {
            AppManager.getDisciplinesDao().delete(disciplinesTable.getSelectionModel().getSelectedItem());
            onDisciplinesRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onDisciplinesSearch() {
        if (!disciplinesSearchField.getText().isEmpty()) {
            disciplinesTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getDisciplinesDao().findAll()).filtered(
                            createDisciplinesPredicate(disciplinesSearchField.getText()))));
        }
    }

    @FXML
    protected void onDisciplinesSearchClear() {
        disciplinesSearchField.setText("");
        onDisciplinesRefreshButton();
    }

    private Predicate<Discipline> createDisciplinesPredicate(String searchText){
        return discipline -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return discipline.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onSpecializationsRefreshButton() {
        specializationsTable.setItems(FXCollections.observableArrayList(AppManager.getSpecializationsDao().findAll()));
        specializationsSearchField.setText("");
    }

    @FXML
    protected void onSpecializationsAddButton() {
        Specialization specialization = new Specialization();
        specialization.setNumber(specializationsNumberField.getText());
        specialization.setName(specializationsNameField.getText());
        if (AppManager.getSpecializationsDao().contains(specialization)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getSpecializationsDao().insert(specialization);
            specializationsNumberField.setText("");
            specializationsNameField.setText("");
            specializationsStudyDurationField.setText("");
            onSpecializationsRefreshButton();
        }
    }

    @FXML
    protected void onSpecializationsDeleteButton() {
        try {
            AppManager.getSpecializationsDao().delete(specializationsTable.getSelectionModel().getSelectedItem());
            onSpecializationsRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onSpecializationsSearch() {
        if (!specializationsSearchField.getText().isEmpty()) {
            specializationsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getSpecializationsDao().findAll()).filtered(
                            createSpecializationsPredicate(specializationsSearchField.getText()))));
        }
    }

    @FXML
    protected void onSpecializationsSearchClear() {
        specializationsSearchField.setText("");
        onSpecializationsRefreshButton();
    }

    private Predicate<Specialization> createSpecializationsPredicate(String searchText){
        return specialization -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return specialization.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onCurrentPaymentsRefreshButton() {
        currentPaymentsTable.setItems(FXCollections.observableArrayList(AppManager.getCurrentPaymentsDao().findAll()));
        currentPaymentsSearchField.setText("");
    }

    @FXML
    protected void onCurrentPaymentsAddButton() {
        CurrentPayment currentPayment = new CurrentPayment();
        currentPayment.setAmount(Float.parseFloat(currentPaymentsAmountField.getText()));
        try {
            currentPayment.setPaymentDate((new SimpleDateFormat(dateTimePattern)).parse(currentPaymentsDateField.getText()));
        } catch (ParseException ignored) {}
        if (currentPayment.getPaymentDate() == null) {
            try {
                currentPayment.setPaymentDate((new SimpleDateFormat(datePattern)).parse(currentPaymentsDateField.getText()));
            } catch (ParseException ignored) {}
        }
        currentPayment.setDocNumber(Integer.parseInt(currentPaymentsDocNumberField.getText()));
        if (AppManager.getCurrentPaymentsDao().contains(currentPayment)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getCurrentPaymentsDao().insert(currentPayment);
            currentPaymentsAmountField.setText("");
            currentPaymentsDateField.setText("");
            currentPaymentsDocNumberField.setText("");
            onCurrentPaymentsRefreshButton();
        }
    }

    @FXML
    protected void onCurrentPaymentsDeleteButton() {
        try {
            AppManager.getCurrentPaymentsDao().delete(currentPaymentsTable.getSelectionModel().getSelectedItem());
            onCurrentPaymentsRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onCurrentPaymentsSearch() {
        if (!currentPaymentsSearchField.getText().isEmpty()) {
            currentPaymentsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getCurrentPaymentsDao().findAll()).filtered(
                            createCurrentPaymentsPredicate(currentPaymentsSearchField.getText()))));
        }
    }

    @FXML
    protected void onCurrentPaymentsSearchClear() {
        currentPaymentsSearchField.setText("");
        onCurrentPaymentsRefreshButton();
    }

    private Predicate<CurrentPayment> createCurrentPaymentsPredicate(String searchText){
        return currentPayment -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return currentPayment.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onGroupsRefreshButton() {
        groupsTable.setItems(FXCollections.observableArrayList(AppManager.getGroupsDao().findAll()));
        groupsSearchField.setText("");
    }

    @FXML
    protected void onGroupsAddButton() {
        Group group = new Group();
        group.setName(groupsNameField.getText());

        if (groupSpecializationExitsRadioButton.isSelected()) {
            group.setSpecialization(groupSpecializationComboBox.getValue());
        }
        else {
            Specialization specialization = new Specialization();
            specialization.setNumber(groupSpecializationsNumberField.getText());
            specialization.setName(groupSpecializationsNameField.getText());
            specialization.setStudyDuration(Integer.parseInt(groupSpecializationsStudyDurationField.getText()));
            group.setSpecialization(specialization);
            AppManager.getSpecializationsDao().insert(specialization);
        }
        try {
            group.setDateFormation((new SimpleDateFormat(datePattern)).parse(groupDateFormationField.getText()));
        } catch (ParseException ignored) {}

        Date dateGraduation = new Date();
        dateGraduation.setDate(1);
        dateGraduation.setMonth(6);
        dateGraduation.setYear(group.getDateFormation().getYear() + group.getSpecialization().getStudyDuration());
        group.setDateGraduation(dateGraduation);

        group.setCourse(DateUtils.getCurrentCourse(group.getDateFormation()));
        group.setSemester(DateUtils.getCurrentSemester(group.getDateFormation(), group.getCourse()));

        if (AppManager.getGroupsDao().contains(group)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getGroupsDao().insert(group);
            groupsNameField.setText("");
            groupSpecializationsNumberField.setText("");
            groupSpecializationsNameField.setText("");
            groupSpecializationsStudyDurationField.setText("");
            groupDateFormationField.setText("");
            groupSpecializationComboBox.setValue(null);
            groupSpecializationGroup.selectToggle(null);
            onGroupsRefreshButton();
        }
    }

    @FXML
    protected void onGroupsDeleteButton() {
        try {
            AppManager.getGroupsDao().delete(groupsTable.getSelectionModel().getSelectedItem());
            onGroupsRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onGroupsSearch() {
        if (!groupsSearchField.getText().isEmpty()) {
            groupsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getGroupsDao().findAll()).filtered(
                            createGroupsPredicate(groupsSearchField.getText()))));
        }
    }

    @FXML
    protected void onGroupsSearchClear() {
        groupsSearchField.setText("");
        onGroupsRefreshButton();
    }

    private Predicate<Group> createGroupsPredicate(String searchText){
        return group -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return group.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onStudentsRefreshButton() {
        studentsTable.setItems(FXCollections.observableArrayList(AppManager.getStudentDao().findAll()));
        studentsSearchField.setText("");
    }

    @FXML
    protected void onStudentsAddButton() {
        Student student = new Student();
        student.setLastName(studentLastNameField.getText());
        student.setFirstName(studentFirstNameField.getText());
        student.setPatronymic(studentPatronymicField.getText());

        if (studentAddressExitsRadioButton.isSelected()) {
            student.setAddress(studentAddressComboBox.getValue());
        }
        else {
            Address address = new Address();
            address.setCity(studentAddressCityField.getText());
            address.setStreet(studentAddressStreetField.getText());
            address.setHouseNumber(studentAddressHouseNumberField.getText());
            address.setApartmentNumber(Integer.parseInt(studentAddressApartmentNumberField.getText()));
            student.setAddress(address);
            AppManager.getAddressesDao().insert(address);
        }

        try {
            student.setDateOfBirth((new SimpleDateFormat(datePattern)).parse(studentDateOfBirthField.getText()));
        } catch (ParseException ignored) {}

        if (studentGroupExitsRadioButton.isSelected()) {
            student.setGroup(studentGroupComboBox.getValue());
        }
        else {
            Group group = new Group();
            group.setName(studentGroupNameField.getText());

            if (studentSpecializationExitsRadioButton.isSelected()) {
                group.setSpecialization(studentSpecializationComboBox.getValue());
            }
            else {
                Specialization specialization = new Specialization();
                specialization.setNumber(studentSpecializationNumberField.getText());
                specialization.setName(studentSpecializationNameField.getText());
                specialization.setStudyDuration(Integer.parseInt(studentSpecializationStudyDurationField.getText()));
                group.setSpecialization(specialization);
                AppManager.getSpecializationsDao().insert(specialization);
            }

            try {
                group.setDateFormation((new SimpleDateFormat(datePattern)).parse(studentGroupDateFormationField.getText()));
            } catch (ParseException ignored) {}

            Date dateGraduation = new Date();
            dateGraduation.setDate(1);
            dateGraduation.setMonth(6);
            dateGraduation.setYear(group.getDateFormation().getYear() + group.getSpecialization().getStudyDuration());
            group.setDateGraduation(dateGraduation);

            group.setCourse(DateUtils.getCurrentCourse(group.getDateFormation()));
            group.setSemester(DateUtils.getCurrentSemester(group.getDateFormation(), group.getCourse()));

            student.setGroup(group);
            AppManager.getGroupsDao().insert(group);
        }

        if (studentFormOfEducationExitsRadioButton.isSelected()) {
            student.setFormOfEducation(studentFormOfEducationComboBox.getValue());
        }
        else {
            FormOfEducation formOfEducation = new FormOfEducation();
            formOfEducation.setName(studentFormOfEducationNameField.getText());
            student.setFormOfEducation(formOfEducation);
            AppManager.getFormsOfEducationDao().insert(formOfEducation);
        }

        if (studentBasisOfEducationExitsRadioButton.isSelected()) {
            student.setBasisOfEducation(studentBasisOfEducationComboBox.getValue());
        }
        else {
            BasisOfEducation basisOfEducation = new BasisOfEducation();
            basisOfEducation.setName(studentBasisOfEducationNameField.getText());
            student.setBasisOfEducation(basisOfEducation);
            AppManager.getBasisOfEducationDao().insert(basisOfEducation);
        }

        try {
            student.setDateAdmission((new SimpleDateFormat(datePattern)).parse(studentDateAdmissionField.getText()));
        } catch (ParseException ignored) {}

        if (AppManager.getStudentDao().contains(student)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getStudentDao().insert(student);
            AuthUtils.generateStudentAuthData(student);
            studentLastNameField.setText("");
            studentFirstNameField.setText("");
            studentPatronymicField.setText("");
            studentAddressCityField.setText("");
            studentAddressStreetField.setText("");
            studentAddressHouseNumberField.setText("");
            studentAddressApartmentNumberField.setText("");
            studentPatronymicField.setText("");
            studentDateOfBirthField.setText("");
            studentSpecializationNumberField.setText("");
            studentSpecializationNameField.setText("");
            studentSpecializationStudyDurationField.setText("");
            studentGroupNameField.setText("");
            studentGroupDateFormationField.setText("");
            studentFormOfEducationNameField.setText("");
            studentBasisOfEducationNameField.setText("");
            studentDateAdmissionField.setText("");
            studentAddressComboBox.setValue(null);
            studentGroupComboBox.setValue(null);
            studentSpecializationComboBox.setValue(null);
            studentFormOfEducationComboBox.setValue(null);
            studentBasisOfEducationComboBox.setValue(null);
            studentAddressGroup.selectToggle(null);
            studentGroupGroup.selectToggle(null);
            studentSpecializationGroup.selectToggle(null);
            studentFormOfEducationGroup.selectToggle(null);
            studentBasisOfEducationGroup.selectToggle(null);
            onStudentsRefreshButton();
        }
    }

    @FXML
    protected void onStudentsDeleteButton() {
        try {
            AppManager.getStudentDao().delete(studentsTable.getSelectionModel().getSelectedItem());
            onStudentsRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onStudentsSearch() {
        if (!studentsSearchField.getText().isEmpty()) {
            studentsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getStudentDao().findAll()).filtered(
                            createStudentsPredicate(studentsSearchField.getText()))));
        }
    }

    @FXML
    protected void onStudentsSearchClear() {
        studentsSearchField.setText("");
        onStudentsRefreshButton();
    }

    private Predicate<Student> createStudentsPredicate(String searchText){
        return student -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return student.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onParentsRefreshButton() {
        parentsTable.setItems(FXCollections.observableArrayList(AppManager.getParentDao().findAll()));
        parentStudentComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getStudentDao().findAll()));
        parentsSearchField.setText("");
    }

    @FXML
    protected void onParentsAddButton() {
        Parent parent = new Parent();
        parent.setLastName(parentLastNameField.getText());
        parent.setFirstName(parentFirstNameField.getText());
        parent.setPatronymic(parentPatronymicField.getText());
        if (parentAddressExitsRadioButton.isSelected()) {
            parent.setAddress(parentAddressComboBox.getValue());
        }
        else {
            Address address = new Address();
            address.setCity(parentAddressCityField.getText());
            address.setStreet(parentAddressStreetField.getText());
            address.setHouseNumber(parentAddressHouseNumberField.getText());
            address.setApartmentNumber(Integer.parseInt(parentAddressApartmentNumberField.getText()));
            parent.setAddress(address);
            AppManager.getAddressesDao().insert(address);
        }
        parent.setStudent(parentStudentComboBox.getValue());
        if (AppManager.getParentDao().contains(parent)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getParentDao().insert(parent);
            parentLastNameField.setText("");
            parentFirstNameField.setText("");
            parentPatronymicField.setText("");
            parentAddressCityField.setText("");
            parentAddressStreetField.setText("");
            parentAddressHouseNumberField.setText("");
            parentAddressApartmentNumberField.setText("");
            parentAddressComboBox.setValue(null);
            parentStudentComboBox.getSelectionModel().select(0);
            parentAddressGroup.selectToggle(null);
            onParentsRefreshButton();
        }
    }

    @FXML
    protected void onParentsDeleteButton() {
        try {
            AppManager.getParentDao().delete(parentsTable.getSelectionModel().getSelectedItem());
            onParentsRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onParentsSearch() {
        if (!parentsSearchField.getText().isEmpty()) {
            parentsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getParentDao().findAll()).filtered(
                            createParentsPredicate(parentsSearchField.getText()))));
        }
    }

    @FXML
    protected void onParentsSearchClear() {
        parentsSearchField.setText("");
        onParentsRefreshButton();
    }

    private Predicate<Parent> createParentsPredicate(String searchText){
        return parent -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return parent.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onContractDataRefreshButton() {
        contractDataTable.setItems(FXCollections.observableArrayList(AppManager.getContractDataDao().findAll()));
        contractDataStudentComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getStudentDao().findAll()));
        contractDataSearchField.setText("");
    }

    @FXML
    protected void onContractDataAddButton() {
        ContractData contractData = new ContractData();
        contractData.setStudent(contractDataStudentComboBox.getValue());
        try {
            contractData.setContractDate((new SimpleDateFormat(dateTimePattern)).parse(contractDataDateField.getText()));
        } catch (ParseException ignored) {}
        if (contractData.getCurrentPayments() == null) {
            try {
                contractData.setContractDate((new SimpleDateFormat(datePattern)).parse(contractDataDateField.getText()));
            } catch (ParseException ignored) {}
        }
        contractData.setPaymentAmount(Float.parseFloat(contractDataPaymentAmountField.getText()));
        if (contractDataCurrentPaymentExitsRadioButton.isSelected()) {
            contractData.setCurrentPayments(contractDataCurrentPaymentComboBox.getValue());
        }
        else {
            CurrentPayment currentPayment = new CurrentPayment();
            currentPayment.setAmount(Float.parseFloat(contractDataCurrentPaymentAmountField.getText()));
            try {
                currentPayment.setPaymentDate((new SimpleDateFormat(dateTimePattern)).parse(contractDataCurrentPaymentDateField.getText()));
            } catch (ParseException ignored) {}
            if (currentPayment.getPaymentDate() == null) {
                try {
                    currentPayment.setPaymentDate((new SimpleDateFormat(datePattern)).parse(contractDataCurrentPaymentDateField.getText()));
                } catch (ParseException ignored) {}
            }
            currentPayment.setDocNumber(Integer.parseInt(contractDataCurrentPaymentDocNumberField.getText()));
            contractData.setCurrentPayments(currentPayment);
            AppManager.getCurrentPaymentsDao().insert(currentPayment);
        }
        if (contractDataStatusExitsRadioButton.isSelected()) {
            contractData.setStatuses(contractDataStatusComboBox.getValue());
        }
        else {
            Status status = new Status();
            status.setName(contractDataStatusNameField.getText());
            contractData.setStatuses(status);
            AppManager.getStatusesDao().insert(status);
        }
        if (AppManager.getContractDataDao().contains(contractData)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }
        else {
            AppManager.getContractDataDao().insert(contractData);
            contractDataDateField.setText("");
            contractDataPaymentAmountField.setText("");
            contractDataCurrentPaymentAmountField.setText("");
            contractDataCurrentPaymentDateField.setText("");
            contractDataCurrentPaymentDocNumberField.setText("");
            contractDataStatusNameField.setText("");
            contractDataStudentComboBox.getSelectionModel().select(0);
            contractDataCurrentPaymentComboBox.setValue(null);
            contractDataStatusComboBox.setValue(null);
            contractDataCurrentPaymentGroup.selectToggle(null);
            contractDataStatusGroup.selectToggle(null);
            onContractDataRefreshButton();
        }
    }

    @FXML
    protected void onContractDataDeleteButton() {
        try {
            AppManager.getContractDataDao().delete(contractDataTable.getSelectionModel().getSelectedItem());
            onContractDataRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onContractDataSearch() {
        if (!contractDataSearchField.getText().isEmpty()) {
            contractDataTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getContractDataDao().findAll()).filtered(
                            createContractDataPredicate(contractDataSearchField.getText()))));
        }
    }

    @FXML
    protected void onContractDataSearchClear() {
        contractDataSearchField.setText("");
        onContractDataRefreshButton();
    }

    private Predicate<ContractData> createContractDataPredicate(String searchText){
        return contractData -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return contractData.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onSemesterPerformanceRefreshButton() {
        semesterPerformanceTable.setItems(FXCollections.observableArrayList(AppManager.getSemesterPerformanceDao().findAll()));
        semesterPerformanceStudentComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getStudentDao().findAll()));
        semesterPerformanceSearchField.setText("");
    }

    @FXML
    protected void onSemesterPerformanceAddButton() {
        SemesterPerformance semesterPerformance = new SemesterPerformance();
        semesterPerformance.setStudent(semesterPerformanceStudentComboBox.getValue());
        semesterPerformance.setCourse(Integer.parseInt(semesterPerformanceCourseField.getText()));
        semesterPerformance.setSemester(Integer.parseInt(semesterPerformanceSemesterField.getText()));

        if (semesterPerformanceDisciplineExitsRadioButton.isSelected()) {
            semesterPerformance.setDiscipline(semesterPerformanceDisciplineComboBox.getValue());
        }
        else {
            Discipline discipline = new Discipline();
            discipline.setName(semesterPerformanceDisciplineNameField.getText());

            if (semesterPerformanceDisciplineTypeOfMarkExitsRadioButton.isSelected()) {
                discipline.setTypeOfMark(semesterPerformanceDisciplineTypeOfMarkComboBox.getValue());
            }
            else {
                TypeOfMark typeOfMark = new TypeOfMark();
                typeOfMark.setName(semesterPerformanceDisciplineTypeOfMarkNameField.getText());
                discipline.setTypeOfMark(typeOfMark);
                AppManager.getTypesOfMarksDao().insert(typeOfMark);
            }

            semesterPerformance.setDiscipline(discipline);
            AppManager.getDisciplinesDao().insert(discipline);
        }

        semesterPerformance.setMark(Integer.parseInt(semesterPerformanceMarkField.getText()));
        semesterPerformance.setEctsMark(MarksUtils.getEctsMark(semesterPerformance.getMark()));
        semesterPerformance.setTraditionalMark(MarksUtils.getTraditionalMark(semesterPerformance.getMark()));
        semesterPerformance.setTraditionalWordMark(MarksUtils.getTraditionalWordMark(semesterPerformance.getMark(),
                semesterPerformance.getDiscipline().getTypeOfMark().getName()));

        if (AppManager.getSemesterPerformanceDao().contains(semesterPerformance)) {
            readdingAlert.showAndWait();
            readdingAlert.getResult();
        }

        else {
            AppManager.getSemesterPerformanceDao().insert(semesterPerformance);
            semesterPerformanceCourseField.setText("");
            semesterPerformanceSemesterField.setText("");
            semesterPerformanceDisciplineNameField.setText("");
            semesterPerformanceDisciplineTypeOfMarkNameField.setText("");
            semesterPerformanceMarkField.setText("");
            semesterPerformanceStudentComboBox.getSelectionModel().select(0);
            semesterPerformanceDisciplineComboBox.setValue(null);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setValue(null);
            disciplineTypeOfMarkComboBox.setValue(null);
            semesterPerformanceDisciplineGroup.selectToggle(null);
            semesterPerformanceDisciplineTypeOfMarkGroup.selectToggle(null);
            onSemesterPerformanceRefreshButton();
        }
    }

    @FXML
    protected void onSemesterPerformanceDeleteButton() {
        try {
            AppManager.getSemesterPerformanceDao().delete(semesterPerformanceTable.getSelectionModel().getSelectedItem());
            onSemesterPerformanceRefreshButton();
        }
        catch (Exception ignored) {
            deleteAlert.showAndWait();
        }
    }

    @FXML
    protected void onSemesterPerformanceSearch() {
        if (!semesterPerformanceSearchField.getText().isEmpty()) {
            semesterPerformanceTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getSemesterPerformanceDao().findAll()).filtered(
                            createSemesterPerformancePredicate(semesterPerformanceSearchField.getText()))));
        }
    }

    @FXML
    protected void onSemesterPerformanceSearchClear() {
        semesterPerformanceSearchField.setText("");
        onSemesterPerformanceRefreshButton();
    }

    private Predicate<SemesterPerformance> createSemesterPerformancePredicate(String searchText){
        return semesterPerformance -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return semesterPerformance.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }

    @FXML
    protected void onAddressesTabChanged() {
        if (addressesTab.isSelected()) {
            onAddressesRefreshButton();
        }
    }

    @FXML
    protected void onBasisOfEducationTabChanged() {
        if (basisOfEducationTab.isSelected()) {
            onBasisOfEducationRefreshButton();
        }
    }

    @FXML
    protected void onDisciplinesTabChanged() {
        if (disciplinesTab.isSelected()) {
            onDisciplinesRefreshButton();
        }
    }

    @FXML
    protected void onFormsOfEducationTabChanged() {
        if (formsOfEducationTab.isSelected()) {
            onFormsOfEducationRefreshButton();
        }
    }

    @FXML
    protected void onStatusesTabChanged() {
        if (statusesTab.isSelected()) {
            onStatusesRefreshButton();
        }
    }

    @FXML
    protected void onTypesOfMarksTabChanged() {
        if (typesOfMarksTab.isSelected()) {
            onTypesOfMarksRefreshButton();
        }
    }

    @FXML
    protected void onSpecializationsTabChanged() {
        if (specializationsTab.isSelected()) {
            onSpecializationsRefreshButton();
        }
    }

    @FXML
    protected void onCurrentPaymentsTabChanged() {
        if (currentPaymentsTab.isSelected()) {
            onCurrentPaymentsRefreshButton();
        }
    }

    @FXML
    protected void onGroupsTabChanged() {
        if (groupsTab.isSelected()) {
            onGroupsRefreshButton();
        }
    }

    @FXML
    protected void onStudentsTabChanged() {
        if (studentsTab.isSelected()) {
            onStudentsRefreshButton();
        }
    }

    @FXML
    protected void onParentsTabChanged() {
        if (parentsTab.isSelected()) {
            onParentsRefreshButton();
        }
    }

    @FXML
    protected void onContractDataTabChanged() {
        if (contractDataTab.isSelected()) {
            onContractDataRefreshButton();
        }
    }

    @FXML
    protected void onSemesterPerformanceTabChanged() {
        if (semesterPerformanceTab.isSelected()) {
            onSemesterPerformanceRefreshButton();
        }
    }

    @FXML
    protected void onFormNameColumnEditCommit(TableColumn.CellEditEvent<FormOfEducation, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(value);
        formsOfEducationTable.refresh();
        AppManager.getFormsOfEducationDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onBasisNameColumnEditCommit(TableColumn.CellEditEvent<BasisOfEducation, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(value);
        basisOfEducationTable.refresh();
        AppManager.getBasisOfEducationDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStatusNameColumnEditCommit(TableColumn.CellEditEvent<Status, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(value);
        statusesTable.refresh();
        AppManager.getStatusesDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onTypeOfMarkNameColumnEditCommit(TableColumn.CellEditEvent<TypeOfMark, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(value);
        typesOfMarksTable.refresh();
        AppManager.getTypesOfMarksDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onDisciplineNameColumnEditCommit(TableColumn.CellEditEvent<Discipline, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(value);
        disciplinesTable.refresh();
        AppManager.getDisciplinesDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onSpecializationNumberColumnEditCommit(TableColumn.CellEditEvent<Specialization, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setNumber(value);
        specializationsTable.refresh();
        AppManager.getSpecializationsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onSpecializationNameColumnEditCommit(TableColumn.CellEditEvent<Specialization, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(value);
        specializationsTable.refresh();
        AppManager.getSpecializationsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onSpecializationStudyDurationColumnEditCommit(TableColumn.CellEditEvent<Specialization, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setStudyDuration(value);
        specializationsTable.refresh();
        AppManager.getSpecializationsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onCityColumnEditCommit(TableColumn.CellEditEvent<Address, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setCity(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStreetColumnEditCommit(TableColumn.CellEditEvent<Address, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setStreet(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onHouseNumberColumnEditCommit(TableColumn.CellEditEvent<Address, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setHouseNumber(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onApartmentNumberColumnEditCommit(TableColumn.CellEditEvent<Address, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setApartmentNumber(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onAmountColumnEditCommit(TableColumn.CellEditEvent<CurrentPayment, Float> event) {
        final Float value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setAmount(value);
        currentPaymentsTable.refresh();
        AppManager.getCurrentPaymentsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onPaymentDateColumnEditCommit(TableColumn.CellEditEvent<CurrentPayment, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setPaymentDate(value);
        currentPaymentsTable.refresh();
        AppManager.getCurrentPaymentsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onDocNumberColumnEditCommit(TableColumn.CellEditEvent<CurrentPayment, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setDocNumber(value);
        currentPaymentsTable.refresh();
        AppManager.getCurrentPaymentsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onGroupNameColumnEditCommit(TableColumn.CellEditEvent<Group, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(value);
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onCourseColumnEditCommit(TableColumn.CellEditEvent<Group, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setCourse(value);
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onSemesterColumnEditCommit(TableColumn.CellEditEvent<Group, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setSemester(value);
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onDateFormationColumnEditCommit(TableColumn.CellEditEvent<Group, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setDateFormation(value);
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentLastNameColumnEditCommit(TableColumn.CellEditEvent<Student, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentFirstNameColumnEditCommit(TableColumn.CellEditEvent<Student, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentPatronymicColumnEditCommit(TableColumn.CellEditEvent<Student, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setPatronymic(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentAddressColumnEditCommit(TableColumn.CellEditEvent<Student, Address> event) {
        final Address value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        if (!AppManager.getAddressesDao().contains(value) && !addingAlert.isShowing()) {
            addingAlert.showAndWait();
            if (addingAlert.getResult() == ButtonType.YES) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setAddress(value);
                studentsTable.refresh();
                AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
            }
        }
    }

    @FXML
    protected void onStudentDateOfBirthColumnEditCommit(TableColumn.CellEditEvent<Student, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setDateOfBirth(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentGroupColumnEditCommit(TableColumn.CellEditEvent<Student, Group> event) {
        final Group value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setGroup(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentFormOfEducationColumnEditCommit(TableColumn.CellEditEvent<Student, FormOfEducation> event) {
        final FormOfEducation value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setFormOfEducation(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentBasisOfEducationColumnEditCommit(TableColumn.CellEditEvent<Student, BasisOfEducation> event) {
        final BasisOfEducation value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setBasisOfEducation(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onStudentDateAdmissionColumnEditCommit(TableColumn.CellEditEvent<Student, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setDateAdmission(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onParentLastNameColumnEditCommit(TableColumn.CellEditEvent<Parent, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onParentFirstNameColumnEditCommit(TableColumn.CellEditEvent<Parent, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onParentPatronymicColumnEditCommit(TableColumn.CellEditEvent<Parent, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setPatronymic(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onParentStudentColumnEditCommit(TableColumn.CellEditEvent<Parent, Student> event) {
        final Student value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setStudent(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onParentAddressColumnEditCommit(TableColumn.CellEditEvent<Parent, Address> event) {
        final Address value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        if (!AppManager.getAddressesDao().contains(value) && !addingAlert.isShowing()) {
            addingAlert.showAndWait();
            if (addingAlert.getResult() == ButtonType.YES) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setAddress(value);
                studentsTable.refresh();
                AppManager.getParentDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
            }
        }
    }

    @FXML
    protected void onContractDataStudentColumnEditCommit(TableColumn.CellEditEvent<ContractData, Student> event) {
        final Student value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setStudent(value);
        contractDataTable.refresh();
        AppManager.getContractDataDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onContractDataDateColumnEditCommit(TableColumn.CellEditEvent<ContractData, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setContractDate(value);
        contractDataTable.refresh();
        AppManager.getContractDataDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onContractDataPaymentAmountColumnEditCommit(TableColumn.CellEditEvent<ContractData, Float> event) {
        final Float value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setPaymentAmount(value);
        contractDataTable.refresh();
        AppManager.getContractDataDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onContractDataCurrentPaymentColumnEditCommit(TableColumn.CellEditEvent<ContractData, CurrentPayment> event) {
        final CurrentPayment value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setCurrentPayments(value);
        contractDataTable.refresh();
        AppManager.getContractDataDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onContractDataStatusColumnEditCommit(TableColumn.CellEditEvent<ContractData, Status> event) {
        final Status value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setStatuses(value);
        contractDataTable.refresh();
        AppManager.getContractDataDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onDisciplineTypeOfMarkColumnEditCommit(TableColumn.CellEditEvent<Discipline, TypeOfMark> event) {
        final TypeOfMark value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setTypeOfMark(value);
        semesterPerformanceTable.refresh();
        AppManager.getDisciplinesDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onSemesterPerformanceStudentColumnEditCommit(TableColumn.CellEditEvent<SemesterPerformance, Student> event) {
        final Student value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setStudent(value);
        semesterPerformanceTable.refresh();
        AppManager.getSemesterPerformanceDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onSemesterPerformanceDisciplineColumnEditCommit(TableColumn.CellEditEvent<SemesterPerformance, Discipline> event) {
        final Discipline value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setDiscipline(value);
        semesterPerformanceTable.refresh();
        AppManager.getSemesterPerformanceDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onSemesterPerformanceMarkColumnEditCommit(TableColumn.CellEditEvent<SemesterPerformance, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setMark(value);
        semesterPerformanceTable.refresh();
        AppManager.getSemesterPerformanceDao().update(event.getTableView().getItems().get(event.getTablePosition().getRow()));
    }

    @FXML
    protected void onGroupSpecializationChoose() {
        if (groupSpecializationExitsRadioButton.isSelected()) {
            groupSpecializationsNumberField.setDisable(true);
            groupSpecializationsNameField.setDisable(true);
            groupSpecializationsStudyDurationField.setDisable(true);
            groupSpecializationComboBox.setDisable(false);
            groupSpecializationComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getSpecializationsDao().findAll()));
            groupSpecializationComboBox.getSelectionModel().select(0);
        }
        else if (groupSpecializationNewRadioButton.isSelected()) {
            groupSpecializationComboBox.setDisable(true);
            groupSpecializationsNumberField.setDisable(false);
            groupSpecializationsNameField.setDisable(false);
            groupSpecializationsStudyDurationField.setDisable(false);
            groupSpecializationComboBox.setValue(null);
        }
    }

    @FXML
    protected void onStudentAddressChoose() {
        if (studentAddressExitsRadioButton.isSelected()) {
            studentAddressCityField.setDisable(true);
            studentAddressStreetField.setDisable(true);
            studentAddressHouseNumberField.setDisable(true);
            studentAddressApartmentNumberField.setDisable(true);
            studentAddressComboBox.setDisable(false);
            studentAddressComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getAddressesDao().findAll()));
            studentAddressComboBox.getSelectionModel().select(0);
        }
        else if (studentAddressNewRadioButton.isSelected()) {
            studentAddressComboBox.setDisable(true);
            studentAddressCityField.setDisable(false);
            studentAddressStreetField.setDisable(false);
            studentAddressHouseNumberField.setDisable(false);
            studentAddressApartmentNumberField.setDisable(false);
            studentAddressComboBox.setValue(null);
        }
    }

    @FXML
    protected void onStudentGroupChoose() {
        if (studentGroupExitsRadioButton.isSelected()) {
            studentGroupNameField.setDisable(true);
            studentGroupDateFormationField.setDisable(true);
            studentSpecializationExitsRadioButton.setDisable(true);
            studentSpecializationNewRadioButton.setDisable(true);
            studentGroupComboBox.setDisable(false);
            studentGroupComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getGroupsDao().findAll()));
            studentGroupComboBox.getSelectionModel().select(0);
        }
        else if (studentGroupNewRadioButton.isSelected()) {
            studentGroupComboBox.setDisable(true);
            studentGroupNameField.setDisable(false);
            studentGroupDateFormationField.setDisable(false);
            studentSpecializationExitsRadioButton.setDisable(false);
            studentSpecializationNewRadioButton.setDisable(false);
            studentGroupComboBox.setValue(null);
        }
    }

    @FXML
    protected void onStudentSpecializationChoose() {
        if (studentSpecializationExitsRadioButton.isSelected()) {
            studentSpecializationNumberField.setDisable(true);
            studentSpecializationNameField.setDisable(true);
            studentSpecializationStudyDurationField.setDisable(true);
            studentSpecializationComboBox.setDisable(false);
            studentSpecializationComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getSpecializationsDao().findAll()));
            studentSpecializationComboBox.getSelectionModel().select(0);
        }
        else if (studentSpecializationNewRadioButton.isSelected()) {
            studentSpecializationComboBox.setDisable(true);
            studentSpecializationNumberField.setDisable(false);
            studentSpecializationNameField.setDisable(false);
            studentSpecializationStudyDurationField.setDisable(false);
            studentSpecializationComboBox.setValue(null);
        }
    }

    @FXML
    protected void onStudentFormOfEducationChoose() {
        if (studentFormOfEducationExitsRadioButton.isSelected()) {
            studentFormOfEducationNameField.setDisable(true);
            studentFormOfEducationComboBox.setDisable(false);
            studentFormOfEducationComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getFormsOfEducationDao().findAll()));
            studentFormOfEducationComboBox.getSelectionModel().select(0);
        }
        else if (studentFormOfEducationNewRadioButton.isSelected()) {
            studentFormOfEducationComboBox.setDisable(true);
            studentFormOfEducationNameField.setDisable(false);
            studentFormOfEducationComboBox.setValue(null);
        }
    }

    @FXML
    protected void onStudentBasisOfEducationChoose() {
        if (studentBasisOfEducationExitsRadioButton.isSelected()) {
            studentBasisOfEducationNameField.setDisable(true);
            studentBasisOfEducationComboBox.setDisable(false);
            studentBasisOfEducationComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getBasisOfEducationDao().findAll()));
            studentBasisOfEducationComboBox.getSelectionModel().select(0);
        }
        else if (studentBasisOfEducationNewRadioButton.isSelected()) {
            studentBasisOfEducationComboBox.setDisable(true);
            studentBasisOfEducationNameField.setDisable(false);
            studentBasisOfEducationComboBox.setValue(null);
        }
    }

    @FXML
    protected void onParentAddressChoose() {
        if (parentAddressExitsRadioButton.isSelected()) {
            parentAddressCityField.setDisable(true);
            parentAddressStreetField.setDisable(true);
            parentAddressHouseNumberField.setDisable(true);
            parentAddressApartmentNumberField.setDisable(true);
            parentAddressComboBox.setDisable(false);
            parentAddressComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getAddressesDao().findAll()));
            parentAddressComboBox.getSelectionModel().select(0);
        }
        else if (parentAddressNewRadioButton.isSelected()) {
            parentAddressComboBox.setDisable(true);
            parentAddressCityField.setDisable(false);
            parentAddressStreetField.setDisable(false);
            parentAddressHouseNumberField.setDisable(false);
            parentAddressApartmentNumberField.setDisable(false);
            parentAddressComboBox.setValue(null);
        }
    }

    @FXML
    protected void onContractDataCurrentPaymentChoose() {
        if (contractDataCurrentPaymentExitsRadioButton.isSelected()) {
            contractDataCurrentPaymentAmountField.setDisable(true);
            contractDataCurrentPaymentDateField.setDisable(true);
            contractDataCurrentPaymentDocNumberField.setDisable(true);
            contractDataCurrentPaymentComboBox.setDisable(false);
            contractDataCurrentPaymentComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getCurrentPaymentsDao().findAll()));
            contractDataCurrentPaymentComboBox.getSelectionModel().select(0);
        }
        else if (contractDataCurrentPaymentNewRadioButton.isSelected()) {
            contractDataCurrentPaymentComboBox.setDisable(true);
            contractDataCurrentPaymentAmountField.setDisable(false);
            contractDataCurrentPaymentDateField.setDisable(false);
            contractDataCurrentPaymentDocNumberField.setDisable(false);
            contractDataCurrentPaymentComboBox.setValue(null);
        }
    }

    @FXML
    protected void onContractDataStatusChoose() {
        if (contractDataStatusExitsRadioButton.isSelected()) {
            contractDataStatusNameField.setDisable(true);
            contractDataStatusComboBox.setDisable(false);
            contractDataStatusComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getStatusesDao().findAll()));
            contractDataStatusComboBox.getSelectionModel().select(0);
        }
        else if (contractDataStatusNewRadioButton.isSelected()) {
            contractDataStatusComboBox.setDisable(true);
            contractDataStatusNameField.setDisable(false);
            contractDataStatusComboBox.setValue(null);
        }
    }

    @FXML
    protected void onSemesterPerformanceDisciplineChoose() {
        if (semesterPerformanceDisciplineExitsRadioButton.isSelected()) {
            semesterPerformanceDisciplineNameField.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkExitsRadioButton.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkNewRadioButton.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkNameField.setDisable(true);
            semesterPerformanceDisciplineComboBox.setDisable(false);
            semesterPerformanceDisciplineComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getDisciplinesDao().findAll()));
            semesterPerformanceDisciplineComboBox.getSelectionModel().select(0);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setValue(null);
            semesterPerformanceDisciplineTypeOfMarkGroup.selectToggle(null);
        }
        else if (semesterPerformanceDisciplineNewRadioButton.isSelected()) {
            semesterPerformanceDisciplineComboBox.setDisable(true);
            semesterPerformanceDisciplineNameField.setDisable(false);
            semesterPerformanceDisciplineTypeOfMarkExitsRadioButton.setDisable(false);
            semesterPerformanceDisciplineTypeOfMarkNewRadioButton.setDisable(false);
            semesterPerformanceDisciplineComboBox.setValue(null);
        }
    }

    @FXML
    protected void onSemesterPerformanceDisciplineTypeOfMarksChoose() {
        if (semesterPerformanceDisciplineTypeOfMarkExitsRadioButton.isSelected()) {
            semesterPerformanceDisciplineTypeOfMarkNameField.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setDisable(false);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getTypesOfMarksDao().findAll()));
            semesterPerformanceDisciplineTypeOfMarkComboBox.getSelectionModel().select(0);
        }
        else if (semesterPerformanceDisciplineTypeOfMarkNewRadioButton.isSelected()) {
            semesterPerformanceDisciplineTypeOfMarkComboBox.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkNameField.setDisable(false);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setValue(null);
        }
    }

    @FXML
    protected void onDisciplineTypeOfMarkChoose() {
        if (disciplineTypeOfMarkExitsRadioButton.isSelected()) {
            disciplinesTypeOfMarkNameField.setDisable(true);
            disciplineTypeOfMarkComboBox.setDisable(false);
            disciplineTypeOfMarkComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getTypesOfMarksDao().findAll()));
            disciplineTypeOfMarkComboBox.getSelectionModel().select(0);
        }
        else if (disciplineTypeOfMarkNewRadioButton.isSelected()) {
            disciplineTypeOfMarkComboBox.setDisable(true);
            disciplinesTypeOfMarkNameField.setDisable(false);
            disciplineTypeOfMarkComboBox.setValue(null);
        }
    }

    @FXML
    protected void onAdvancedModeClick() {
        if (advancedMode.isSelected()) activateAdvancedTabs();
        else deactivateAdvancedTabs();
    }

    private void activateAdvancedTabs() {
//        tabPane.getTabs().add(currentPaymentsTab);
        tabPane.getTabs().add(addressesTab);
        tabPane.getTabs().add(groupsTab);
        tabPane.getTabs().add(specializationsTab);
        tabPane.getTabs().add(disciplinesTab);
        tabPane.getTabs().add(typesOfMarksTab);
//        tabPane.getTabs().add(statusesTab);
        tabPane.getTabs().add(formsOfEducationTab);
        tabPane.getTabs().add(basisOfEducationTab);
    }

    private void deactivateAdvancedTabs() {
//        tabPane.getTabs().remove(currentPaymentsTab);
        tabPane.getTabs().remove(addressesTab);
        tabPane.getTabs().remove(groupsTab);
        tabPane.getTabs().remove(specializationsTab);
        tabPane.getTabs().remove(disciplinesTab);
        tabPane.getTabs().remove(typesOfMarksTab);
//        tabPane.getTabs().remove(statusesTab);
        tabPane.getTabs().remove(formsOfEducationTab);
        tabPane.getTabs().remove(basisOfEducationTab);
    }

    @FXML
    protected void onAboutProgramClick() throws IOException {
        aboutProgramStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about-program-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        aboutProgramStage.setScene(scene);
        aboutProgramStage.setTitle("О программе");
        aboutProgramStage.setResizable(false);
        aboutProgramStage.initModality(Modality.WINDOW_MODAL);
        aboutProgramStage.initOwner(tabPane.getScene().getWindow());
        aboutProgramStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deactivateAdvancedTabs();
        initAllTables();
        initAllButtons();
        initAllRadioButtons();
    }

    private void initAllRadioButtons() {
        groupSpecializationNewRadioButton.setToggleGroup(groupSpecializationGroup);
        groupSpecializationExitsRadioButton.setToggleGroup(groupSpecializationGroup);

        studentAddressExitsRadioButton.setToggleGroup(studentAddressGroup);
        studentAddressNewRadioButton.setToggleGroup(studentAddressGroup);

        studentGroupExitsRadioButton.setToggleGroup(studentGroupGroup);
        studentGroupNewRadioButton.setToggleGroup(studentGroupGroup);

        studentSpecializationExitsRadioButton.setToggleGroup(studentSpecializationGroup);
        studentSpecializationNewRadioButton.setToggleGroup(studentSpecializationGroup);

        studentFormOfEducationExitsRadioButton.setToggleGroup(studentFormOfEducationGroup);
        studentFormOfEducationNewRadioButton.setToggleGroup(studentFormOfEducationGroup);

        studentBasisOfEducationExitsRadioButton.setToggleGroup(studentBasisOfEducationGroup);
        studentBasisOfEducationNewRadioButton.setToggleGroup(studentBasisOfEducationGroup);

        parentAddressExitsRadioButton.setToggleGroup(parentAddressGroup);
        parentAddressNewRadioButton.setToggleGroup(parentAddressGroup);

//        contractDataCurrentPaymentExitsRadioButton.setToggleGroup(contractDataCurrentPaymentGroup);
//        contractDataCurrentPaymentNewRadioButton.setToggleGroup(contractDataCurrentPaymentGroup);

//        contractDataStatusExitsRadioButton.setToggleGroup(contractDataStatusGroup);
//        contractDataStatusNewRadioButton.setToggleGroup(contractDataStatusGroup);

        semesterPerformanceDisciplineExitsRadioButton.setToggleGroup(semesterPerformanceDisciplineGroup);
        semesterPerformanceDisciplineNewRadioButton.setToggleGroup(semesterPerformanceDisciplineGroup);

        semesterPerformanceDisciplineTypeOfMarkExitsRadioButton.setToggleGroup(semesterPerformanceDisciplineTypeOfMarkGroup);
        semesterPerformanceDisciplineTypeOfMarkNewRadioButton.setToggleGroup(semesterPerformanceDisciplineTypeOfMarkGroup);

        disciplineTypeOfMarkExitsRadioButton.setToggleGroup(disciplineTypeOfMarkGroup);
        disciplineTypeOfMarkNewRadioButton.setToggleGroup(disciplineTypeOfMarkGroup);
    }

    private void initAllTables() {
        initAddressesTable();
        initBasisOfEducationTable();
        initFormsOfEducationTable();
//        initStatusesTable();
        initTypesOfMarksTable();
        initDisciplinesTable();
        initSpecializationsTable();
//        initCurrentPaymentsTable();
        initGroupsTable();
        initStudentsTable();
        initParentsTable();
//        initContractDataTable();
        initSemesterPerformanceTable();
    }

    private void initAllButtons() {
        initAddressButtons();
        initBasisOfEducationButtons();
        initFormsOfEducationButtons();
//        initStatusesButtons();
        initTypesOfMarksButtons();
        initDisciplinesButtons();
        initSpecializationsButtons();
//        initCurrentPaymentsButtons();
        initGroupsButtons();
        initStudentsButtons();
        initParentsButtons();
//        initContractDataButtons();
        initSemesterPerformanceButtons();
    }

    private void initAddressesTable() {
        addressesTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        apartmentNumberColumn.setCellFactory(CustomTableCell.forTableColumn(new IntegerStringConverter()));
        addressesSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onAddressesSearch();
                }
            }
        });
    }

    private void initBasisOfEducationTable() {
        basisOfEducationTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        basisOfEducationSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onBasisOfEducationSearch();
                }
            }
        });
    }

    private void initFormsOfEducationTable() {
        formsOfEducationTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        formsOfEducationSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onFormsOfEducationSearch();
                }
            }
        });
    }

//    private void initStatusesTable() {
//        statusesTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
//        statusesSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
//                    onStatusesSearch();
//                }
//            }
//        });
//    }

    private void initTypesOfMarksTable() {
        typesOfMarksTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        typesOfMarksSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onTypesOfMarksSearch();
                }
            }
        });
    }

    private void initDisciplinesTable() {
        disciplinesTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        disciplineTypeOfMarkColumn.setCellFactory(tc -> {
            ComboBox<TypeOfMark> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getTypesOfMarksDao().findAll());
            TableCell<Discipline, TypeOfMark> cell = new TableCell<>() {
                @Override
                protected void updateItem(TypeOfMark reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setTooltip(null);
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setTooltip(new Tooltip(combo.getValue().toString()));
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                disciplinesTable.getItems().get(cell.getIndex()).setTypeOfMark(combo.getValue());
                disciplinesTable.refresh();
                AppManager.getDisciplinesDao().update(disciplinesTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        disciplinesSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onDisciplinesSearch();
                }
            }
        });
    }

    private void initSpecializationsTable() {
        specializationsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        studyDurationColumn.setCellFactory(CustomTableCell.forTableColumn(new IntegerStringConverter()));
        specializationsSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onSpecializationsSearch();
                }
            }
        });
    }

//    private void initCurrentPaymentsTable() {
//        currentPaymentsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
//        amountColumn.setCellFactory(CustomTableCell.forTableColumn(new FloatStringConverter()));
//        paymentDateColumn.setCellFactory(CustomTableCell.forTableColumn(new CustomDateStringConverter(dateTimePattern)));
//        docNumberColumn.setCellFactory(CustomTableCell.forTableColumn(new IntegerStringConverter()));
//        currentPaymentsSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
//                    onCurrentPaymentsSearch();
//                }
//            }
//        });
//    }

    private void initGroupsTable() {
        groupsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        courseColumn.setCellFactory(CustomTableCell.forTableColumn(new IntegerStringConverter()));
        semesterColumn.setCellFactory(CustomTableCell.forTableColumn(new IntegerStringConverter()));
        specializationColumn.setCellFactory(tc -> {
            ComboBox<Specialization> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getSpecializationsDao().findAllWithOrder("number"));
            TableCell<Group, Specialization> cell = new TableCell<>() {
                @Override
                protected void updateItem(Specialization reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                groupsTable.getItems().get(cell.getIndex()).setSpecialization(combo.getValue());
                groupsTable.refresh();
                AppManager.getGroupsDao().update(groupsTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        dateFormationColumn.setCellFactory(CustomTableCell.forTableColumn(new CustomDateStringConverter(datePattern)));
        dateGraduationColumn.setCellFactory(CustomTableCell.forTableColumn(new CustomDateStringConverter(datePattern)));
        groupsSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onGroupsSearch();
                }
            }
        });
    }

    private void initStudentsTable() {
        studentsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        dateOfBirthColumn.setCellFactory(CustomTableCell.forTableColumn(new CustomDateStringConverter(datePattern)));
        studentAddressColumn.setCellFactory(CustomTableCell.forTableColumn(new AddressConverter()));
        groupColumn.setCellFactory(tc -> {
            ComboBox<Group> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getGroupsDao().findAll());
            TableCell<Student, Group> cell = new TableCell<>() {
                @Override
                protected void updateItem(Group reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setTooltip(null);
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setTooltip(new Tooltip(combo.getValue().toString()));
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                studentsTable.getItems().get(cell.getIndex()).setGroup(combo.getValue());
                studentsTable.refresh();
                AppManager.getStudentDao().update(studentsTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        formOfEducationColumn.setCellFactory(tc -> {
            ComboBox<FormOfEducation> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getFormsOfEducationDao().findAll());
            TableCell<Student, FormOfEducation> cell = new TableCell<>() {
                @Override
                protected void updateItem(FormOfEducation reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                studentsTable.getItems().get(cell.getIndex()).setFormOfEducation(combo.getValue());
                studentsTable.refresh();
                AppManager.getStudentDao().update(studentsTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        basisOfEducationColumn.setCellFactory(tc -> {
            ComboBox<BasisOfEducation> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getBasisOfEducationDao().findAll());
            TableCell<Student, BasisOfEducation> cell = new TableCell<>() {
                @Override
                protected void updateItem(BasisOfEducation reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                studentsTable.getItems().get(cell.getIndex()).setBasisOfEducation(combo.getValue());
                studentsTable.refresh();
                AppManager.getStudentDao().update(studentsTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        dateAdmissionColumn.setCellFactory(CustomTableCell.forTableColumn(new CustomDateStringConverter(datePattern)));
        studentsSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onStudentsSearch();
                }
            }
        });
    }

    private void initParentsTable() {
        parentsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        parentStudentComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getStudentDao().findAll()));
        parentStudentComboBox.getSelectionModel().select(0);
        parentStudentColumn.setCellFactory(tc -> {
            ComboBox<Student> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getStudentDao().findAll());
            TableCell<Parent, Student> cell = new TableCell<>() {
                @Override
                protected void updateItem(Student reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setTooltip(null);
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setTooltip(new Tooltip(combo.getValue().toString()));
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                parentsTable.getItems().get(cell.getIndex()).setStudent(combo.getValue());
                parentsTable.refresh();
                AppManager.getParentDao().update(parentsTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        parentAddressColumn.setCellFactory(CustomTableCell.forTableColumn(new AddressConverter()));
    }

//    private void initContractDataTable() {
//        contractDataTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
//        contractDataStudentComboBox.setItems(FXCollections.observableArrayList(
//                AppManager.getStudentDao().findAll()));
//        contractDataStudentComboBox.getSelectionModel().select(0);
//        contractDataStudentColumn.setCellFactory(tc -> {
//            ComboBox<Student> combo = new ComboBox<>();
//            combo.getItems().addAll(AppManager.getStudentDao().findAll());
//            TableCell<ContractData, Student> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(Student reason, boolean empty) {
//                    super.updateItem(reason, empty);
//                    if (empty) {
//                        setTooltip(null);
//                        setGraphic(null);
//                    } else {
//                        combo.setValue(reason);
//                        setTooltip(new Tooltip(combo.getValue().toString()));
//                        setGraphic(combo);
//                    }
//                }
//            };
//            combo.setOnAction(e -> {
//                contractDataTable.getItems().get(cell.getIndex()).setStudent(combo.getValue());
//                contractDataTable.refresh();
//                AppManager.getContractDataDao().update(contractDataTable.getItems().get(cell.getIndex()));
//            });
//            return cell;
//        });
//        contractDataDateColumn.setCellFactory(CustomTableCell.forTableColumn(new CustomDateStringConverter(dateTimePattern)));
//        contractDataPaymentAmountColumn.setCellFactory(CustomTableCell.forTableColumn(new FloatStringConverter()));
//        contractDataCurrentPaymentColumn.setCellFactory(tc -> {
//            ComboBox<CurrentPayment> combo = new ComboBox<>();
//            combo.getItems().addAll(AppManager.getCurrentPaymentsDao().findAll());
//            TableCell<ContractData, CurrentPayment> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(CurrentPayment reason, boolean empty) {
//                    super.updateItem(reason, empty);
//                    if (empty) {
//                        setTooltip(null);
//                        setGraphic(null);
//                    } else {
//                        combo.setValue(reason);
//                        setTooltip(new Tooltip(combo.getValue().toString()));
//                        setGraphic(combo);
//                    }
//                }
//            };
//            combo.setOnAction(e -> {
//                contractDataTable.getItems().get(cell.getIndex()).setCurrentPayments(combo.getValue());
//                contractDataTable.refresh();
//                AppManager.getContractDataDao().update(contractDataTable.getItems().get(cell.getIndex()));
//            });
//            return cell;
//        });
//        contractDataStatusColumn.setCellFactory(tc -> {
//            ComboBox<Status> combo = new ComboBox<>();
//            combo.getItems().addAll(AppManager.getStatusesDao().findAll());
//            TableCell<ContractData, Status> cell = new TableCell<>() {
//                @Override
//                protected void updateItem(Status reason, boolean empty) {
//                    super.updateItem(reason, empty);
//                    if (empty) {
//                        setTooltip(null);
//                        setGraphic(null);
//                    } else {
//                        combo.setValue(reason);
//                        setTooltip(new Tooltip(combo.getValue().toString()));
//                        setGraphic(combo);
//                    }
//                }
//            };
//            combo.setOnAction(e -> {
//                contractDataTable.getItems().get(cell.getIndex()).setStatuses(combo.getValue());
//                contractDataTable.refresh();
//                AppManager.getContractDataDao().update(contractDataTable.getItems().get(cell.getIndex()));
//            });
//            return cell;
//        });
//        contractDataSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
//                    onParentsSearch();
//                }
//            }
//        });
//    }

    private void initSemesterPerformanceTable() {
        semesterPerformanceTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        semesterPerformanceStudentComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getStudentDao().findAll()));
        semesterPerformanceStudentComboBox.getSelectionModel().select(0);
        semesterPerformanceStudentColumn.setCellFactory(tc -> {
            ComboBox<Student> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getStudentDao().findAll());
            TableCell<SemesterPerformance, Student> cell = new TableCell<>() {
                @Override
                protected void updateItem(Student reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setTooltip(null);
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setTooltip(new Tooltip(combo.getValue().toString()));
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                semesterPerformanceTable.getItems().get(cell.getIndex()).setStudent(combo.getValue());
                semesterPerformanceTable.refresh();
                AppManager.getSemesterPerformanceDao().update(semesterPerformanceTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        semesterPerformanceCourseColumn.setCellFactory(CustomTableCell.forTableColumn(new LimitationIntegerConverter(1, 4)));
        semesterPerformanceSemesterColumn.setCellFactory(CustomTableCell.forTableColumn(new LimitationIntegerConverter(1, 8)));
        semesterPerformanceDisciplineColumn.setCellFactory(tc -> {
            ComboBox<Discipline> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getDisciplinesDao().findAll());
            TableCell<SemesterPerformance, Discipline> cell = new TableCell<>() {
                @Override
                protected void updateItem(Discipline reason, boolean empty) {
                    super.updateItem(reason, empty);
                    if (empty) {
                        setTooltip(null);
                        setGraphic(null);
                    } else {
                        combo.setValue(reason);
                        setTooltip(new Tooltip(combo.getValue().toString()));
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                semesterPerformanceTable.getItems().get(cell.getIndex()).setDiscipline(combo.getValue());
                semesterPerformanceTable.refresh();
                AppManager.getSemesterPerformanceDao().update(semesterPerformanceTable.getItems().get(cell.getIndex()));
            });
            return cell;
        });
        semesterPerformanceMarkColumn.setCellFactory(CustomTableCell.forTableColumn(new LimitationIntegerConverter(0, 100)));
        semesterPerformanceSemesterField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onSemesterPerformanceSearch();
                }
            }
        });
    }

    private void initAddressButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(addressCityField.textProperty(),
                        addressStreetField.textProperty(),
                        addressHouseNumberField.textProperty(),
                        addressApartmentNumberField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (addressCityField.getText().isEmpty()
                        || addressStreetField.getText().isEmpty()
                        || addressHouseNumberField.getText().isEmpty()
                        || !TypesUtils.isInteger(addressApartmentNumberField.getText()));
            }
        };

        addressesAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(addressesTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (addressesTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        addressesDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initBasisOfEducationButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(basisOfEducationNameField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (basisOfEducationNameField.getText().isEmpty());
            }
        };

        basisOfEducationAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(basisOfEducationTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (basisOfEducationTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        basisOfEducationDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initFormsOfEducationButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(formsOfEducationNameField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (formsOfEducationNameField.getText().isEmpty());
            }
        };

        formsOfEducationAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(formsOfEducationTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (formsOfEducationTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        formsOfEducationDeleteButton.disableProperty().bind(deleteBind);
    }

//    private void initStatusesButtons() {
//        BooleanBinding addBind = new BooleanBinding() {
//            {
//                super.bind(statusesNameField.textProperty());
//            }
//
//            @Override
//            protected boolean computeValue() {
//                return (statusesNameField.getText().isEmpty());
//            }
//        };
//
//        statusesAddButton.disableProperty().bind(addBind);
//
//        BooleanBinding deleteBind = new BooleanBinding() {
//            {
//                super.bind(statusesTable.getSelectionModel().getSelectedItems());
//            }
//
//            @Override
//            protected boolean computeValue() {
//                return (statusesTable.getSelectionModel().getSelectedItems().isEmpty());
//            }
//        };
//
//        statusesDeleteButton.disableProperty().bind(deleteBind);
//    }

    private void initTypesOfMarksButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(typesOfMarksNameField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (typesOfMarksNameField.getText().isEmpty());
            }
        };

        typesOfMarksAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(typesOfMarksTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (typesOfMarksTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        typesOfMarksDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initDisciplinesButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(disciplinesNameField.textProperty(),
                            disciplinesTypeOfMarkNameField.textProperty(),
                            disciplineTypeOfMarkComboBox.valueProperty(),
                            disciplineTypeOfMarkGroup.selectedToggleProperty());
            }

            @Override
            protected boolean computeValue() {
                return disciplinesNameField.getText().isEmpty()
                        || (!(disciplineTypeOfMarkNewRadioButton.isSelected()
                            && !disciplinesTypeOfMarkNameField.getText().isEmpty())
                            && (!disciplineTypeOfMarkExitsRadioButton.isSelected()
                            || disciplineTypeOfMarkComboBox.getSelectionModel().isEmpty()));
            }
        };

        disciplinesAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(disciplinesTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (disciplinesTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        disciplinesDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initSpecializationsButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(specializationsNumberField.textProperty(),
                        specializationsNameField.textProperty(),
                        specializationsStudyDurationField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (specializationsNumberField.getText().isEmpty()
                        || specializationsNameField.getText().isEmpty()
                        || !TypesUtils.isInteger(specializationsStudyDurationField.getText()));
            }
        };

        specializationsAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(specializationsTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (specializationsTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        specializationsDeleteButton.disableProperty().bind(deleteBind);
    }

//    private void initCurrentPaymentsButtons() {
//        BooleanBinding addBind = new BooleanBinding() {
//            {
//                super.bind(currentPaymentsAmountField.textProperty(),
//                        currentPaymentsDateField.textProperty(),
//                        currentPaymentsDocNumberField.textProperty());
//            }
//
//            @Override
//            protected boolean computeValue() {
//                return (!TypesUtils.isFloat(currentPaymentsAmountField.getText())
//                        || !TypesUtils.isTimestamp(currentPaymentsDateField.getText())
//                        || !TypesUtils.isInteger(currentPaymentsDocNumberField.getText()));
//            }
//        };
//
//        currentPaymentsAddButton.disableProperty().bind(addBind);
//
//        BooleanBinding deleteBind = new BooleanBinding() {
//            {
//                super.bind(currentPaymentsTable.getSelectionModel().getSelectedItems());
//            }
//
//            @Override
//            protected boolean computeValue() {
//                return (currentPaymentsTable.getSelectionModel().getSelectedItems().isEmpty());
//            }
//        };
//
//        currentPaymentsDeleteButton.disableProperty().bind(deleteBind);
//    }

    private void initGroupsButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(groupsNameField.textProperty(),
                        groupSpecializationGroup.selectedToggleProperty(),
                        groupSpecializationComboBox.valueProperty(),
                        groupSpecializationsNumberField.textProperty(),
                        groupSpecializationsNameField.textProperty(),
                        groupSpecializationsStudyDurationField.textProperty(),
                        groupDateFormationField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return groupsNameField.getText().isEmpty()
                        || (!(groupSpecializationNewRadioButton.isSelected()
                            && !groupSpecializationsNumberField.getText().isEmpty()
                            && !groupSpecializationsNameField.getText().isEmpty()
                            && TypesUtils.isInteger(groupSpecializationsStudyDurationField.getText()))
                            && (!groupSpecializationExitsRadioButton.isSelected()
                            || groupSpecializationComboBox.getSelectionModel().isEmpty()))
                        || !TypesUtils.isDate(groupDateFormationField.getText());
            }
        };

        groupsAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(groupsTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (groupsTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        groupsDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initStudentsButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(studentLastNameField.textProperty(),
                        studentFirstNameField.textProperty(),
                        studentPatronymicField.textProperty(),
                        studentAddressCityField.textProperty(),
                        studentAddressStreetField.textProperty(),
                        studentAddressHouseNumberField.textProperty(),
                        studentAddressApartmentNumberField.textProperty(),
                        studentDateOfBirthField.textProperty(),
                        studentGroupNameField.textProperty(),
                        studentSpecializationNumberField.textProperty(),
                        studentSpecializationNameField.textProperty(),
                        studentFormOfEducationNameField.textProperty(),
                        studentBasisOfEducationNameField.textProperty(),
                        studentDateAdmissionField.textProperty(),
                        studentSpecializationStudyDurationField.textProperty(),
                        studentGroupDateFormationField.textProperty(),
                        studentAddressComboBox.valueProperty(),
                        studentGroupComboBox.valueProperty(),
                        studentSpecializationComboBox.valueProperty(),
                        studentFormOfEducationComboBox.valueProperty(),
                        studentBasisOfEducationComboBox.valueProperty(),
                        studentAddressGroup.selectedToggleProperty(),
                        studentGroupGroup.selectedToggleProperty(),
                        studentSpecializationGroup.selectedToggleProperty(),
                        studentFormOfEducationGroup.selectedToggleProperty(),
                        studentBasisOfEducationGroup.selectedToggleProperty());
            }

            private boolean computeStudentAddress() {
                return (!(studentAddressNewRadioButton.isSelected()
                        && !studentAddressCityField.getText().isEmpty()
                        && !studentAddressStreetField.getText().isEmpty()
                        && !studentAddressHouseNumberField.getText().isEmpty()
                        && TypesUtils.isInteger(studentAddressApartmentNumberField.getText()))
                        && !studentAddressExitsRadioButton.isSelected());
            }

            private boolean computeStudentGroup() {
                return (!(studentGroupNewRadioButton.isSelected()
                        && !studentGroupNameField.getText().isEmpty()
                        && !computeStudentSpecialization()
                        && TypesUtils.isDate(studentGroupDateFormationField.getText()))
                        && !studentGroupExitsRadioButton.isSelected());
            }

            private boolean computeStudentSpecialization() {
                return (!(studentSpecializationNewRadioButton.isSelected()
                        && !studentSpecializationNumberField.getText().isEmpty()
                        && !studentSpecializationNameField.getText().isEmpty()
                        && TypesUtils.isInteger(studentSpecializationStudyDurationField.getText()))
                        && !studentSpecializationExitsRadioButton.isSelected());
            }

            private boolean computeStudentFormOfEducation() {
                return (!(studentFormOfEducationNewRadioButton.isSelected()
                        && !studentFormOfEducationNameField.getText().isEmpty())
                        && !studentFormOfEducationExitsRadioButton.isSelected());
            }

            private boolean computeStudentBasisOfEducation() {
                return (!(studentBasisOfEducationNewRadioButton.isSelected()
                        && !studentBasisOfEducationNameField.getText().isEmpty())
                        && !studentBasisOfEducationExitsRadioButton.isSelected());
            }

            @Override
            protected boolean computeValue() {
                return studentLastNameField.getText().isEmpty()
                        || studentFirstNameField.getText().isEmpty()
                        || studentPatronymicField.getText().isEmpty()
                        || !TypesUtils.isDate(studentDateOfBirthField.getText())
                        || computeStudentAddress()
                        || computeStudentGroup()
                        || computeStudentFormOfEducation()
                        || computeStudentBasisOfEducation()
                        || !TypesUtils.isDate(studentDateAdmissionField.getText());
            }
        };

        studentsAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(studentsTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (studentsTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        studentsDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initParentsButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(parentLastNameField.textProperty(),
                        parentFirstNameField.textProperty(),
                        parentPatronymicField.textProperty(),
                        parentAddressCityField.textProperty(),
                        parentAddressStreetField.textProperty(),
                        parentAddressHouseNumberField.textProperty(),
                        parentAddressApartmentNumberField.textProperty(),
                        parentAddressComboBox.valueProperty(),
                        parentStudentComboBox.valueProperty(),
                        parentAddressGroup.selectedToggleProperty());
            }

            @Override
            protected boolean computeValue() {
                return parentLastNameField.getText().isEmpty()
                        || parentFirstNameField.getText().isEmpty()
                        || parentPatronymicField.getText().isEmpty()
                        || (!(parentAddressNewRadioButton.isSelected()
                            && !parentAddressCityField.getText().isEmpty()
                            && !parentAddressStreetField.getText().isEmpty()
                            && !parentAddressHouseNumberField.getText().isEmpty()
                            && TypesUtils.isInteger(parentAddressApartmentNumberField.getText()))
                            && !parentAddressExitsRadioButton.isSelected())
                        || parentStudentComboBox.getSelectionModel().isEmpty();
            }
        };

        parentsAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(parentsTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (parentsTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        parentsDeleteButton.disableProperty().bind(deleteBind);
    }

//    private void initContractDataButtons() {
//        BooleanBinding addBind = new BooleanBinding() {
//            {
//                super.bind(contractDataDateField.textProperty(),
//                        contractDataPaymentAmountField.textProperty(),
//                        contractDataCurrentPaymentAmountField.textProperty(),
//                        contractDataCurrentPaymentDateField.textProperty(),
//                        contractDataCurrentPaymentDocNumberField.textProperty(),
//                        contractDataStatusNameField.textProperty(),
//                        contractDataCurrentPaymentComboBox.valueProperty(),
//                        contractDataStatusComboBox.valueProperty(),
//                        contractDataCurrentPaymentGroup.selectedToggleProperty(),
//                        contractDataStatusGroup.selectedToggleProperty());
//            }
//
//            @Override
//            protected boolean computeValue() {
//                return !TypesUtils.isTimestamp(contractDataDateField.getText())
//                        || !TypesUtils.isFloat(contractDataPaymentAmountField.getText())
//                        || (!(contractDataCurrentPaymentNewRadioButton.isSelected()
//                            && TypesUtils.isFloat(contractDataCurrentPaymentAmountField.getText())
//                            && TypesUtils.isTimestamp(contractDataCurrentPaymentDateField.getText())
//                            && TypesUtils.isInteger(contractDataCurrentPaymentDocNumberField.getText()))
//                            && !contractDataCurrentPaymentExitsRadioButton.isSelected())
//                        || (!(contractDataStatusNewRadioButton.isSelected()
//                            && !contractDataStatusNameField.getText().isEmpty())
//                            && !contractDataStatusExitsRadioButton.isSelected());
//            }
//        };
//
//        contractDataAddButton.disableProperty().bind(addBind);
//
//        BooleanBinding deleteBind = new BooleanBinding() {
//            {
//                super.bind(contractDataTable.getSelectionModel().getSelectedItems());
//            }
//
//            @Override
//            protected boolean computeValue() {
//                return (contractDataTable.getSelectionModel().getSelectedItems().isEmpty());
//            }
//        };
//
//        contractDataDeleteButton.disableProperty().bind(deleteBind);
//    }

    private void initSemesterPerformanceButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(semesterPerformanceCourseField.textProperty(),
                        semesterPerformanceSemesterField.textProperty(),
                        semesterPerformanceDisciplineNameField.textProperty(),
                        semesterPerformanceMarkField.textProperty(),
                        semesterPerformanceStudentComboBox.valueProperty(),
                        semesterPerformanceDisciplineComboBox.valueProperty(),
                        semesterPerformanceDisciplineTypeOfMarkComboBox.valueProperty(),
                        semesterPerformanceDisciplineGroup.selectedToggleProperty(),
                        semesterPerformanceDisciplineTypeOfMarkGroup.selectedToggleProperty());
            }

            private boolean computeSemesterPerformanceDisciplineTypeOfMarks() {
                return (!(semesterPerformanceDisciplineTypeOfMarkNewRadioButton.isSelected()
                        && !semesterPerformanceDisciplineTypeOfMarkNameField.getText().isEmpty())
                        && (!semesterPerformanceDisciplineTypeOfMarkExitsRadioButton.isSelected()
                        || semesterPerformanceDisciplineTypeOfMarkComboBox.getSelectionModel().isEmpty()));
            }

            @Override
            protected boolean computeValue() {
                return semesterPerformanceStudentComboBox.getSelectionModel().isEmpty()
                        || !TypesUtils.isCourse(semesterPerformanceCourseField.getText())
                        || !TypesUtils.isSemester(semesterPerformanceSemesterField.getText())
                        || (!(semesterPerformanceDisciplineNewRadioButton.isSelected()
                            && !semesterPerformanceDisciplineNameField.getText().isEmpty()
                            && !computeSemesterPerformanceDisciplineTypeOfMarks())
                            && (!semesterPerformanceDisciplineExitsRadioButton.isSelected()
                            || semesterPerformanceDisciplineComboBox.getSelectionModel().isEmpty()))
                        || !TypesUtils.isInteger(semesterPerformanceMarkField.getText());
            }
        };

        semesterPerformanceAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(semesterPerformanceTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return (semesterPerformanceTable.getSelectionModel().getSelectedItems().isEmpty());
            }
        };

        semesterPerformanceDeleteButton.disableProperty().bind(deleteBind);
    }
}