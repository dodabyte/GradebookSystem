package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.TypeOfMark;
import jakarta.persistence.TypedQuery;

public class TypesOfMarksDao extends DataAccessObject<TypeOfMark> {
    @Override
    public boolean contains(TypeOfMark entity) {
        TypeOfMark typeOfMark = null;
        try {
            TypedQuery<TypeOfMark> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE name = '" + entity.getName() + "'",
                    getType());
            typeOfMark = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return typeOfMark != null;
    }

    @Override
    protected Class<TypeOfMark> getType() {
        return TypeOfMark.class;
    }
}
