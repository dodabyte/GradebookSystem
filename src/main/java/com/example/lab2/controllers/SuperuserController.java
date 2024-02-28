package com.example.lab2.controllers;

import com.example.lab2.AppManager;
import com.example.lab2.Main;
import com.example.lab2.alerts.AlertLoader;
import com.example.lab2.cells.CustomTextFieldTableCell;
import com.example.lab2.converters.*;
import com.example.lab2.controls.DateTextField;
import com.example.lab2.objects.main.*;
import com.example.lab2.objects.references.SpecializationDiscipline;
import com.example.lab2.objects.references.TeacherDiscipline;
import com.example.lab2.objects.references.TeacherGroup;
import com.example.lab2.predicates.CustomPredicate;
import com.example.lab2.utils.DateUtils;
import com.example.lab2.utils.MarksUtils;
import com.example.lab2.utils.AuthUtils;
import com.example.lab2.utils.TypesUtils;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SuperuserController implements Initializable {
    @FXML private Button addressesAddButton;
    @FXML private Button addressesDeleteButton;
    @FXML private Button disciplinesAddButton;
    @FXML private Button disciplinesDeleteButton;
    @FXML private Button specializationsAddButton;
    @FXML private Button specializationsDeleteButton;
    @FXML private Button groupsAddButton;
    @FXML private Button groupsDeleteButton;
    @FXML private Button studentsAddButton;
    @FXML private Button studentsDeleteButton;
    @FXML private Button parentsAddButton;
    @FXML private Button parentsDeleteButton;
    @FXML private Button semesterPerformanceAddButton;
    @FXML private Button semesterPerformanceDeleteButton;
    @FXML private Button departmentsAddButton;
    @FXML private Button departmentsDeleteButton;
    @FXML private Button postsAddButton;
    @FXML private Button postsDeleteButton;
    @FXML private Button teacherDisciplineAddButton;
    @FXML private Button teacherDisciplineDeleteButton;
    @FXML private Button teacherGroupAddButton;
    @FXML private Button teacherGroupDeleteButton;
    @FXML private Button teachersAddButton;
    @FXML private Button teachersDeleteButton;
    @FXML private Button specializationDisciplineAddButton;
    @FXML private Button specializationDisciplineDeleteButton;

    @FXML private TextField addressApartmentNumberField;
    @FXML private TextField addressCityField;
    @FXML private TextField addressHouseNumberField;
    @FXML private TextField addressStreetField;
    @FXML private TextField disciplinesNameField;
    @FXML private TextField specializationsNumberField;
    @FXML private TextField specializationsNameField;
    @FXML private TextField specializationsStudyDurationField;
    @FXML private TextField groupsNameField;
    @FXML private TextField groupSpecializationsNumberField;
    @FXML private TextField groupSpecializationsNameField;
    @FXML private TextField groupSpecializationsStudyDurationField;
    @FXML private DateTextField groupDateFormationField;
    @FXML private TextField studentLastNameField;
    @FXML private TextField studentFirstNameField;
    @FXML private TextField studentPatronymicField;
    @FXML private TextField studentAddressCityField;
    @FXML private TextField studentAddressStreetField;
    @FXML private TextField studentAddressHouseNumberField;
    @FXML private TextField studentAddressApartmentNumberField;
    @FXML private DateTextField studentDateOfBirthField;
    @FXML private TextField studentGroupNameField;
    @FXML private DateTextField studentGroupDateFormationField;
    @FXML private TextField studentSpecializationNumberField;
    @FXML private TextField studentSpecializationNameField;
    @FXML private TextField studentSpecializationStudyDurationField;
    @FXML private DateTextField studentDateAdmissionField;
    @FXML private TextField parentLastNameField;
    @FXML private TextField parentFirstNameField;
    @FXML private TextField parentPatronymicField;
    @FXML private TextField parentAddressCityField;
    @FXML private TextField parentAddressStreetField;
    @FXML private TextField parentAddressHouseNumberField;
    @FXML private TextField parentAddressApartmentNumberField;
    @FXML private TextField semesterPerformanceCourseField;
    @FXML private TextField semesterPerformanceSemesterField;
    @FXML private TextField semesterPerformanceDisciplineNameField;
    @FXML private TextField semesterPerformanceMarkField;
    @FXML private TextField departmentsNameField;
    @FXML private TextField postsNameField;
    @FXML private TextField teacherLastNameField;
    @FXML private TextField teacherFirstNameField;
    @FXML private TextField teacherPatronymicField;
    @FXML private DateTextField teacherDateOfBirthField;
    @FXML private TextField teacherDepartmentNameField;
    @FXML private TextField teacherPostNameField;
    @FXML private TextField teacherAddressCityField;
    @FXML private TextField teacherAddressStreetField;
    @FXML private TextField teacherAddressHouseNumberField;
    @FXML private TextField teacherAddressApartmentNumberField;
    @FXML private TextField basisOfEducationSearchField;
    @FXML private TextField formsOfEducationSearchField;
    @FXML private TextField typesOfMarksSearchField;
    @FXML private TextField disciplinesSearchField;
    @FXML private TextField specializationsSearchField;
    @FXML private TextField groupsSearchField;
    @FXML private TextField addressesSearchField;
    @FXML private TextField parentsSearchField;
    @FXML private TextField semesterPerformanceSearchField;
    @FXML private TextField studentsSearchField;
    @FXML private TextField departmentsSearchField;
    @FXML private TextField postsSearchField;
    @FXML private TextField teacherDisciplineSearchField;
    @FXML private TextField teacherGroupSearchField;
    @FXML private TextField teachersSearchField;
    @FXML private TextField specializationDisciplineSearchField;
    @FXML private TextField teacherGroupSemesterField;

    @FXML private RadioButton groupSpecializationExitsRadioButton;
    @FXML private RadioButton groupSpecializationNewRadioButton;
    @FXML private RadioButton studentAddressExitsRadioButton;
    @FXML private RadioButton studentAddressNewRadioButton;
    @FXML private RadioButton studentGroupExitsRadioButton;
    @FXML private RadioButton studentGroupNewRadioButton;
    @FXML private RadioButton studentSpecializationExitsRadioButton;
    @FXML private RadioButton studentSpecializationNewRadioButton;
    @FXML private RadioButton parentAddressExitsRadioButton;
    @FXML private RadioButton parentAddressNewRadioButton;
    @FXML private RadioButton semesterPerformanceDisciplineExitsRadioButton;
    @FXML private RadioButton semesterPerformanceDisciplineNewRadioButton;
    @FXML private RadioButton teacherDepartmentExitsRadioButton;
    @FXML private RadioButton teacherDepartmentNewRadioButton;
    @FXML private RadioButton teacherPostExitsRadioButton;
    @FXML private RadioButton teacherPostNewRadioButton;
    @FXML private RadioButton teacherAddressExitsRadioButton;
    @FXML private RadioButton teacherAddressNewRadioButton;

    @FXML private ComboBox<Specialization> groupSpecializationComboBox;
    @FXML private ComboBox<Address> studentAddressComboBox;
    @FXML private ComboBox<Group> studentGroupComboBox;
    @FXML private ComboBox<Specialization> studentSpecializationComboBox;
    @FXML private ComboBox<FormOfEducation> studentFormOfEducationComboBox;
    @FXML private ComboBox<BasisOfEducation> studentBasisOfEducationComboBox;
    @FXML private ComboBox<Address> parentAddressComboBox;
    @FXML private ComboBox<Student> parentStudentComboBox;
    @FXML private ComboBox<Student> semesterPerformanceStudentComboBox;
    @FXML private ComboBox<Discipline> semesterPerformanceDisciplineComboBox;
    @FXML private ComboBox<TypeOfMark> semesterPerformanceDisciplineTypeOfMarkComboBox;
    @FXML private ComboBox<TypeOfMark> disciplineTypeOfMarkComboBox;
    @FXML private ComboBox<Teacher> teacherDisciplineTeacherComboBox;
    @FXML private ComboBox<Discipline> teacherDisciplineDisciplineComboBox;
    @FXML private ComboBox<Teacher> teacherGroupTeacherComboBox;
    @FXML private ComboBox<Discipline> teacherGroupDisciplineComboBox;
    @FXML private ComboBox<Group> teacherGroupGroupComboBox;
    @FXML private ComboBox<Department> teacherDepartmentComboBox;
    @FXML private ComboBox<Post> teacherPostComboBox;
    @FXML private ComboBox<Address> teacherAddressComboBox;
    @FXML private ComboBox<Specialization> specializationDisciplineSpecializationComboBox;
    @FXML private ComboBox<Discipline> specializationDisciplineDisciplineComboBox;

    @FXML private TableView<Address> addressesTable;
    @FXML private TableView<BasisOfEducation> basisOfEducationTable;
    @FXML private TableView<Discipline> disciplinesTable;
    @FXML private TableView<FormOfEducation> formsOfEducationTable;
    @FXML private TableView<TypeOfMark> typesOfMarksTable;
    @FXML private TableView<Specialization> specializationsTable;
    @FXML private TableView<Group> groupsTable;
    @FXML private TableView<Student> studentsTable;
    @FXML private TableView<Parent> parentsTable;
    @FXML private TableView<SemesterPerformance> semesterPerformanceTable;
    @FXML private TableView<Department> departmentsTable;
    @FXML private TableView<Post> postsTable;
    @FXML private TableView<TeacherDiscipline> teacherDisciplineTable;
    @FXML private TableView<TeacherGroup> teacherGroupTable;
    @FXML private TableView<Teacher> teachersTable;
    @FXML private TableView<SpecializationDiscipline> specializationDisciplineTable;

    @FXML protected TableColumn<Address, Integer> apartmentNumberColumn;
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
    @FXML protected TableColumn<SemesterPerformance, Student> semesterPerformanceStudentColumn;
    @FXML protected TableColumn<SemesterPerformance, Integer> semesterPerformanceCourseColumn;
    @FXML protected TableColumn<SemesterPerformance, Integer> semesterPerformanceSemesterColumn;
    @FXML protected TableColumn<SemesterPerformance, Discipline> semesterPerformanceDisciplineColumn;
    @FXML protected TableColumn<SemesterPerformance, Integer> semesterPerformanceMarkColumn;
    @FXML protected TableColumn<Discipline, TypeOfMark> disciplineTypeOfMarkColumn;
    @FXML protected TableColumn<Specialization, Integer> studyDurationColumn;
    @FXML protected TableColumn<TeacherDiscipline, Teacher> teacherDisciplineTeacherColumn;
    @FXML protected TableColumn<TeacherDiscipline, Discipline> teacherDisciplineDisciplineColumn;
    @FXML protected TableColumn<TeacherGroup, TeacherDiscipline> teacherGroupTeacherDisciplineColumn;
    @FXML protected TableColumn<TeacherGroup, Group> teacherGroupGroupColumn;
    @FXML protected TableColumn<TeacherGroup, Integer> teacherGroupSemesterColumn;
    @FXML protected TableColumn<Teacher, Date> teacherDateOfBirthColumn;
    @FXML protected TableColumn<Teacher, Department> teacherDepartmentColumn;
    @FXML protected TableColumn<Teacher, Post> teacherPostColumn;
    @FXML protected TableColumn<Teacher, Address> teacherAddressColumn;
    @FXML protected TableColumn<SpecializationDiscipline, Specialization> specializationDisciplineSpecializationColumn;
    @FXML protected TableColumn<SpecializationDiscipline, Discipline> specializationDisciplineDisciplineColumn;

    @FXML private RadioMenuItem advancedMode;

    @FXML private TabPane tabPane;

    @FXML private Tab studentsTab;
    @FXML private Tab addressesTab;
    @FXML private Tab basisOfEducationTab;
    @FXML private Tab disciplinesTab;
    @FXML private Tab formsOfEducationTab;
    @FXML private Tab typesOfMarksTab;
    @FXML private Tab specializationsTab;
    @FXML private Tab groupsTab;
    @FXML private Tab parentsTab;
    @FXML private Tab semesterPerformanceTab;
    @FXML private Tab departmentsTab;
    @FXML private Tab postsTab;
    @FXML private Tab teacherDisciplineTab;
    @FXML private Tab teacherGroupTab;
    @FXML private Tab teachersTab;
    @FXML private Tab specializationDisciplineTab;

    private Stage aboutProgramStage;
    private Stage changePasswordStage;

    private ToggleGroup groupSpecializationGroup = new ToggleGroup();
    private ToggleGroup studentAddressGroup = new ToggleGroup();
    private ToggleGroup studentGroupGroup = new ToggleGroup();
    private ToggleGroup studentSpecializationGroup = new ToggleGroup();
    private ToggleGroup parentAddressGroup = new ToggleGroup();
    private ToggleGroup semesterPerformanceDisciplineGroup = new ToggleGroup();
    private ToggleGroup teacherDepartmentGroup = new ToggleGroup();
    private ToggleGroup teacherPostGroup = new ToggleGroup();
    private ToggleGroup teacherAddressGroup = new ToggleGroup();

    @FXML
    protected void onAddressesRefreshButton() {
        addressesTable.setItems(FXCollections.observableArrayList(AppManager.getAddressesDao().findAll()));
        addressesSearchField.setText("");
    }

    @FXML
    protected void onAddressesAddButton() {
        Address address = new Address(addressCityField.getText(),
                addressStreetField.getText(),
                addressHouseNumberField.getText(),
                Integer.parseInt(addressApartmentNumberField.getText()));

        if (AppManager.getAddressesDao().contains(address)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else {
            AppManager.getAddressesDao().insert(address);
            addressCityField.setText("");
            addressStreetField.setText("");
            addressHouseNumberField.setText("");
            addressApartmentNumberField.setText("");
            onAddressesRefreshButton();
            addressesComboBoxUpdate();
        }
    }

    @FXML
    protected void onAddressesDeleteButton() {
        try {
            AppManager.getAddressesDao().delete(addressesTable.getSelectionModel().getSelectedItem());
            onAddressesRefreshButton();
            addressesComboBoxUpdate();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    @FXML
    protected void onAddressesSearch() {
        if (!addressesSearchField.getText().isEmpty()) {
            addressesTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getAddressesDao().findAll()).filtered(
                            new CustomPredicate<Address>().createPredicate(addressesSearchField.getText()))));
        }
    }

    private void addressesComboBoxUpdate() {
        teacherAddressComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getAddressesDao().findAll()));;
        parentAddressComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getAddressesDao().findAll()));;
        studentAddressComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getAddressesDao().findAll()));
    }

    @FXML
    protected void onAddressesSearchClear() {
        addressesSearchField.setText("");
        onAddressesRefreshButton();
    }
    @FXML
    protected void onBasisOfEducationRefreshButton() {
        basisOfEducationTable.setItems(FXCollections.observableArrayList(AppManager.getBasisOfEducationDao().findAll()));
        basisOfEducationSearchField.setText("");
    }

    @FXML
    protected void onBasisOfEducationSearch() {
        if (!basisOfEducationSearchField.getText().isEmpty()) {
            basisOfEducationTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getBasisOfEducationDao().findAll()).filtered(
                            new CustomPredicate<BasisOfEducation>().createPredicate(basisOfEducationSearchField.getText()))));
        }
    }

    @FXML
    protected void onBasisOfEducationSearchClear() {
        basisOfEducationSearchField.setText("");
        onBasisOfEducationRefreshButton();
    }

    @FXML
    protected void onFormsOfEducationRefreshButton() {
        formsOfEducationTable.setItems(FXCollections.observableArrayList(AppManager.getFormsOfEducationDao().findAll()));
        formsOfEducationSearchField.setText("");
    }

    @FXML
    protected void onFormsOfEducationSearch() {
        if (!formsOfEducationSearchField.getText().isEmpty()) {
            formsOfEducationTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getFormsOfEducationDao().findAll()).filtered(
                            new CustomPredicate<FormOfEducation>().createPredicate(formsOfEducationSearchField.getText()))));
        }
    }

    @FXML
    protected void onFormsOfEducationSearchClear() {
        formsOfEducationSearchField.setText("");
        onFormsOfEducationRefreshButton();
    }

    @FXML
    protected void onTypesOfMarksRefreshButton() {
        typesOfMarksTable.setItems(FXCollections.observableArrayList(AppManager.getTypesOfMarksDao().findAll()));
        typesOfMarksSearchField.setText("");
    }

    @FXML
    protected void onTypesOfMarksSearch() {
        if (!typesOfMarksSearchField.getText().isEmpty()) {
            typesOfMarksTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getTypesOfMarksDao().findAll()).filtered(
                            new CustomPredicate<TypeOfMark>().createPredicate(typesOfMarksSearchField.getText()))));
        }
    }

    @FXML
    protected void onTypesOfMarksSearchClear() {
        typesOfMarksSearchField.setText("");
        onTypesOfMarksRefreshButton();
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

        if (!disciplineTypeOfMarkComboBox.getSelectionModel().isEmpty()) {
            discipline.setTypeOfMark(disciplineTypeOfMarkComboBox.getValue());
        }

        if (AppManager.getDisciplinesDao().contains(discipline)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else {
            AppManager.getDisciplinesDao().insert(discipline);
            disciplinesNameField.setText("");
            disciplineTypeOfMarkComboBox.setValue(null);
            onDisciplinesRefreshButton();
            disciplinesComboBoxUpdate();
        }
    }

    @FXML
    protected void onDisciplinesDeleteButton() {
        try {
            AppManager.getDisciplinesDao().delete(disciplinesTable.getSelectionModel().getSelectedItem());
            onDisciplinesRefreshButton();
            disciplinesComboBoxUpdate();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    private void disciplinesComboBoxUpdate() {
        semesterPerformanceDisciplineComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getDisciplinesDao().findAll()));
        specializationDisciplineDisciplineComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getDisciplinesDao().findAll()));
        teacherDisciplineDisciplineComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getDisciplinesDao().findAll()));
        teacherGroupDisciplineComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getDisciplinesDao().findAll()));
    }

    @FXML
    protected void onDisciplinesSearch() {
        if (!disciplinesSearchField.getText().isEmpty()) {
            disciplinesTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getDisciplinesDao().findAll()).filtered(
                            new CustomPredicate<Discipline>().createPredicate(disciplinesSearchField.getText()))));
        }
    }

    @FXML
    protected void onDisciplinesSearchClear() {
        disciplinesSearchField.setText("");
        onDisciplinesRefreshButton();
    }

    @FXML
    protected void onSpecializationsRefreshButton() {
        specializationsTable.setItems(FXCollections.observableArrayList(AppManager.getSpecializationsDao().findAll()));
        specializationsSearchField.setText("");
    }

    @FXML
    protected void onSpecializationsAddButton() {
        Specialization specialization = new Specialization(specializationsNumberField.getText(),
                specializationsNameField.getText(),
                Integer.parseInt(specializationsStudyDurationField.getText()));

        if (AppManager.getSpecializationsDao().contains(specialization)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else {
            AppManager.getSpecializationsDao().insert(specialization);
            specializationsNumberField.setText("");
            specializationsNameField.setText("");
            specializationsStudyDurationField.setText("");
            onSpecializationsRefreshButton();
            specializationsComboBoxUpdate();
        }
    }

    @FXML
    protected void onSpecializationsDeleteButton() {
        try {
            AppManager.getSpecializationsDao().delete(specializationsTable.getSelectionModel().getSelectedItem());
            onSpecializationsRefreshButton();
            specializationsComboBoxUpdate();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    private void specializationsComboBoxUpdate() {
        specializationDisciplineSpecializationComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getSpecializationsDao().findAll()));
        studentSpecializationComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getSpecializationsDao().findAll()));
        groupSpecializationComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getSpecializationsDao().findAll()));
    }

    @FXML
    protected void onSpecializationsSearch() {
        if (!specializationsSearchField.getText().isEmpty()) {
            specializationsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getSpecializationsDao().findAll()).filtered(
                            new CustomPredicate<Specialization>().createPredicate(specializationsSearchField.getText()))));
        }
    }

    @FXML
    protected void onSpecializationsSearchClear() {
        specializationsSearchField.setText("");
        onSpecializationsRefreshButton();
    }

    @FXML
    protected void onGroupsRefreshButton() {
        groupsTable.setItems(FXCollections.observableArrayList(AppManager.getGroupsDao().findAll()));
        groupsSearchField.setText("");
    }

    @FXML
    protected void onGroupsAddButton() {
        boolean canAdd = true;

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

            if (AppManager.getSpecializationsDao().contains(specialization)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                group.setSpecialization(specialization);
                AppManager.getSpecializationsDao().insert(specialization);
            }
        }

        group.setDateFormation(DateUtils.getDateFromString(groupDateFormationField.getText(), DateUtils.DATE_PATTERN));

        Date dateGraduation = new Date();
        dateGraduation.setSeconds(0);
        dateGraduation.setMinutes(0);
        dateGraduation.setHours(0);
        dateGraduation.setDate(1);
        dateGraduation.setMonth(6);
        dateGraduation.setYear(group.getDateFormation().getYear() + group.getSpecialization().getStudyDuration());
        group.setDateGraduation(dateGraduation);

        group.setCourse(DateUtils.getCurrentCourse(group.getDateFormation()));
        group.setSemester(DateUtils.getCurrentSemester(group.getDateFormation(), group.getCourse()));

        if (AppManager.getGroupsDao().contains(group)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else if (canAdd) {
            AppManager.getGroupsDao().insert(group);
            groupsNameField.setText("");
            groupSpecializationsNumberField.setText("");
            groupSpecializationsNameField.setText("");
            groupSpecializationsStudyDurationField.setText("");
            groupDateFormationField.setText("");
            groupSpecializationComboBox.setValue(null);
            groupSpecializationComboBox.setDisable(true);
            groupSpecializationGroup.selectToggle(null);
            onGroupsRefreshButton();
            groupsComboBoxUpdate();
        }
    }

    @FXML
    protected void onGroupsDeleteButton() {
        try {
            AppManager.getGroupsDao().delete(groupsTable.getSelectionModel().getSelectedItem());
            onGroupsRefreshButton();
            groupsComboBoxUpdate();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    private void groupsComboBoxUpdate() {
        studentGroupComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getGroupsDao().findAll()));
        teacherGroupGroupComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getGroupsDao().findAll()));
    }

    @FXML
    protected void onGroupsSearch() {
        if (!groupsSearchField.getText().isEmpty()) {
            groupsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getGroupsDao().findAll()).filtered(
                            new CustomPredicate<Group>().createPredicate(groupsSearchField.getText()))));
        }
    }

    @FXML
    protected void onGroupsSearchClear() {
        groupsSearchField.setText("");
        onGroupsRefreshButton();
    }

    @FXML
    protected void onStudentsRefreshButton() {
        studentsTable.setItems(FXCollections.observableArrayList(AppManager.getStudentDao().findAll()));
        studentsSearchField.setText("");
    }

    @FXML
    protected void onStudentsAddButton() {
        boolean canAdd = true;

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

            if (AppManager.getAddressesDao().contains(address)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                student.setAddress(address);
                AppManager.getAddressesDao().insert(address);
            }
        }

        student.setDateOfBirth(DateUtils.getDateFromString(studentDateOfBirthField.getText(), DateUtils.DATE_PATTERN));

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

                if (AppManager.getSpecializationsDao().contains(specialization)) {
                    AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                    canAdd = false;
                }
                else {
                    group.setSpecialization(specialization);
                    AppManager.getSpecializationsDao().insert(specialization);
                }
            }

            group.setDateFormation(DateUtils.getDateFromString(studentGroupDateFormationField.getText(), DateUtils.DATE_PATTERN));

            Date dateGraduation = new Date();
            dateGraduation.setDate(1);
            dateGraduation.setMonth(6);
            dateGraduation.setYear(group.getDateFormation().getYear() + group.getSpecialization().getStudyDuration());
            group.setDateGraduation(dateGraduation);

            group.setCourse(DateUtils.getCurrentCourse(group.getDateFormation()));
            group.setSemester(DateUtils.getCurrentSemester(group.getDateFormation(), group.getCourse()));

            if (AppManager.getGroupsDao().contains(group)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                student.setGroup(group);
                AppManager.getGroupsDao().insert(group);
            }
        }

        if (!studentFormOfEducationComboBox.getSelectionModel().isEmpty()) {
            student.setFormOfEducation(studentFormOfEducationComboBox.getValue());
        }

        if (!studentBasisOfEducationComboBox.getSelectionModel().isEmpty()) {
            student.setBasisOfEducation(studentBasisOfEducationComboBox.getValue());
        }

        student.setDateAdmission(DateUtils.getDateFromString(studentDateAdmissionField.getText(), DateUtils.DATE_PATTERN));

        if (AppManager.getStudentDao().contains(student)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else if (canAdd) {
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
            studentDateAdmissionField.setText("");
            studentAddressComboBox.setValue(null);
            studentGroupComboBox.setValue(null);
            studentSpecializationComboBox.setValue(null);
            studentFormOfEducationComboBox.setValue(null);
            studentBasisOfEducationComboBox.setValue(null);
            studentSpecializationComboBox.setDisable(true);
            studentFormOfEducationComboBox.setDisable(true);
            studentBasisOfEducationComboBox.setDisable(true);
            studentAddressGroup.selectToggle(null);
            studentGroupGroup.selectToggle(null);
            studentSpecializationGroup.selectToggle(null);
            onStudentsRefreshButton();
            studentsComboBoxUpdate();
        }
    }

    @FXML
    protected void onStudentsDeleteButton() {
        try {
            AppManager.getStudentDao().delete(studentsTable.getSelectionModel().getSelectedItem());
            onStudentsRefreshButton();
            studentsComboBoxUpdate();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    private void studentsComboBoxUpdate() {
        semesterPerformanceStudentComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getStudentDao().findAll()));
        parentStudentComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getStudentDao().findAll()));
    }

    @FXML
    protected void onStudentsSearch() {
        if (!studentsSearchField.getText().isEmpty()) {
            studentsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getStudentDao().findAll()).filtered(
                            new CustomPredicate<Student>().createPredicate(studentsSearchField.getText()))));
        }
    }

    @FXML
    protected void onStudentsSearchClear() {
        studentsSearchField.setText("");
        onStudentsRefreshButton();
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
        boolean canAdd = true;

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

            if (AppManager.getAddressesDao().contains(address)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                parent.setAddress(address);
                AppManager.getAddressesDao().insert(address);
            }
        }

        if (!parentStudentComboBox.getSelectionModel().isEmpty()) {
            parent.setStudent(parentStudentComboBox.getValue());
        }

        if (AppManager.getParentDao().contains(parent)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else if (canAdd) {
            AppManager.getParentDao().insert(parent);
            parentLastNameField.setText("");
            parentFirstNameField.setText("");
            parentPatronymicField.setText("");
            parentAddressCityField.setText("");
            parentAddressStreetField.setText("");
            parentAddressHouseNumberField.setText("");
            parentAddressApartmentNumberField.setText("");
            parentAddressComboBox.setValue(null);
            parentAddressComboBox.setDisable(true);
            parentStudentComboBox.setValue(null);
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
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    @FXML
    protected void onParentsSearch() {
        if (!parentsSearchField.getText().isEmpty()) {
            parentsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getParentDao().findAll()).filtered(
                            new CustomPredicate<Parent>().createPredicate(parentsSearchField.getText()))));
        }
    }

    @FXML
    protected void onParentsSearchClear() {
        parentsSearchField.setText("");
        onParentsRefreshButton();
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
        boolean canAdd = true;

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

            if (!semesterPerformanceDisciplineTypeOfMarkComboBox.getSelectionModel().isEmpty()) {
                discipline.setTypeOfMark(semesterPerformanceDisciplineTypeOfMarkComboBox.getValue());
            }

            if (AppManager.getDisciplinesDao().contains(discipline)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                semesterPerformance.setDiscipline(discipline);
                AppManager.getDisciplinesDao().insert(discipline);
            }
        }

        semesterPerformance.setMark(Integer.parseInt(semesterPerformanceMarkField.getText()));
        semesterPerformance.setEctsMark(MarksUtils.getEctsMark(semesterPerformance.getMark()));
        semesterPerformance.setTraditionalMark(MarksUtils.getTraditionalMark(semesterPerformance.getMark()));
        semesterPerformance.setTraditionalWordMark(MarksUtils.getTraditionalWordMark(semesterPerformance.getMark(),
                semesterPerformance.getDiscipline().getTypeOfMark().getName()));

        if (AppManager.getSemesterPerformanceDao().contains(semesterPerformance)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else if (canAdd) {
            AppManager.getSemesterPerformanceDao().insert(semesterPerformance);
            semesterPerformanceCourseField.setText("");
            semesterPerformanceSemesterField.setText("");
            semesterPerformanceDisciplineNameField.setText("");
            semesterPerformanceMarkField.setText("");
            semesterPerformanceStudentComboBox.setValue(null);
            semesterPerformanceDisciplineComboBox.setValue(null);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setValue(null);
            semesterPerformanceDisciplineComboBox.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setDisable(true);
            semesterPerformanceDisciplineGroup.selectToggle(null);
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
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    @FXML
    protected void onSemesterPerformanceSearch() {
        if (!semesterPerformanceSearchField.getText().isEmpty()) {
            semesterPerformanceTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getSemesterPerformanceDao().findAll()).filtered(
                            new CustomPredicate<SemesterPerformance>().createPredicate(semesterPerformanceSearchField.getText()))));
        }
    }

    @FXML
    protected void onSemesterPerformanceSearchClear() {
        semesterPerformanceSearchField.setText("");
        onSemesterPerformanceRefreshButton();
    }

    @FXML
    protected void onDepartmentsRefreshButton() {
        departmentsTable.setItems(FXCollections.observableArrayList(AppManager.getDepartmentsDao().findAll()));
        departmentsSearchField.setText("");
    }

    @FXML
    protected void onDepartmentsAddButton() {
        Department departments = new Department(departmentsNameField.getText());

        if (AppManager.getDepartmentsDao().contains(departments)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else {
            AppManager.getDepartmentsDao().insert(departments);
            departmentsNameField.setText("");
            onDepartmentsRefreshButton();
            departmentsComboBoxUpdate();
        }
    }

    @FXML
    protected void onDepartmentsDeleteButton() {
        try {
            AppManager.getDepartmentsDao().delete(departmentsTable.getSelectionModel().getSelectedItem());
            onDepartmentsRefreshButton();
            departmentsComboBoxUpdate();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    private void departmentsComboBoxUpdate() {
        teacherDepartmentComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getDepartmentsDao().findAll()));
    }

    @FXML
    protected void onDepartmentsSearch() {
        if (!departmentsSearchField.getText().isEmpty()) {
            departmentsTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                    AppManager.getDepartmentsDao().findAll()).filtered(
                            new CustomPredicate<Department>().createPredicate(departmentsSearchField.getText()))));
        }
    }

    @FXML
    protected void onDepartmentsSearchClear() {
        departmentsSearchField.setText("");
        onDepartmentsRefreshButton();
    }

    @FXML
    protected void onPostsRefreshButton() {
        postsTable.setItems(FXCollections.observableArrayList(AppManager.getPostsDao().findAll()));
        postsSearchField.setText("");
    }

    @FXML
    protected void onPostsAddButton() {
        Post posts = new Post(postsNameField.getText());

        if (AppManager.getPostsDao().contains(posts)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else {
            AppManager.getPostsDao().insert(posts);
            postsNameField.setText("");
            onPostsRefreshButton();
        }
    }

    @FXML
    protected void onPostsDeleteButton() {
        try {
            AppManager.getPostsDao().delete(postsTable.getSelectionModel().getSelectedItem());
            onPostsRefreshButton();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    private void postsComboBoxUpdate() {
        teacherPostComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getPostsDao().findAll()));
    }


    @FXML
    protected void onPostsSearch() {
        if (!postsSearchField.getText().isEmpty()) {
            postsTable.setItems(new FilteredList<>(
                        FXCollections.observableArrayList(
                        AppManager.getPostsDao().findAll()).filtered(
                                new CustomPredicate<Post>().createPredicate(postsSearchField.getText()))));
        }
    }

    @FXML
    protected void onPostsSearchClear() {
        postsSearchField.setText("");
        onPostsRefreshButton();
    }

    @FXML
    protected void onTeacherDisciplineRefreshButton() {
        teacherDisciplineTable.setItems(FXCollections.observableArrayList(AppManager.getTeacherDisciplineDao().findAll()));
        teacherDisciplineSearchField.setText("");
    }

    @FXML
    protected void onTeacherDisciplineAddButton() {
        TeacherDiscipline teacherDiscipline = new TeacherDiscipline();

        if (!teacherDisciplineTeacherComboBox.getSelectionModel().isEmpty()) {
            teacherDiscipline.setTeacher(teacherDisciplineTeacherComboBox.getValue());
        }

        if (!teacherDisciplineDisciplineComboBox.getSelectionModel().isEmpty()) {
            teacherDiscipline.setDiscipline(teacherDisciplineDisciplineComboBox.getValue());
        }

        if (AppManager.getTeacherDisciplineDao().contains(teacherDiscipline)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        } else {
            AppManager.getTeacherDisciplineDao().insert(teacherDiscipline);
            teacherDisciplineTeacherComboBox.setValue(null);
            teacherDisciplineDisciplineComboBox.setValue(null);
            onTeacherDisciplineRefreshButton();
        }
    }

    @FXML
    protected void onTeacherDisciplineDeleteButton() {
        try {
            AppManager.getTeacherDisciplineDao().delete(teacherDisciplineTable.getSelectionModel().getSelectedItem());
            onTeacherDisciplineRefreshButton();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    @FXML
    protected void onTeacherDisciplineSearch() {
        if (!teacherDisciplineSearchField.getText().isEmpty()) {
            teacherDisciplineTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getTeacherDisciplineDao().findAll()).filtered(
                            new CustomPredicate<TeacherDiscipline>().createPredicate(teacherDisciplineSearchField.getText()))));
        }
    }

    @FXML
    protected void onTeacherDisciplineSearchClear() {
        teacherDisciplineSearchField.setText("");
        onTeacherDisciplineRefreshButton();
    }
    @FXML
    protected void onTeacherGroupRefreshButton() {
        teacherGroupTable.setItems(FXCollections.observableArrayList(AppManager.getTeacherGroupDao().findAll()));
        teacherGroupSearchField.setText("");
    }

    @FXML
    protected void onTeacherGroupAddButton() {
        TeacherGroup teacherGroup = new TeacherGroup();

        if (!teacherGroupTeacherComboBox.getSelectionModel().isEmpty() &&
                !teacherGroupDisciplineComboBox.getSelectionModel().isEmpty()) {
            if (AppManager.getTeacherDisciplineDao().contains(teacherGroupTeacherComboBox.getValue(),
                    teacherGroupDisciplineComboBox.getValue())) {
                teacherGroup.setTeacherDiscipline(AppManager.getTeacherDisciplineDao().findByField(
                        teacherGroupTeacherComboBox.getValue(), teacherGroupDisciplineComboBox.getValue()));
            }
            else {
                TeacherDiscipline teacherDiscipline = new TeacherDiscipline(teacherGroupTeacherComboBox.getValue(),
                        teacherGroupDisciplineComboBox.getValue());
                AppManager.getTeacherDisciplineDao().insert(teacherDiscipline);
                teacherGroup.setTeacherDiscipline(teacherDiscipline);
            }
        }

        if (!teacherGroupGroupComboBox.getSelectionModel().isEmpty()) {
            teacherGroup.setGroup(teacherGroupGroupComboBox.getValue());
        }

        teacherGroup.setSemester(Integer.parseInt(teacherGroupSemesterField.getText()));

        if (AppManager.getTeacherGroupDao().contains(teacherGroup)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else {
            AppManager.getTeacherGroupDao().insert(teacherGroup);
            teacherGroupSemesterField.setText("");
            teacherGroupDisciplineComboBox.setValue(null);
            teacherGroupTeacherComboBox.setValue(null);
            teacherGroupGroupComboBox.setValue(null);
            teacherGroupTeacherComboBox.setDisable(true);
            onTeacherGroupRefreshButton();
        }
    }

    @FXML
    protected void onTeacherGroupDeleteButton() {
        try {
            AppManager.getTeacherGroupDao().delete(teacherGroupTable.getSelectionModel().getSelectedItem());
            onTeacherGroupRefreshButton();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    @FXML
    protected void onTeacherGroupSearch() {
        if (!teacherGroupSearchField.getText().isEmpty()) {
            teacherGroupTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getTeacherGroupDao().findAll()).filtered(
                            new CustomPredicate<TeacherGroup>().createPredicate(teacherGroupSearchField.getText()))));
        }
    }

    @FXML
    protected void onTeacherGroupSearchClear() {
        teacherGroupSearchField.setText("");
        onTeacherGroupRefreshButton();
    }

    @FXML
    protected void onTeachersRefreshButton() {
        teachersTable.setItems(FXCollections.observableArrayList(AppManager.getTeacherDao().findAll()));
        teachersSearchField.setText("");
    }

    @FXML
    protected void onTeachersAddButton() {
        boolean canAdd = true;

        Teacher teacher = new Teacher();

        teacher.setLastName(teacherLastNameField.getText());
        teacher.setFirstName(teacherFirstNameField.getText());
        teacher.setPatronymic(teacherPatronymicField.getText());

        teacher.setDateOfBirth(DateUtils.getDateFromString(teacherDateOfBirthField.getText(), DateUtils.DATE_PATTERN));

        if (teacherDepartmentExitsRadioButton.isSelected()) {
            teacher.setDepartment(teacherDepartmentComboBox.getValue());
        }
        else {
            Department department = new Department();
            department.setName(teacherDepartmentNameField.getText());

            if (AppManager.getDepartmentsDao().contains(department)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                teacher.setDepartment(department);
                AppManager.getDepartmentsDao().insert(department);
            }
        }

        if (teacherPostExitsRadioButton.isSelected()) {
            teacher.setPost(teacherPostComboBox.getValue());
        }
        else {
            Post post = new Post();
            post.setName(teacherPostNameField.getText());

            if (AppManager.getPostsDao().contains(post)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                teacher.setPost(post);
                AppManager.getPostsDao().insert(post);
            }
        }

        if (teacherAddressExitsRadioButton.isSelected()) {
            teacher.setAddress(teacherAddressComboBox.getValue());
        }
        else {
            Address address = new Address();
            address.setCity(teacherAddressStreetField.getText());
            address.setStreet(teacherAddressStreetField.getText());
            address.setHouseNumber(teacherAddressHouseNumberField.getText());
            address.setApartmentNumber(Integer.parseInt(teacherAddressApartmentNumberField.getText()));

            if (AppManager.getAddressesDao().contains(address)) {
                AlertLoader.SUBAVAILABLE_ALERT.showAndWait();
                canAdd = false;
            }
            else {
                teacher.setAddress(address);
                AppManager.getAddressesDao().insert(address);
            }
        }

        if (AppManager.getTeacherDao().contains(teacher)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else if (canAdd) {
            AppManager.getTeacherDao().insert(teacher);
            teacherLastNameField.setText("");
            teacherFirstNameField.setText("");
            teacherPatronymicField.setText("");
            teacherDateOfBirthField.setText("");
            teacherDepartmentNameField.setText("");
            teacherPostNameField.setText("");
            teacherAddressCityField.setText("");
            teacherAddressStreetField.setText("");
            teacherAddressHouseNumberField.setText("");
            teacherAddressApartmentNumberField.setText("");
            teacherDepartmentComboBox.setValue(null);
            teacherPostComboBox.setValue(null);
            teacherAddressComboBox.setValue(null);
            teacherDepartmentComboBox.setDisable(true);
            teacherPostComboBox.setDisable(true);
            teacherAddressComboBox.setDisable(true);
            teacherDepartmentGroup.selectToggle(null);
            teacherPostGroup.selectToggle(null);
            teacherAddressGroup.selectToggle(null);
            onTeachersRefreshButton();
            teacherComboBoxUpdate();
        }
    }

    @FXML
    protected void onTeachersDeleteButton() {
        try {
            AppManager.getTeacherDao().delete(teachersTable.getSelectionModel().getSelectedItem());
            onTeachersRefreshButton();
            teacherComboBoxUpdate();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    private void teacherComboBoxUpdate() {
        teacherDisciplineTeacherComboBox.setItems(
                FXCollections.observableArrayList(AppManager.getTeacherDao().findAll()));
    }

    @FXML
    protected void onTeachersSearch() {
        if (!teachersSearchField.getText().isEmpty()) {
            teachersTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getTeacherDao().findAll()).filtered(
                            new CustomPredicate<Teacher>().createPredicate(teachersSearchField.getText()))));
        }
    }

    @FXML
    protected void onTeachersSearchClear() {
        teachersSearchField.setText("");
        onTeachersRefreshButton();
    }

    @FXML
    protected void onSpecializationDisciplineRefreshButton() {
        specializationDisciplineTable.setItems(FXCollections.observableArrayList(AppManager.getSpecializationDisciplineDao().findAll()));
        specializationDisciplineSearchField.setText("");
    }

    @FXML
    protected void onSpecializationDisciplineAddButton() {
        SpecializationDiscipline specializationDiscipline = new SpecializationDiscipline();

        if (!specializationDisciplineSpecializationComboBox.getSelectionModel().isEmpty()) {
            specializationDiscipline.setSpecialization(specializationDisciplineSpecializationComboBox.getValue());
        }
        if (!specializationDisciplineDisciplineComboBox.getSelectionModel().isEmpty()) {
            specializationDiscipline.setDiscipline(specializationDisciplineDisciplineComboBox.getValue());
        }

        if (AppManager.getSpecializationDisciplineDao().contains(specializationDiscipline)) {
            AlertLoader.AVAILABLE_ALERT.showAndWait();
        }
        else {
            AppManager.getSpecializationDisciplineDao().insert(specializationDiscipline);
            specializationDisciplineSpecializationComboBox.setValue(null);
            specializationDisciplineDisciplineComboBox.setValue(null);
            specializationDisciplineSpecializationComboBox.setDisable(true);
            specializationDisciplineDisciplineComboBox.setDisable(true);
            onSpecializationDisciplineRefreshButton();
        }
    }

    @FXML
    protected void onSpecializationDisciplineDeleteButton() {
        try {
            AppManager.getSpecializationDisciplineDao().delete(specializationDisciplineTable.getSelectionModel().getSelectedItem());
            onSpecializationDisciplineRefreshButton();
        }
        catch (Exception ignored) {
            AlertLoader.DELETE_ALERT.showAndWait();
        }
    }

    @FXML
    protected void onSpecializationDisciplineSearch() {
        if (!specializationDisciplineSearchField.getText().isEmpty()) {
            specializationDisciplineTable.setItems(new FilteredList<>(
                    FXCollections.observableArrayList(
                            AppManager.getSpecializationDisciplineDao().findAll()).filtered(
                            new CustomPredicate<SpecializationDiscipline>().createPredicate(
                                    specializationDisciplineSearchField.getText()))));
        }
    }

    @FXML
    protected void onSpecializationDisciplineSearchClear() {
        specializationDisciplineSearchField.setText("");
        onSpecializationDisciplineRefreshButton();
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
    protected void onSemesterPerformanceTabChanged() {
        if (semesterPerformanceTab.isSelected()) {
            onSemesterPerformanceRefreshButton();
        }
    }

    @FXML
    protected void onDepartmentsTabChanged() {
        if (departmentsTab.isSelected()) {
            onDepartmentsRefreshButton();
        }
    }

    @FXML
    protected void onPostsTabChanged() {
        if (postsTab.isSelected()) {
            onPostsRefreshButton();
        }
    }

    @FXML
    protected void onTeacherDisciplineTabChanged() {
        if (teacherDisciplineTab.isSelected()) {
            onTeacherDisciplineRefreshButton();
        }
    }

    @FXML
    protected void onTeacherGroupTabChanged() {
        if (teacherGroupTab.isSelected()) {
            onTeacherGroupRefreshButton();
        }
    }

    @FXML
    protected void onTeachersTabChanged() {
        if (teachersTab.isSelected()) {
            onTeachersRefreshButton();
        }
    }

    @FXML
    protected void onSpecializationDisciplineTabChanged() {
        if (specializationDisciplineTab.isSelected()) {
            onSpecializationDisciplineRefreshButton();
        }
    }

    @FXML
    protected void onDisciplineNameColumnEditCommit(TableColumn.CellEditEvent<Discipline, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setName(value);
        disciplinesTable.refresh();
        AppManager.getDisciplinesDao().update(event.getRowValue());
        disciplinesComboBoxUpdate();
    }

    @FXML
    protected void onSpecializationNumberColumnEditCommit(TableColumn.CellEditEvent<Specialization, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setNumber(value);
        specializationsTable.refresh();
        AppManager.getSpecializationsDao().update(event.getRowValue());
        specializationsComboBoxUpdate();
    }

    @FXML
    protected void onSpecializationNameColumnEditCommit(TableColumn.CellEditEvent<Specialization, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setName(value);
        specializationsTable.refresh();
        AppManager.getSpecializationsDao().update(event.getRowValue());
        specializationsComboBoxUpdate();
    }

    @FXML
    protected void onSpecializationStudyDurationColumnEditCommit(TableColumn.CellEditEvent<Specialization, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setStudyDuration(value);
        specializationsTable.refresh();
        AppManager.getSpecializationsDao().update(event.getRowValue());
        specializationsComboBoxUpdate();
    }

    @FXML
    protected void onCityColumnEditCommit(TableColumn.CellEditEvent<Address, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setCity(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getRowValue());
        addressesComboBoxUpdate();
    }

    @FXML
    protected void onStreetColumnEditCommit(TableColumn.CellEditEvent<Address, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setStreet(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getRowValue());
        addressesComboBoxUpdate();
    }

    @FXML
    protected void onHouseNumberColumnEditCommit(TableColumn.CellEditEvent<Address, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setHouseNumber(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getRowValue());
        addressesComboBoxUpdate();
    }

    @FXML
    protected void onApartmentNumberColumnEditCommit(TableColumn.CellEditEvent<Address, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setApartmentNumber(value);
        addressesTable.refresh();
        AppManager.getAddressesDao().update(event.getRowValue());
        addressesComboBoxUpdate();
    }

    @FXML
    protected void onGroupNameColumnEditCommit(TableColumn.CellEditEvent<Group, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setName(value);
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getRowValue());
        groupsComboBoxUpdate();
    }

    @FXML
    protected void onCourseColumnEditCommit(TableColumn.CellEditEvent<Group, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setCourse(value);
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getRowValue());
        groupsComboBoxUpdate();
    }

    @FXML
    protected void onSemesterColumnEditCommit(TableColumn.CellEditEvent<Group, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setSemester(value);
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getRowValue());
        groupsComboBoxUpdate();
    }

    @FXML
    protected void onDateFormationColumnEditCommit(TableColumn.CellEditEvent<Group, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDateFormation(value);
        event.getRowValue().setCourse(DateUtils.getCurrentCourse(event.getRowValue().getDateFormation()));
        event.getRowValue().setSemester(DateUtils.getCurrentSemester(event.getRowValue().getDateFormation(), event.getRowValue().getCourse()));
        groupsTable.refresh();
        AppManager.getGroupsDao().update(event.getRowValue());
        groupsComboBoxUpdate();
    }

    @FXML
    protected void onStudentLastNameColumnEditCommit(TableColumn.CellEditEvent<Student, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setLastName(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onStudentFirstNameColumnEditCommit(TableColumn.CellEditEvent<Student, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setFirstName(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onStudentPatronymicColumnEditCommit(TableColumn.CellEditEvent<Student, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setPatronymic(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onStudentAddressColumnEditCommit(TableColumn.CellEditEvent<Student, Address> event) {
        final Address value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        if (!AppManager.getAddressesDao().contains(value) && !AlertLoader.ADDING_ALERT.isShowing()) {
            AlertLoader.ADDING_ALERT.showAndWait();
            if (AlertLoader.ADDING_ALERT.getResult() == ButtonType.YES) {
                event.getRowValue().setAddress(value);
                studentsTable.refresh();
                AppManager.getStudentDao().update(event.getRowValue());
                studentsComboBoxUpdate();
            }
        }
    }

    @FXML
    protected void onStudentDateOfBirthColumnEditCommit(TableColumn.CellEditEvent<Student, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDateOfBirth(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onStudentGroupColumnEditCommit(TableColumn.CellEditEvent<Student, Group> event) {
        final Group value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setGroup(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onStudentFormOfEducationColumnEditCommit(TableColumn.CellEditEvent<Student, FormOfEducation> event) {
        final FormOfEducation value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setFormOfEducation(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onStudentBasisOfEducationColumnEditCommit(TableColumn.CellEditEvent<Student, BasisOfEducation> event) {
        final BasisOfEducation value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setBasisOfEducation(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onStudentDateAdmissionColumnEditCommit(TableColumn.CellEditEvent<Student, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDateAdmission(value);
        studentsTable.refresh();
        AppManager.getStudentDao().update(event.getRowValue());
        studentsComboBoxUpdate();
    }

    @FXML
    protected void onParentLastNameColumnEditCommit(TableColumn.CellEditEvent<Parent, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setLastName(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getRowValue());
    }

    @FXML
    protected void onParentFirstNameColumnEditCommit(TableColumn.CellEditEvent<Parent, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setFirstName(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getRowValue());
    }

    @FXML
    protected void onParentPatronymicColumnEditCommit(TableColumn.CellEditEvent<Parent, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setPatronymic(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getRowValue());
    }

    @FXML
    protected void onParentStudentColumnEditCommit(TableColumn.CellEditEvent<Parent, Student> event) {
        final Student value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setStudent(value);
        studentsTable.refresh();
        AppManager.getParentDao().update(event.getRowValue());
    }

    @FXML
    protected void onParentAddressColumnEditCommit(TableColumn.CellEditEvent<Parent, Address> event) {
        final Address value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        if (!AppManager.getAddressesDao().contains(value) && !AlertLoader.ADDING_ALERT.isShowing()) {
            AlertLoader.ADDING_ALERT.showAndWait();
            if (AlertLoader.ADDING_ALERT.getResult() == ButtonType.YES) {
                event.getRowValue().setAddress(value);
                parentsTable.refresh();
                AppManager.getParentDao().update(event.getRowValue());
            }
        }
    }

    @FXML
    protected void onDisciplineTypeOfMarkColumnEditCommit(TableColumn.CellEditEvent<Discipline, TypeOfMark> event) {
        final TypeOfMark value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setTypeOfMark(value);
        disciplinesTable.refresh();
        AppManager.getDisciplinesDao().update(event.getRowValue());
        disciplinesComboBoxUpdate();
    }

    @FXML
    protected void onSemesterPerformanceStudentColumnEditCommit(TableColumn.CellEditEvent<SemesterPerformance, Student> event) {
        final Student value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setStudent(value);
        semesterPerformanceTable.refresh();
        AppManager.getSemesterPerformanceDao().update(event.getRowValue());
    }

    @FXML
    protected void onSemesterPerformanceDisciplineColumnEditCommit(TableColumn.CellEditEvent<SemesterPerformance, Discipline> event) {
        final Discipline value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDiscipline(value);
        semesterPerformanceTable.refresh();
        AppManager.getSemesterPerformanceDao().update(event.getRowValue());
    }

    @FXML
    protected void onSemesterPerformanceMarkColumnEditCommit(TableColumn.CellEditEvent<SemesterPerformance, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setMark(value);
        event.getRowValue().setEctsMark(MarksUtils.getEctsMark(value));
        event.getRowValue().setTraditionalMark(MarksUtils.getTraditionalMark(value));
        event.getRowValue().setTraditionalWordMark(MarksUtils.getTraditionalWordMark(value,
                event.getRowValue().getDiscipline().getTypeOfMark().getName()));
        semesterPerformanceTable.refresh();
        AppManager.getSemesterPerformanceDao().update(event.getRowValue());
    }

    @FXML
    protected void onDepartmentNameColumnEditCommit(TableColumn.CellEditEvent<Department, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setName(value);
        departmentsTable.refresh();
        AppManager.getDepartmentsDao().update(event.getRowValue());
        departmentsComboBoxUpdate();
    }

    @FXML
    protected void onPostNameColumnEditCommit(TableColumn.CellEditEvent<Post, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setName(value);
        departmentsTable.refresh();
        AppManager.getPostsDao().update(event.getRowValue());
        postsComboBoxUpdate();
    }

    @FXML
    protected void onTeacherGroupSemesterColumnEditCommit(TableColumn.CellEditEvent<TeacherGroup, Integer> event) {
        final Integer value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setSemester(value);
        teacherGroupTable.refresh();
        AppManager.getTeacherGroupDao().update(event.getRowValue());
    }

    @FXML
    protected void onTeacherDisciplineTeacherColumnEditCommit(TableColumn.CellEditEvent<TeacherDiscipline, Teacher> event) {
        final Teacher value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setTeacher(value);
        teacherDisciplineTable.refresh();
        AppManager.getTeacherDisciplineDao().update(event.getRowValue());
    }

    @FXML
    protected void onTeacherDisciplineDisciplineColumnEditCommit(TableColumn.CellEditEvent<TeacherDiscipline, Discipline> event) {
        final Discipline value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDiscipline(value);
        teacherDisciplineTable.refresh();
        AppManager.getTeacherDisciplineDao().update(event.getRowValue());
    }

    @FXML
    protected void onTeacherLastNameColumnEditCommit(TableColumn.CellEditEvent<Teacher, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setLastName(value);
        teachersTable.refresh();
        AppManager.getTeacherDao().update(event.getRowValue());
        teacherComboBoxUpdate();
    }

    @FXML
    protected void onTeacherFirstNameColumnEditCommit(TableColumn.CellEditEvent<Teacher, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setFirstName(value);
        teachersTable.refresh();
        AppManager.getTeacherDao().update(event.getRowValue());
        teacherComboBoxUpdate();
    }

    @FXML
    protected void onTeacherPatronymicColumnEditCommit(TableColumn.CellEditEvent<Teacher, String> event) {
        final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setPatronymic(value);
        teachersTable.refresh();
        AppManager.getTeacherDao().update(event.getRowValue());
        teacherComboBoxUpdate();
    }

    @FXML
    protected void onTeacherDateOfBirthColumnEditCommit(TableColumn.CellEditEvent<Teacher, Date> event) {
        final Date value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDateOfBirth(value);
        teachersTable.refresh();
        AppManager.getTeacherDao().update(event.getRowValue());
        teacherComboBoxUpdate();
    }

    @FXML
    protected void onTeacherDepartmentColumnEditCommit(TableColumn.CellEditEvent<Teacher, Department> event) {
        final Department value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDepartment(value);
        teachersTable.refresh();
        AppManager.getTeacherDao().update(event.getRowValue());
        teacherComboBoxUpdate();
    }

    @FXML
    protected void onTeacherPostColumnEditCommit(TableColumn.CellEditEvent<Teacher, Post> event) {
        final Post value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setPost(value);
        teachersTable.refresh();
        AppManager.getTeacherDao().update(event.getRowValue());
        teacherComboBoxUpdate();
    }

    @FXML
    protected void onTeacherAddressColumnEditCommit(TableColumn.CellEditEvent<Teacher, Address> event) {
        final Address value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        if (!AppManager.getAddressesDao().contains(value) && !AlertLoader.ADDING_ALERT.isShowing()) {
            AlertLoader.ADDING_ALERT.showAndWait();
            if (AlertLoader.ADDING_ALERT.getResult() == ButtonType.YES) {
                event.getRowValue().setAddress(value);
                teachersTable.refresh();
                AppManager.getTeacherDao().update(event.getRowValue());
                teacherComboBoxUpdate();
            }
        }
    }

    @FXML
    protected void onSpecializationDisciplineSpecializationColumnEditCommit(
            TableColumn.CellEditEvent<SpecializationDiscipline, Specialization> event) {
        final Specialization value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setSpecialization(value);
        specializationDisciplineTable.refresh();
        AppManager.getSpecializationDisciplineDao().update(event.getRowValue());
        specializationsComboBoxUpdate();
    }

    @FXML
    protected void onSpecializationDisciplineDisciplineColumnEditCommit(
            TableColumn.CellEditEvent<SpecializationDiscipline, Discipline> event) {
        final Discipline value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        event.getRowValue().setDiscipline(value);
        specializationDisciplineTable.refresh();
        AppManager.getSpecializationDisciplineDao().update(event.getRowValue());
        specializationsComboBoxUpdate();
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
    protected void onSemesterPerformanceDisciplineChoose() {
        if (semesterPerformanceDisciplineExitsRadioButton.isSelected()) {
            semesterPerformanceDisciplineNameField.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setDisable(true);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setDisable(true);
            semesterPerformanceDisciplineComboBox.setDisable(false);
            semesterPerformanceDisciplineComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getDisciplinesDao().findAll()));
            semesterPerformanceDisciplineComboBox.getSelectionModel().select(0);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setValue(null);
        }
        else if (semesterPerformanceDisciplineNewRadioButton.isSelected()) {
            semesterPerformanceDisciplineComboBox.setDisable(true);
            semesterPerformanceDisciplineNameField.setDisable(false);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setDisable(false);
            semesterPerformanceDisciplineComboBox.setValue(null);
            semesterPerformanceDisciplineTypeOfMarkComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getTypesOfMarksDao().findAll()));
            semesterPerformanceDisciplineTypeOfMarkComboBox.getSelectionModel().select(0);
        }
    }

    @FXML
    protected void onTeacherDepartmentChoose() {
        if (teacherDepartmentExitsRadioButton.isSelected()) {
            teacherDepartmentNameField.setDisable(true);
            teacherDepartmentComboBox.setDisable(false);
            teacherDepartmentComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getDepartmentsDao().findAll()));
            teacherDepartmentComboBox.getSelectionModel().select(0);
        }
        else if (teacherDepartmentNewRadioButton.isSelected()) {
            teacherDepartmentComboBox.setDisable(true);
            teacherDepartmentNameField.setDisable(false);
            teacherDepartmentComboBox.setValue(null);
        }
    }

    @FXML
    protected void onTeacherPostChoose() {
        if (teacherPostExitsRadioButton.isSelected()) {
            teacherPostNameField.setDisable(true);
            teacherPostComboBox.setDisable(false);
            teacherPostComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getPostsDao().findAll()));
            teacherPostComboBox.getSelectionModel().select(0);
        }
        else if (teacherPostNewRadioButton.isSelected()) {
            teacherPostComboBox.setDisable(true);
            teacherPostNameField.setDisable(false);
            teacherPostComboBox.setValue(null);
        }
    }

    @FXML
    protected void onTeacherAddressChoose() {
        if (teacherAddressExitsRadioButton.isSelected()) {
            teacherAddressCityField.setDisable(true);
            teacherAddressStreetField.setDisable(true);
            teacherAddressHouseNumberField.setDisable(true);
            teacherAddressApartmentNumberField.setDisable(true);
            teacherAddressComboBox.setDisable(false);
            teacherAddressComboBox.setItems(FXCollections.observableArrayList(
                    AppManager.getAddressesDao().findAll()));
            teacherAddressComboBox.getSelectionModel().select(0);
        }
        else if (teacherAddressNewRadioButton.isSelected()) {
            teacherAddressComboBox.setDisable(true);
            teacherAddressCityField.setDisable(false);
            teacherAddressStreetField.setDisable(false);
            teacherAddressHouseNumberField.setDisable(false);
            teacherAddressApartmentNumberField.setDisable(false);
            teacherAddressComboBox.setValue(null);
        }
    }

    @FXML
    protected void onAdvancedModeClick() {
        if (advancedMode.isSelected()) activateAdvancedTabs();
        else deactivateAdvancedTabs();
    }

    private void activateAdvancedTabs() {
        tabPane.getTabs().add(typesOfMarksTab);
        tabPane.getTabs().add(formsOfEducationTab);
        tabPane.getTabs().add(basisOfEducationTab);
        tabPane.getTabs().add(teacherDisciplineTab);
        tabPane.getTabs().add(teacherGroupTab);
        tabPane.getTabs().add(specializationDisciplineTab);
    }

    private void deactivateAdvancedTabs() {
        tabPane.getTabs().remove(typesOfMarksTab);
        tabPane.getTabs().remove(formsOfEducationTab);
        tabPane.getTabs().remove(basisOfEducationTab);
        tabPane.getTabs().remove(teacherDisciplineTab);
        tabPane.getTabs().remove(teacherGroupTab);
        tabPane.getTabs().remove(specializationDisciplineTab);
    }

    @FXML
    protected void onAboutProgramClick() throws IOException {
        aboutProgramStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about-program-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        aboutProgramStage.setScene(scene);
        aboutProgramStage.setTitle(AppManager.getLocalizationLoader().getMessage("About.stage.title"));
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
        checkCoursesAndSemesters();
    }

    private void checkCoursesAndSemesters() {
        for (Group group : AppManager.getGroupsDao().findAll()) {
            int course = DateUtils.getCurrentCourse(group.getDateFormation());
            int semester = DateUtils.getCurrentSemester(group.getDateFormation(), course);
            if (group.getCourse() != course) {
                group.setCourse(course);
            }
            if (group.getSemester() != semester) {
                group.setSemester(semester);
            }
        }
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

        parentAddressExitsRadioButton.setToggleGroup(parentAddressGroup);
        parentAddressNewRadioButton.setToggleGroup(parentAddressGroup);

        semesterPerformanceDisciplineExitsRadioButton.setToggleGroup(semesterPerformanceDisciplineGroup);
        semesterPerformanceDisciplineNewRadioButton.setToggleGroup(semesterPerformanceDisciplineGroup);

        teacherDepartmentExitsRadioButton.setToggleGroup(teacherDepartmentGroup);
        teacherDepartmentNewRadioButton.setToggleGroup(teacherDepartmentGroup);

        teacherPostExitsRadioButton.setToggleGroup(teacherPostGroup);
        teacherPostNewRadioButton.setToggleGroup(teacherPostGroup);

        teacherAddressExitsRadioButton.setToggleGroup(teacherAddressGroup);
        teacherAddressNewRadioButton.setToggleGroup(teacherAddressGroup);
    }

    private void initAllTables() {
        initAddressesTable();
        initBasisOfEducationTable();
        initFormsOfEducationTable();
        initTypesOfMarksTable();
        initDisciplinesTable();
        initSpecializationsTable();
        initGroupsTable();
        initStudentsTable();
        initParentsTable();
        initSemesterPerformanceTable();
        initDepartmentsTable();
        initPostsTable();
        initTeacherDisciplineTable();
        initTeacherGroupTable();
        initTeachersTable();
        initSpecializationDisciplineTable();
    }

    private void initAllButtons() {
        initAddressButtons();
        initDisciplinesButtons();
        initSpecializationsButtons();
        initGroupsButtons();
        initStudentsButtons();
        initParentsButtons();
        initSemesterPerformanceButtons();
        initDepartmentsButtons();
        initPostsButtons();
        initTeacherDisciplineButtons();
        initTeacherGroupButtons();
        initTeachersButtons();
        initSpecializationDisciplineButtons();
    }

    private void initAddressesTable() {
        addressesTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        apartmentNumberColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new IntegerStringConverter()));

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

        BasisOfEducation formBudget = new BasisOfEducation("Бюджет");
        BasisOfEducation formContract = new BasisOfEducation("Контракт");
        BasisOfEducation formTarget = new BasisOfEducation("Целевое");

        if (!AppManager.getBasisOfEducationDao().contains(formBudget)) {
            AppManager.getBasisOfEducationDao().insert(formBudget);
        }
        if (!AppManager.getBasisOfEducationDao().contains(formContract)) {
            AppManager.getBasisOfEducationDao().insert(formContract);
        }
        if (!AppManager.getBasisOfEducationDao().contains(formTarget)) {
            AppManager.getBasisOfEducationDao().insert(formTarget);
        }
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

        FormOfEducation formFullTime = new FormOfEducation("Очно");
        FormOfEducation formCorrespondence = new FormOfEducation("Заочно");
        FormOfEducation formFullTimeCorrespondence = new FormOfEducation("Очно-заочно");

        if (!AppManager.getFormsOfEducationDao().contains(formFullTime)) {
            AppManager.getFormsOfEducationDao().insert(formFullTime);
        }
        if (!AppManager.getFormsOfEducationDao().contains(formCorrespondence)) {
            AppManager.getFormsOfEducationDao().insert(formCorrespondence);
        }
        if (!AppManager.getFormsOfEducationDao().contains(formFullTimeCorrespondence)) {
            AppManager.getFormsOfEducationDao().insert(formFullTimeCorrespondence);
        }
    }

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

        TypeOfMark typeTest = new TypeOfMark("Зачёт");
        TypeOfMark typeExam = new TypeOfMark("Экзамен");
        TypeOfMark typeDiffTest = new TypeOfMark("Дифф. зачёт");

        if (!AppManager.getTypesOfMarksDao().contains(typeTest)) {
            AppManager.getTypesOfMarksDao().insert(typeTest);
        }
        if (!AppManager.getTypesOfMarksDao().contains(typeExam)) {
            AppManager.getTypesOfMarksDao().insert(typeExam);
        }
        if (!AppManager.getTypesOfMarksDao().contains(typeDiffTest)) {
            AppManager.getTypesOfMarksDao().insert(typeDiffTest);
        }
    }

    private void initDisciplinesTable() {
        disciplinesTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        disciplineTypeOfMarkComboBox.setItems(FXCollections.observableArrayList(AppManager.getTypesOfMarksDao().findAll()));

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
        studyDurationColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        specializationsSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onSpecializationsSearch();
                }
            }
        });
    }

    private void initGroupsTable() {
        groupsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        courseColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new LimitationIntegerConverter(1, AppManager.getSpecializationsDao().findMaxStudyDuration())));
        semesterColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new LimitationIntegerConverter(1, AppManager.getSpecializationsDao().findMaxStudyDuration() * 2)));

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
                specializationsComboBoxUpdate();
            });
            return cell;
        });

        dateFormationColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new CustomDateStringConverter(DateUtils.DATE_PATTERN), new DateTextField()));
        dateGraduationColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new CustomDateStringConverter(DateUtils.DATE_PATTERN), new DateTextField()));

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

        studentFormOfEducationComboBox.setItems(FXCollections.observableArrayList(AppManager.getFormsOfEducationDao().findAll()));
        studentBasisOfEducationComboBox.setItems(FXCollections.observableArrayList(AppManager.getBasisOfEducationDao().findAll()));

        dateOfBirthColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn
                (new CustomDateStringConverter(DateUtils.DATE_PATTERN), new DateTextField()));
        studentAddressColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new AddressConverter()));

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
                groupsComboBoxUpdate();
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

        dateAdmissionColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new CustomDateStringConverter(DateUtils.DATE_PATTERN), new DateTextField()));

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
                studentsComboBoxUpdate();
            });
            return cell;
        });

        parentAddressColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new AddressConverter()));
    }

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
                studentsComboBoxUpdate();
            });
            return cell;
        });

        semesterPerformanceCourseColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new LimitationIntegerConverter(1, AppManager.getSpecializationsDao().findMaxStudyDuration())));
        semesterPerformanceSemesterColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new LimitationIntegerConverter(1, AppManager.getSpecializationsDao().findMaxStudyDuration() * 2)));

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
                disciplinesComboBoxUpdate();
            });
            return cell;
        });
        semesterPerformanceMarkColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new LimitationIntegerConverter(0, 100)));
        semesterPerformanceSemesterField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onSemesterPerformanceSearch();
                }
            }
        });
    }

    private void initDepartmentsTable() {
        departmentsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        departmentsSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onDepartmentsSearch();
                }
            }
        });
    }

    private void initPostsTable() {
        postsTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        postsSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onPostsSearch();
                }
            }
        });
    }

    private void initTeacherDisciplineTable() {
        teacherDisciplineTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        teacherDisciplineTeacherComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getTeacherDao().findAll()));
        teacherDisciplineDisciplineComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getDisciplinesDao().findAll()));

        teacherDisciplineTeacherColumn.setCellFactory(tc -> {
            ComboBox<Teacher> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getTeacherDao().findAllWithOrder("lastName"));
            TableCell<TeacherDiscipline, Teacher> cell = new TableCell<>() {
                @Override
                protected void updateItem(Teacher reason, boolean empty) {
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
                teacherDisciplineTable.getItems().get(cell.getIndex()).setTeacher(combo.getValue());
                teacherDisciplineTable.refresh();
                AppManager.getTeacherDisciplineDao().update(teacherDisciplineTable.getItems().get(cell.getIndex()));
                teacherComboBoxUpdate();
            });
            return cell;
        });

        teacherDisciplineDisciplineColumn.setCellFactory(tc -> {
            ComboBox<Discipline> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getDisciplinesDao().findAllWithOrder("name"));
            TableCell<TeacherDiscipline, Discipline> cell = new TableCell<>() {
                @Override
                protected void updateItem(Discipline reason, boolean empty) {
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
                teacherDisciplineTable.getItems().get(cell.getIndex()).setDiscipline(combo.getValue());
                teacherDisciplineTable.refresh();
                AppManager.getTeacherDisciplineDao().update(teacherDisciplineTable.getItems().get(cell.getIndex()));
                disciplinesComboBoxUpdate();
            });
            return cell;
        });

        teacherDisciplineSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onTeacherDisciplineSearch();
                }
            }
        });
    }

    private void initTeacherGroupTable() {
        teacherGroupTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        teacherGroupSemesterColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new LimitationIntegerConverter(1, AppManager.getSpecializationsDao().findMaxStudyDuration() * 2)));

        teacherGroupDisciplineComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getDisciplinesDao().findAll()));
        teacherGroupGroupComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getGroupsDao().findAll()));

        teacherGroupDisciplineComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!teacherGroupDisciplineComboBox.getSelectionModel().isEmpty()) {
                    teacherGroupTeacherComboBox.setDisable(false);
                    teacherGroupTeacherComboBox.setItems(FXCollections.observableArrayList(
                            AppManager.getTeacherDao().findTeachers(teacherGroupDisciplineComboBox.getValue())));
                }
                else {
                    teacherGroupTeacherComboBox.setDisable(true);
                }
            }
        });

        teacherGroupTeacherDisciplineColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new TeacherDisciplineConverter()));
        teacherGroupGroupColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new GroupConverter()));

        teacherGroupSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onTeacherGroupSearch();
                }
            }
        });
    }

    private void initTeachersTable() {
        teachersTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        teacherDateOfBirthColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(
                new CustomDateStringConverter(DateUtils.DATE_PATTERN), new DateTextField()));
        teacherAddressColumn.setCellFactory(CustomTextFieldTableCell.forTableColumn(new AddressConverter()));

        teacherDepartmentColumn.setCellFactory(tc -> {
            ComboBox<Department> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getDepartmentsDao().findAll());
            TableCell<Teacher, Department> cell = new TableCell<>() {
                @Override
                protected void updateItem(Department reason, boolean empty) {
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
                teachersTable.getItems().get(cell.getIndex()).setDepartment(combo.getValue());
                teachersTable.refresh();
                AppManager.getTeacherDao().update(teachersTable.getItems().get(cell.getIndex()));
                departmentsComboBoxUpdate();
            });
            return cell;
        });

        teacherPostColumn.setCellFactory(tc -> {
            ComboBox<Post> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getPostsDao().findAll());
            TableCell<Teacher, Post> cell = new TableCell<>() {
                @Override
                protected void updateItem(Post reason, boolean empty) {
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
                teachersTable.getItems().get(cell.getIndex()).setPost(combo.getValue());
                teachersTable.refresh();
                AppManager.getTeacherDao().update(teachersTable.getItems().get(cell.getIndex()));
                postsComboBoxUpdate();
            });
            return cell;
        });

        teachersSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onTeachersSearch();
                }
            }
        });
    }

    private void initSpecializationDisciplineTable() {
        specializationDisciplineTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        specializationDisciplineSpecializationComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getSpecializationsDao().findAll()));
        specializationDisciplineDisciplineComboBox.setItems(FXCollections.observableArrayList(
                AppManager.getDisciplinesDao().findAll()));

        // TODO Попробовать что-то доделать
//        specializationDisciplineSpecializationColumn.setCellFactory(
//                CustomComboBoxTableCell.forTableColumn(new SpecializationConverter(),
//                        FXCollections.observableArrayList(AppManager.getSpecializationsDao().findAll())));

        specializationDisciplineSpecializationColumn.setCellFactory(tc -> {
            ComboBox<Specialization> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getSpecializationsDao().findAll());
            TableCell<SpecializationDiscipline, Specialization> cell = new TableCell<>() {
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
                specializationDisciplineTable.getItems().get(cell.getIndex()).setSpecialization(combo.getValue());
                specializationDisciplineTable.refresh();
                AppManager.getSpecializationDisciplineDao().update(specializationDisciplineTable.getItems().get(cell.getIndex()));
                specializationsComboBoxUpdate();
            });
            return cell;
        });

        specializationDisciplineDisciplineColumn.setCellFactory(tc -> {
            ComboBox<Discipline> combo = new ComboBox<>();
            combo.getItems().addAll(AppManager.getDisciplinesDao().findAllWithOrder("name"));
            TableCell<SpecializationDiscipline, Discipline> cell = new TableCell<>() {
                @Override
                protected void updateItem(Discipline reason, boolean empty) {
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
                specializationDisciplineTable.getItems().get(cell.getIndex()).setDiscipline(combo.getValue());
                specializationDisciplineTable.refresh();
                AppManager.getSpecializationDisciplineDao().update(specializationDisciplineTable.getItems().get(cell.getIndex()));
                disciplinesComboBoxUpdate();
            });
            return cell;
        });

        specializationDisciplineSearchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    onSpecializationDisciplineSearch();
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
                return addressCityField.getText().isEmpty()
                        || addressStreetField.getText().isEmpty()
                        || addressHouseNumberField.getText().isEmpty()
                        || !TypesUtils.isInteger(addressApartmentNumberField.getText());
            }
        };

        addressesAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(addressesTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return addressesTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        addressesDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initDisciplinesButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(disciplinesNameField.textProperty(),
                            disciplineTypeOfMarkComboBox.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                return disciplinesNameField.getText().isEmpty()
                        || disciplineTypeOfMarkComboBox.getSelectionModel().isEmpty();
            }
        };

        disciplinesAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(disciplinesTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return disciplinesTable.getSelectionModel().getSelectedItems().isEmpty();
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
                return specializationsNumberField.getText().isEmpty()
                        || specializationsNameField.getText().isEmpty()
                        || !TypesUtils.isInteger(specializationsStudyDurationField.getText());
            }
        };

        specializationsAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(specializationsTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return specializationsTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        specializationsDeleteButton.disableProperty().bind(deleteBind);
    }

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
                return groupsTable.getSelectionModel().getSelectedItems().isEmpty();
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
                        studentSpecializationGroup.selectedToggleProperty());
            }

            private boolean computeStudentAddress() {
                return !(studentAddressNewRadioButton.isSelected()
                        && !studentAddressCityField.getText().isEmpty()
                        && !studentAddressStreetField.getText().isEmpty()
                        && !studentAddressHouseNumberField.getText().isEmpty()
                        && TypesUtils.isInteger(studentAddressApartmentNumberField.getText()))
                        && (!studentAddressExitsRadioButton.isSelected()
                            || studentAddressComboBox.getSelectionModel().isEmpty());
            }

            private boolean computeStudentGroup() {
                return !(studentGroupNewRadioButton.isSelected()
                        && !studentGroupNameField.getText().isEmpty()
                        && !computeStudentSpecialization()
                        && TypesUtils.isDate(studentGroupDateFormationField.getText()))
                        && (!studentGroupExitsRadioButton.isSelected()
                            || studentGroupComboBox.getSelectionModel().isEmpty());
            }

            private boolean computeStudentSpecialization() {
                return !(studentSpecializationNewRadioButton.isSelected()
                        && !studentSpecializationNumberField.getText().isEmpty()
                        && !studentSpecializationNameField.getText().isEmpty()
                        && TypesUtils.isInteger(studentSpecializationStudyDurationField.getText()))
                        && (!studentSpecializationExitsRadioButton.isSelected()
                            || studentSpecializationComboBox.getSelectionModel().isEmpty());
            }

            @Override
            protected boolean computeValue() {
                return studentLastNameField.getText().isEmpty()
                        || studentFirstNameField.getText().isEmpty()
                        || studentPatronymicField.getText().isEmpty()
                        || !TypesUtils.isDate(studentDateOfBirthField.getText())
                        || computeStudentAddress()
                        || computeStudentGroup()
                        || studentFormOfEducationComboBox.getSelectionModel().isEmpty()
                        || studentBasisOfEducationComboBox.getSelectionModel().isEmpty()
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
                return studentsTable.getSelectionModel().getSelectedItems().isEmpty();
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
                            && (!parentAddressExitsRadioButton.isSelected()
                                || parentAddressComboBox.getSelectionModel().isEmpty()))
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
                        semesterPerformanceDisciplineGroup.selectedToggleProperty());
            }

            @Override
            protected boolean computeValue() {
                return semesterPerformanceStudentComboBox.getSelectionModel().isEmpty()
                        || !TypesUtils.isCourse(semesterPerformanceCourseField.getText())
                        || !TypesUtils.isSemester(semesterPerformanceSemesterField.getText())
                        || (!(semesterPerformanceDisciplineNewRadioButton.isSelected()
                            && !semesterPerformanceDisciplineNameField.getText().isEmpty()
                            && !semesterPerformanceDisciplineTypeOfMarkComboBox.getSelectionModel().isEmpty())
                            && (!semesterPerformanceDisciplineExitsRadioButton.isSelected()
                            || semesterPerformanceDisciplineComboBox.getSelectionModel().isEmpty()))
                        || !TypesUtils.isMark(semesterPerformanceMarkField.getText());
            }
        };

        semesterPerformanceAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(semesterPerformanceTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return semesterPerformanceTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        semesterPerformanceDeleteButton.disableProperty().bind(deleteBind);
    }

    @FXML
    private void onExitClick() throws IOException {
        AppManager.setCurrentStudent(null);
//        AppManager.setCurrentTeacher(null);

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

    private void initDepartmentsButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(departmentsNameField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return departmentsNameField.getText().isEmpty();
            }
        };

        departmentsAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(departmentsTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return departmentsTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        departmentsDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initPostsButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(postsNameField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (postsNameField.getText().isEmpty());
            }
        };

        postsAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(postsTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return postsTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        postsDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initTeacherDisciplineButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(teacherDisciplineTeacherComboBox.valueProperty(),
                        teacherDisciplineDisciplineComboBox.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                return teacherDisciplineTeacherComboBox.getSelectionModel().isEmpty()
                        || teacherDisciplineDisciplineComboBox.getSelectionModel().isEmpty();
            }
        };

        teacherDisciplineAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(teacherDisciplineTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return teacherDisciplineTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        teacherDisciplineDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initTeacherGroupButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(teacherGroupSemesterField.textProperty(),
                        teacherGroupTeacherComboBox.valueProperty(),
                        teacherGroupDisciplineComboBox.valueProperty(),
                        teacherGroupGroupComboBox.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                return teacherGroupTeacherComboBox.getSelectionModel().isEmpty()
                        || teacherGroupDisciplineComboBox.getSelectionModel().isEmpty()
                        || teacherGroupGroupComboBox.getSelectionModel().isEmpty()
                        || !TypesUtils.isSemester(teacherGroupSemesterField.getText());
            }
        };

        teacherGroupAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(teacherGroupTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return teacherGroupTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        teacherGroupDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initTeachersButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(teacherLastNameField.textProperty(),
                        teacherFirstNameField.textProperty(),
                        teacherPatronymicField.textProperty(),
                        teacherDateOfBirthField.textProperty(),
                        teacherDepartmentNameField.textProperty(),
                        teacherPostNameField.textProperty(),
                        teacherAddressCityField.textProperty(),
                        teacherAddressStreetField.textProperty(),
                        teacherAddressHouseNumberField.textProperty(),
                        teacherAddressApartmentNumberField.textProperty(),
                        teacherDepartmentComboBox.valueProperty(),
                        teacherPostComboBox.valueProperty(),
                        teacherAddressComboBox.valueProperty(),
                        teacherDepartmentGroup.selectedToggleProperty(),
                        teacherPostGroup.selectedToggleProperty(),
                        teacherAddressGroup.selectedToggleProperty());
            }

            private boolean computeTeacherAddress() {
                return !(teacherAddressNewRadioButton.isSelected()
                        && !teacherAddressCityField.getText().isEmpty()
                        && !teacherAddressStreetField.getText().isEmpty()
                        && !teacherAddressHouseNumberField.getText().isEmpty()
                        && TypesUtils.isInteger(teacherAddressApartmentNumberField.getText()))
                        && (!teacherAddressExitsRadioButton.isSelected()
                        || teacherAddressComboBox.getSelectionModel().isEmpty());
            }

            private boolean computeTeacherDepartment() {
                return !(teacherDepartmentNewRadioButton.isSelected()
                        && !teacherDepartmentNameField.getText().isEmpty())
                        && (!teacherDepartmentExitsRadioButton.isSelected()
                        || teacherDepartmentComboBox.getSelectionModel().isEmpty());
            }

            private boolean computeTeacherPost() {
                return !(teacherPostNewRadioButton.isSelected()
                        && !teacherPostNameField.getText().isEmpty())
                        && (!teacherPostExitsRadioButton.isSelected()
                        || teacherPostComboBox.getSelectionModel().isEmpty());
            }

            @Override
            protected boolean computeValue() {
                return teacherLastNameField.getText().isEmpty()
                        || teacherFirstNameField.getText().isEmpty()
                        || teacherPatronymicField.getText().isEmpty()
                        || !TypesUtils.isDate(teacherDateOfBirthField.getText())
                        || computeTeacherDepartment()
                        || computeTeacherPost()
                        || computeTeacherAddress();
            }
        };

        teachersAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(teachersTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return teachersTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        teachersDeleteButton.disableProperty().bind(deleteBind);
    }

    private void initSpecializationDisciplineButtons() {
        BooleanBinding addBind = new BooleanBinding() {
            {
                super.bind(specializationDisciplineSpecializationComboBox.valueProperty(),
                        specializationDisciplineDisciplineComboBox.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                return specializationDisciplineSpecializationComboBox.getSelectionModel().isEmpty()
                        || specializationDisciplineDisciplineComboBox.getSelectionModel().isEmpty();
            }
        };

        specializationDisciplineAddButton.disableProperty().bind(addBind);

        BooleanBinding deleteBind = new BooleanBinding() {
            {
                super.bind(specializationDisciplineTable.getSelectionModel().getSelectedItems());
            }

            @Override
            protected boolean computeValue() {
                return specializationDisciplineTable.getSelectionModel().getSelectedItems().isEmpty();
            }
        };

        specializationDisciplineDeleteButton.disableProperty().bind(deleteBind);
    }
}