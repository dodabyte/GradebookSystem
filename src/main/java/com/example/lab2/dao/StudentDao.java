package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.Student;
import jakarta.persistence.TypedQuery;

public class StudentDao extends DataAccessObject<Student>  {
    @Override
    public boolean contains(Student entity) {
        Student student = null;
        try {
        TypedQuery<Student> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                " WHERE lastName = '" + entity.getLastName() +
                "' AND firstName = '" + entity.getFirstName() +
                "' AND patronymic = '" + entity.getPatronymic() +
                "' AND address = " + entity.getAddress() +
                " AND dateOfBirth = '" + entity.getDateOfBirth() +
                "' AND group = " + entity.getGroup() +
                " AND learningCondition = " + entity.getLearningCondition(),
                getType());
        student = typedQuery.getSingleResult();
        HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return student != null;
    }

    @Override
    protected Class<Student> getType() {
        return Student.class;
    }
}
