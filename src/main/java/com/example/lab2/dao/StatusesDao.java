package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.old.Status;
import jakarta.persistence.TypedQuery;

public class StatusesDao extends DataAccessObject<Status> {
    @Override
    public boolean contains(Status entity) {
        Status status = null;
        try {
            TypedQuery<Status> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE name = '" + entity.getName() +
                    "'", getType());
            status = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return status != null;
    }

    @Override
    protected Class<Status> getType() {
        return Status.class;
    }
}
