package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.SemesterPerformance;
import jakarta.persistence.TypedQuery;

public class SemesterPerformanceDao extends DataAccessObject<SemesterPerformance> {
    @Override
    public boolean contains(SemesterPerformance entity) {
        SemesterPerformance semesterPerformance = null;
        try {
            TypedQuery<SemesterPerformance> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
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
                            "' AND course = " + entity.getCourse() +
                            " AND semester = " + entity.getSemester() +
                            " AND discipline.name = '" + entity.getDiscipline().getName() +
                            "' AND discipline.typeOfMark.name = '" + entity.getDiscipline().getTypeOfMark().getName() + "'" +
                            " AND mark = " + entity.getMark(),
                    getType());
            semesterPerformance = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return semesterPerformance != null;
    }

    @Override
    protected Class<SemesterPerformance> getType() {
        return SemesterPerformance.class;
    }
}
