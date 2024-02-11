package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.SemesterPerformance;
import jakarta.persistence.TypedQuery;

public class SemesterPerformanceDao extends DataAccessObject<SemesterPerformance> {
    @Override
    public boolean contains(SemesterPerformance entity) {
        SemesterPerformance semesterPerformance = null;
        try {
            TypedQuery<SemesterPerformance> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "student = " + entity.getStudent() +
                            " AND course = " + entity.getCourse() +
                            " AND semester = " + entity.getSemester() +
                            " AND discipline = " + entity.getDiscipline() +
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
