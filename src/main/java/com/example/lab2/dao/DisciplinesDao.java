package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.Discipline;
import jakarta.persistence.TypedQuery;

public class DisciplinesDao extends DataAccessObject<Discipline> {
    @Override
    public boolean contains(Discipline entity) {
        Discipline discipline = null;
        try {
            TypedQuery<Discipline> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE name = '" + entity.getName() +
                    "' AND typeOfMark = " + entity.getTypeOfMark(), getType());
            discipline = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return discipline != null;
    }

    @Override
    protected Class<Discipline> getType() {
        return Discipline.class;
    }
}
