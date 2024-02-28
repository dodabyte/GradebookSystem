package com.example.lab2.dao.global;

import com.example.lab2.hibernate.HibernateUtils;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Expression;

import java.util.List;

public abstract class DataAccessObject<T>{
    public DataAccessObject() {
        HibernateUtils.getEntityManager();
    }

    public List<T> findAll() {
        TypedQuery<T> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                " order by id", getType());
        List<T> list = typedQuery.getResultList();
        HibernateUtils.getEntityManager().close();
        return list;
    }

    public List<T> findByField(String parameter, Object object) {
        TypedQuery <T> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                " WHERE " + parameter + " = " + object + " order by id", getType());
        List<T> list = typedQuery.getResultList();
        HibernateUtils.getEntityManager().close();
        return list;
    }

    public List<T> findAllWithOrder(String order) {
        TypedQuery<T> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                " order by " + order, getType());
        List<T> list = typedQuery.getResultList();
        HibernateUtils.getEntityManager().close();
        return list;
    }

    public void insert(T entity) {
        try {
            HibernateUtils.getTransaction().begin();
            HibernateUtils.getEntityManager().persist(entity);
            HibernateUtils.getTransaction().commit();
        } finally {
            if (HibernateUtils.getTransaction().isActive()){
                HibernateUtils.getTransaction().rollback();
            }
            HibernateUtils.getEntityManager().close();
        }
    }

    public void update(T entity) {
        try {
            HibernateUtils.getTransaction().begin();
            HibernateUtils.getEntityManager().merge(entity);
            HibernateUtils.getTransaction().commit();
        } finally {
            if (HibernateUtils.getTransaction().isActive()){
                HibernateUtils.getTransaction().rollback();
            }
            HibernateUtils.getEntityManager().close();
        }
    }

    public void delete(T entity) {
        try {
            HibernateUtils.getTransaction().begin();
            HibernateUtils.getEntityManager().remove(HibernateUtils.getEntityManager().contains(entity) ?
                    entity : HibernateUtils.getEntityManager().merge(entity));
            HibernateUtils.getTransaction().commit();
        } finally {
            if (HibernateUtils.getTransaction().isActive()){
                HibernateUtils.getTransaction().rollback();
            }
            HibernateUtils.getEntityManager().close();
        }
    }

    public abstract boolean contains(T entity);

    protected abstract Class<T> getType();

    protected String getTableName() {
        return getType().getSimpleName();
    }
}
