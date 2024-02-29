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
    private static final boolean debugModeEnable = true; // TODO replace with FALSE

    private static Student currentStudent = null;
    private static Teacher currentTeacher = null;

    private static final AddressesDao addressesDao = new AddressesDao();
    private static final BasisOfEducationDao basisOfEducationDao = new BasisOfEducationDao();
    private static final DisciplinesDao disciplinesDao = new DisciplinesDao();
    private static final FormsOfEducationDao formsOfEducationDao = new FormsOfEducationDao();
    private static final TypesOfMarksDao typesOfMarksDao = new TypesOfMarksDao();
    private static final SpecializationsDao specializationsDao = new SpecializationsDao();
    private static final GroupsDao groupsDao = new GroupsDao();
    private static final StudentDao studentDao = new StudentDao();
    private static final ParentDao parentDao = new ParentDao();
    private static final SemesterPerformanceDao semesterPerformanceDao = new SemesterPerformanceDao();
    private static final DepartmentsDao departmentsDao = new DepartmentsDao();
    private static final PostsDao postsDao = new PostsDao();
    private static final TeacherDisciplineDao teacherDisciplineDao = new TeacherDisciplineDao();
    private static final TeacherGroupDao teacherGroupDao = new TeacherGroupDao();
    private static final TeacherDao teacherDao = new TeacherDao();
    private static final SpecializationDisciplineDao specializationDisciplineDao = new SpecializationDisciplineDao();
    private static final AuthDataDao authDataDao = new AuthDataDao();

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

    public static TeacherDisciplineDao getTeacherDisciplineDao() { return teacherDisciplineDao; }

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
