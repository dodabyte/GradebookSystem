package com.example.lab2.converters;

import com.example.lab2.AppManager;
import com.example.lab2.objects.main.Teacher;
import com.example.lab2.objects.references.TeacherDiscipline;
import javafx.util.StringConverter;

import java.util.List;

public class TeacherDisciplineConverter extends StringConverter<TeacherDiscipline> {
    @Override
    public String toString(TeacherDiscipline teacherDiscipline) {
        if (teacherDiscipline == null) return null;
        return teacherDiscipline.toString();
    }

    @Override
    public TeacherDiscipline fromString(String string) {
        for (TeacherDiscipline teacherDiscipline : AppManager.getTeacherDisciplineDao().findAll()) {
            if (teacherDiscipline.toString().equals(string)) {
                return teacherDiscipline;
            }
        }
        return null;
    }
}
