package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Teacher;
import com.example.lab2.objects.references.TeacherDiscipline;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TeacherDao extends DataAccessObject<Teacher> {
    @Override
    public boolean contains(Teacher entity) {
        Teacher teacher = null;
        try {
            TypedQuery<Teacher> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE lastName = '" + entity.getLastName() +
                    "' AND firstName = '" + entity.getFirstName() +
                    "' AND patronymic = '" + entity.getPatronymic() +
                    "' AND department.name = '" + entity.getDepartment().getName() +
                    "' AND post.name = '" + entity.getPost().getName() +
                    "' AND address.city = '" + entity.getAddress().getCity() +
                    "' AND address.street = '" + entity.getAddress().getStreet() +
                    "' AND address.houseNumber = '" + entity.getAddress().getHouseNumber() +
                    "' AND address.apartmentNumber = " + entity.getAddress().getApartmentNumber(),
                    getType());
            teacher = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return teacher != null;
    }

    public List<Teacher> findTeachers(Discipline entity) {
        List<Teacher> teachers = null;
        try {
            TypedQuery<Teacher> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "SELECT t3 FROM " + TeacherDiscipline.class.getSimpleName() + " as t1 " +
                            "JOIN t1.discipline as t2 " +
                            "JOIN t1.teacher as t3 " +
                            "WHERE t2.id = " + entity.getId(),
                    getType());
            teachers = typedQuery.getResultList();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) { ignored.printStackTrace();}
        return teachers;
    }

    @Override
    protected Class<Teacher> getType() {
        return Teacher.class;
    }
}
