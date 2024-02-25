package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Student;
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
                "' AND address.city = '" + entity.getAddress().getCity() +
                "' AND address.street = '" + entity.getAddress().getStreet() +
                "' AND address.houseNumber = '" + entity.getAddress().getHouseNumber() +
                "' AND address.apartmentNumber = " + entity.getAddress().getApartmentNumber() +
                " AND group.name = '" + entity.getGroup().getName() +
                "' AND group.course = " + entity.getGroup().getCourse() +
                " AND group.semester = " + entity.getGroup().getSemester() +
                " AND group.specialization.number = '" + entity.getGroup().getSpecialization().getNumber() +
                "' AND group.specialization.name = '" + entity.getGroup().getSpecialization().getName() +
                "' AND group.specialization.studyDuration = " + entity.getGroup().getSpecialization().getStudyDuration() +
                " AND formOfEducation.name = '" + entity.getFormOfEducation().getName() +
                "' AND basisOfEducation.name = '" + entity.getBasisOfEducation().getName() + "'",
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
