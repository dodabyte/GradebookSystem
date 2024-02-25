package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.main.Department;
import jakarta.persistence.TypedQuery;

public class DepartmentsDao extends DataAccessObject<Department> {
    @Override
    public boolean contains(Department entity) {
        Department departments = null;
        try {
            TypedQuery<Department> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE name = '" + entity.getName() + "'", getType());
            departments = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return departments != null;
    }

    @Override
    protected Class<Department> getType() {
        return Department.class;
    }
}
