package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.AuthData;
import jakarta.persistence.TypedQuery;

public class AuthDataDao extends DataAccessObject<AuthData> {

    @Override
    public boolean contains(AuthData entity) {
        AuthData authData = null;
        try {
            TypedQuery<AuthData> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE email = '" + entity.getEmail() +
                            "' AND password = '" + entity.getPassword() + "'",
                            getType());
            authData = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return authData != null;
    }

    public AuthData findByFields(String email, String password) {
        AuthData authData = null;
        try {
            TypedQuery<AuthData> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "email = '" + email +
                            "' AND password = '" + password + "'",
                            getType());
            authData = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return authData;
    }

    @Override
    protected Class<AuthData> getType() { return AuthData.class; }
}
