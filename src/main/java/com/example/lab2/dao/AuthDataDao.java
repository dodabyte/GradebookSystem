package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.AuthData;
import com.example.lab2.objects.Student;
import jakarta.persistence.TypedQuery;

public class AuthDataDao extends DataAccessObject<AuthData> {

    @Override
    public boolean contains(AuthData entity) {
        AuthData authData = null;
        try {
            TypedQuery<AuthData> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "FROM " + getTableName() + " WHERE email = '" + entity.getEmail() + "'", getType());

            authData = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return authData != null;
    }

    public boolean isContainsAdmin() {
        AuthData authData = null;
        try {
            TypedQuery<AuthData> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "FROM " + getTableName() + " WHERE student IS NULL"/* AND teacher IS NULL"*/, getType());
            authData = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return authData != null;
    }

    public AuthData findByEmail(String email) {
        AuthData authData = null;
        try {
            TypedQuery<AuthData> typedQuery = HibernateUtils.getEntityManager().createQuery(
                    "FROM " + getTableName() + " WHERE email = '" + email + "'", getType());

            authData = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return authData;
    }

    @Override
    protected Class<AuthData> getType() { return AuthData.class; }
}
