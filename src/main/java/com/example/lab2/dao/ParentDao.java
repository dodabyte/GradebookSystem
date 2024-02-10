package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.Parent;
import jakarta.persistence.TypedQuery;

public class ParentDao extends DataAccessObject<Parent> {
    @Override
    public boolean contains(Parent entity) {
        Parent parent = null;
        try {
            TypedQuery<Parent> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "student = " + entity.getStudent() +
                            " AND lastName = '" + entity.getLastName() +
                            "' AND firstName = '" + entity.getFirstName() +
                            "' AND patronymic = '" + entity.getPatronymic() +
                            "' AND address = " + entity.getAddress(),
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
