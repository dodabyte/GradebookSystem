package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Group;
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
                    " AND specialization.number = '" + entity.getSpecialization().getNumber() +
                    "' AND specialization.name = '" + entity.getSpecialization().getName() +
                    "' AND specialization.studyDuration = " + entity.getSpecialization().getStudyDuration(),
                    getType());
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
