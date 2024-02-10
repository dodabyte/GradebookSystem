package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.LearningCondition;
import jakarta.persistence.TypedQuery;

public class LearningConditionDao extends DataAccessObject<LearningCondition> {
    @Override
    public boolean contains(LearningCondition entity) {
        LearningCondition learningCondition = null;
        try {
        TypedQuery<LearningCondition> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                " WHERE formOfEducation = " + entity.getFormOfEducation() +
                " AND basisOfEducation = " + entity.getBasisOfEducation(),
                getType());
        learningCondition = typedQuery.getSingleResult();
        HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return learningCondition != null;
    }

    @Override
    protected Class<LearningCondition> getType() {
        return LearningCondition.class;
    }
}
