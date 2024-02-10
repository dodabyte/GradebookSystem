package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.FormOfEducation;
import jakarta.persistence.TypedQuery;

public class FormsOfEducationDao extends DataAccessObject<FormOfEducation> {
    @Override
    public boolean contains(FormOfEducation entity) {
        FormOfEducation formOfEducation = null;
        try {
        TypedQuery<FormOfEducation> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                " WHERE name = '" + entity.getName() +
                "'", getType());
        formOfEducation = typedQuery.getSingleResult();
        HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return formOfEducation != null;
    }

    @Override
    protected Class<FormOfEducation> getType() {
        return FormOfEducation.class;
    }
}
