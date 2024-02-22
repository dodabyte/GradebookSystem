package com.example.lab2;

import com.example.lab2.dao.*;
import com.example.lab2.objects.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppManager {
    private static boolean debugModeEnable = true; // TODO replace with FALSE

    private static Student currentStudent = null;
//    private static Teacher currentTeacher = null;

    private static AddressesDao addressesDao = new AddressesDao();
    private static BasisOfEducationDao basisOfEducationDao = new BasisOfEducationDao();
    private static DisciplinesDao disciplinesDao = new DisciplinesDao();
    private static FormsOfEducationDao formsOfEducationDao = new FormsOfEducationDao();
    private static StatusesDao statusesDao = new StatusesDao();
    private static TypesOfMarksDao typesOfMarksDao = new TypesOfMarksDao();
    private static SpecializationsDao specializationsDao = new SpecializationsDao();
    private static CurrentPaymentsDao currentPaymentsDao = new CurrentPaymentsDao();
    private static GroupsDao groupsDao = new GroupsDao();
    private static StudentDao studentDao = new StudentDao();
    private static ParentDao parentDao = new ParentDao();
    private static ContractDataDao contractDataDao = new ContractDataDao();
    private static SemesterPerformanceDao semesterPerformanceDao = new SemesterPerformanceDao();
    private static AuthDataDao authDataDao = new AuthDataDao();

    public AppManager(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorization-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setTitle("Система ведения зачетных книжек");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static AddressesDao getAddressesDao() {
        return addressesDao;
    }

    public static BasisOfEducationDao getBasisOfEducationDao() {
        return basisOfEducationDao;
    }

    public static DisciplinesDao getDisciplinesDao() {
        return disciplinesDao;
    }

    public static FormsOfEducationDao getFormsOfEducationDao() {
        return formsOfEducationDao;
    }

    public static StatusesDao getStatusesDao() {
        return statusesDao;
    }

    public static TypesOfMarksDao getTypesOfMarksDao() {
        return typesOfMarksDao;
    }

    public static SpecializationsDao getSpecializationsDao() {
        return specializationsDao;
    }

    public static CurrentPaymentsDao getCurrentPaymentsDao() {
        return currentPaymentsDao;
    }

    public static GroupsDao getGroupsDao() {
        return groupsDao;
    }

    public static StudentDao getStudentDao() {
        return studentDao;
    }

    public static ParentDao getParentDao() {
        return parentDao;
    }

    public static ContractDataDao getContractDataDao() {
        return contractDataDao;
    }

    public static SemesterPerformanceDao getSemesterPerformanceDao() {
        return semesterPerformanceDao;
    }

    public static AuthDataDao getAuthDataDao() {
        return authDataDao;
    }

    public static Student getCurrentStudent() { return currentStudent; }
    public static void setCurrentStudent(Student student) { currentStudent = student;}

//    public static Teacher getCurrentTeacher() { return currentTeacher; }
//    public static void setCurrentTeacher(Teacher teacher) { currentTeacher = teacher;}

    public static Integer getTypeOfUser() {
        if (currentStudent != null)
            return 2; // student
//        else if (currentTeacher != null)
//            return 1; // teacher
        else
            return 0; // superuser
    }

    public static boolean getDebugModeEnable() {
        return debugModeEnable;
    }
}
