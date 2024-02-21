package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Teacher;
import com.example.lab2.objects.references.TeacherDiscipline;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TeacherDisciplineDao extends DataAccessObject<TeacherDiscipline> {
    @Override
    public boolean contains(TeacherDiscipline entity) {
        TeacherDiscipline teacherDiscipline = null;
        try {
            TypedQuery<TeacherDiscipline> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE discipline.name = '" + entity.getDiscipline().getName() +
                    "' AND discipline.typeOfMark.name = '" + entity.getDiscipline().getTypeOfMark().getName() +
                    "' AND teacher.lastName = '" + entity.getTeacher().getLastName() +
                    "' AND teacher.firstName = '" + entity.getTeacher().getFirstName() +
                    "' AND teacher.patronymic = '" + entity.getTeacher().getPatronymic() +
                    "' AND teacher.department.name = '" + entity.getTeacher().getDepartment().getName() +
                    "' AND teacher.post.name = '" + entity.getTeacher().getPost().getName() +
                    "' AND teacher.address.city = '" + entity.getTeacher().getAddress().getCity() +
                    "' AND teacher.address.street = '" + entity.getTeacher().getAddress().getStreet() +
                    "' AND teacher.address.houseNumber = '" + entity.getTeacher().getAddress().getHouseNumber() +
                    "' AND teacher.address.apartmentNumber = " + entity.getTeacher().getAddress().getApartmentNumber(),
                    getType());
            teacherDiscipline = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return teacherDiscipline != null;
    }
    public TeacherDiscipline findByField(Teacher teacher, Discipline discipline) {
        TeacherDiscipline teacherDiscipline = null;
        try {
            TypedQuery<TeacherDiscipline> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE discipline.name = '" + discipline.getName() +
                            "' AND discipline.typeOfMark.name = '" + discipline.getTypeOfMark().getName() +
                            "' AND teacher.lastName = '" + teacher.getLastName() +
                            "' AND teacher.firstName = '" + teacher.getFirstName() +
                            "' AND teacher.patronymic = '" + teacher.getPatronymic() +
                            "' AND teacher.department.name = '" + teacher.getDepartment().getName() +
                            "' AND teacher.post.name = '" + teacher.getPost().getName() +
                            "' AND teacher.address.city = '" + teacher.getAddress().getCity() +
                            "' AND teacher.address.street = '" + teacher.getAddress().getStreet() +
                            "' AND teacher.address.houseNumber = '" + teacher.getAddress().getHouseNumber() +
                            "' AND teacher.address.apartmentNumber = " + teacher.getAddress().getApartmentNumber(),
                    getType());
            teacherDiscipline = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return teacherDiscipline;
    }

    public boolean contains(Teacher teacher, Discipline discipline) {
        TeacherDiscipline teacherDiscipline = null;
        try {
            TypedQuery<TeacherDiscipline> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE discipline.name = '" + discipline.getName() +
                            "' AND discipline.typeOfMark.name = '" + discipline.getTypeOfMark().getName() +
                            "' AND teacher.lastName = '" + teacher.getLastName() +
                            "' AND teacher.firstName = '" + teacher.getFirstName() +
                            "' AND teacher.patronymic = '" + teacher.getPatronymic() +
                            "' AND teacher.department.name = '" + teacher.getDepartment().getName() +
                            "' AND teacher.post.name = '" + teacher.getPost().getName() +
                            "' AND teacher.address.city = '" + teacher.getAddress().getCity() +
                            "' AND teacher.address.street = '" + teacher.getAddress().getStreet() +
                            "' AND teacher.address.houseNumber = '" + teacher.getAddress().getHouseNumber() +
                            "' AND teacher.address.apartmentNumber = " + teacher.getAddress().getApartmentNumber(),
                    getType());
            teacherDiscipline = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return teacherDiscipline != null;
    }



    @Override
    protected Class<TeacherDiscipline> getType() {
        return TeacherDiscipline.class;
    }
}
