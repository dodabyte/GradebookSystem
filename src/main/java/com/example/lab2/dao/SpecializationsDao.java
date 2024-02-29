package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Specialization;
import jakarta.persistence.TypedQuery;

public class SpecializationsDao extends DataAccessObject<Specialization> {
    @Override
    public boolean contains(Specialization entity) {
        Specialization specialization = null;
        try {
            TypedQuery<Specialization> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE number = '" + entity.getNumber() +
                    "' AND name = '" + entity.getName() +
                    "' AND studyDuration = " + entity.getStudyDuration(),
                    getType());
            specialization = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return specialization != null;
    }

    public Specialization findByFields(String number, String name, int studyDuration) {
        Specialization specialization = null;
        try {
            TypedQuery<Specialization> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "number = '" + number +
                            "' AND name = '" + name +
                            "' AND studyDuration = " + studyDuration,
                    getType());
            specialization = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return specialization;
    }

    public int findMaxStudyDuration() {
        int studyDuration = 4;
        try {
            TypedQuery<Integer> typedQuery = HibernateUtils.getEntityManager().createQuery("SELECT MAX(studyDuration) FROM " +
                            getTableName(),
                    Integer.class);
            studyDuration = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return studyDuration;
    }

    @Override
    protected Class<Specialization> getType() {
        return Specialization.class;
    }
}
