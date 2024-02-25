package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.references.TeacherDiscipline;
import jakarta.persistence.TypedQuery;

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

    @Override
    protected Class<TeacherDiscipline> getType() {
        return TeacherDiscipline.class;
    }
}
