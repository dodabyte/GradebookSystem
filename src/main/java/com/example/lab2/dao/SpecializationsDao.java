package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.Specialization;
import jakarta.persistence.TypedQuery;

public class SpecializationsDao extends DataAccessObject<Specialization> {
    @Override
    public boolean contains(Specialization entity) {
        Specialization specialization = null;
        try {
            TypedQuery<Specialization> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE number = '" + entity.getNumber() +
                    "' AND name = '" + entity.getName() +
                    "'", getType());
            specialization = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return specialization != null;
    }

    @Override
    protected Class<Specialization> getType() {
        return Specialization.class;
    }
}