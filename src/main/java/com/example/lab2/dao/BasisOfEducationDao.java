package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.BasisOfEducation;
import jakarta.persistence.TypedQuery;

public class BasisOfEducationDao extends DataAccessObject<BasisOfEducation> {
    @Override
    public boolean contains(BasisOfEducation entity) {
        BasisOfEducation basisOfEducation = null;
        try {
            TypedQuery<BasisOfEducation> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE name = '" + entity.getName() +
                    "'", getType());
            basisOfEducation = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return basisOfEducation != null;
    }

    @Override
    protected Class<BasisOfEducation> getType() {
        return BasisOfEducation.class;
    }
}
