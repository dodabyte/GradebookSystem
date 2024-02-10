package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.Group;
import jakarta.persistence.TypedQuery;

public class GroupsDao extends DataAccessObject<Group> {
    @Override
    public boolean contains(Group entity) {
        Group group = null;
        try {
            TypedQuery<Group> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE name = '" + entity.getName() +
                    "' AND course = " + entity.getCourse() +
                    " AND semester = " + entity.getSemester() +
                    " AND specialization = " + entity.getSpecialization(), getType());
            group = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return group != null;
    }
    @Override
    protected Class<Group> getType() {
        return Group.class;
    }
}
