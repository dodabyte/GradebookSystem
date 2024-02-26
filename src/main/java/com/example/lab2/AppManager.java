package com.example.lab2;

import com.example.lab2.dao.*;
import com.example.lab2.objects.main.Student;
import com.example.lab2.localizations.LocalizationLoader;
import com.example.lab2.objects.main.Teacher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppManager {
    private static boolean debugModeEnable = true; // TODO replace with FALSE

    private static Student currentStudent = null;
    private static Teacher currentTeacher = null;

    private static AddressesDao addressesDao = new AddressesDao();
    private static BasisOfEducationDao basisOfEducationDao = new BasisOfEducationDao();
    private static DisciplinesDao disciplinesDao = new DisciplinesDao();
    private static FormsOfEducationDao formsOfEducationDao = new FormsOfEducationDao();
    private static TypesOfMarksDao typesOfMarksDao = new TypesOfMarksDao();
    private static SpecializationsDao specializationsDao = new SpecializationsDao();
    private static GroupsDao groupsDao = new GroupsDao();
    private static StudentDao studentDao = new StudentDao();
    private static ParentDao parentDao = new ParentDao();
    private static SemesterPerformanceDao semesterPerformanceDao = new SemesterPerformanceDao();
    private static DepartmentsDao departmentsDao = new DepartmentsDao();
    private static PostsDao postsDao = new PostsDao();
    private static TeacherDisciplineDao teacherDisciplineDao = new TeacherDisciplineDao();
    private static TeacherGroupDao teacherGroupDao = new TeacherGroupDao();
    private static TeacherDao teacherDao = new TeacherDao();
    private static SpecializationDisciplineDao specializationDisciplineDao = new SpecializationDisciplineDao();
    private static AuthDataDao authDataDao = new AuthDataDao();

    private static LocalizationLoader localizationLoader;

    public AppManager(Stage stage) throws IOException {
        localizationLoader = new LocalizationLoader(LocalizationLoader.RU_BUNDLE_NAME);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorization-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setTitle(localizationLoader.getMessage("Menu.stage.title"));
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

    public static TypesOfMarksDao getTypesOfMarksDao() {
        return typesOfMarksDao;
    }

    public static SpecializationsDao getSpecializationsDao() {
        return specializationsDao;
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

    public static SemesterPerformanceDao getSemesterPerformanceDao() {
        return semesterPerformanceDao;
    }

    public static DepartmentsDao getDepartmentsDao() { return departmentsDao; }

    public static PostsDao getPostsDao() {
        return postsDao;
    }

    public static TeacherDisciplineDao getTeacherDisciplineDao() {
        return teacherDisciplineDao;
    }

    public static TeacherGroupDao getTeacherGroupDao() { return teacherGroupDao; }

    public static TeacherDao getTeacherDao() { return teacherDao; }

    public static SpecializationDisciplineDao getSpecializationDisciplineDao() { return specializationDisciplineDao; }

    public static AuthDataDao getAuthDataDao() {
        return authDataDao;
    }

    public static Student getCurrentStudent() { return currentStudent; }
    public static void setCurrentStudent(Student student) { currentStudent = student;}

    public static Teacher getCurrentTeacher() { return currentTeacher; }
    public static void setCurrentTeacher(Teacher teacher) { currentTeacher = teacher;}

    public static Integer getTypeOfUser() {
        if (currentStudent != null)
            return 2; // student
        else if (currentTeacher != null)
            return 1; // teacher
        else
            return 0; // superuser
    }

    public static boolean getDebugModeEnable() {
        return debugModeEnable;
    }
    
    public static LocalizationLoader getLocalizationLoader() {
        return localizationLoader;
    }
}
