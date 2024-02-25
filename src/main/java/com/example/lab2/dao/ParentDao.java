package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Parent;
import jakarta.persistence.TypedQuery;

public class ParentDao extends DataAccessObject<Parent> {
    @Override
    public boolean contains(Parent entity) {
        Parent parent = null;
        try {
            TypedQuery<Parent> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "student.lastName = '" + entity.getStudent().getLastName() +
                            "' AND student.firstName = '" + entity.getStudent().getFirstName() +
                            "' AND student.patronymic = '" + entity.getStudent().getPatronymic() +
                            "' AND student.address.city = '" + entity.getStudent().getAddress().getCity() +
                            "' AND student.address.street = '" + entity.getStudent().getAddress().getStreet() +
                            "' AND student.address.houseNumber = '" + entity.getStudent().getAddress().getHouseNumber() +
                            "' AND student.address.apartmentNumber = " + entity.getStudent().getAddress().getApartmentNumber() +
                            " AND student.group.name = '" + entity.getStudent().getGroup().getName() +
                            "' AND student.group.course = " + entity.getStudent().getGroup().getCourse() +
                            " AND student.group.semester = " + entity.getStudent().getGroup().getSemester() +
                            " AND student.group.specialization.number = '" + entity.getStudent().getGroup().getSpecialization().getNumber() +
                            "' AND student.group.specialization.name = '" + entity.getStudent().getGroup().getSpecialization().getName() +
                            "' AND student.group.specialization.studyDuration = " + entity.getStudent().getGroup().getSpecialization().getStudyDuration() +
                            " AND student.formOfEducation.name = '" + entity.getStudent().getFormOfEducation().getName() +
                            "' AND student.basisOfEducation.name = '" + entity.getStudent().getBasisOfEducation().getName() +
                            "' AND lastName = '" + entity.getLastName() +
                            "' AND firstName = '" + entity.getFirstName() +
                            "' AND patronymic = '" + entity.getPatronymic() +
                            "' AND address.city = '" + entity.getAddress().getCity()+
                            "' AND address.street = '" + entity.getAddress().getStreet() +
                            "' AND address.houseNumber = '" + entity.getAddress().getHouseNumber() +
                            "' AND address.apartmentNumber = " + entity.getAddress().getApartmentNumber(),
                    getType());


            parent = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return parent != null;
    }

    @Override
    protected Class<Parent> getType() {
        return Parent.class;
    }
}
