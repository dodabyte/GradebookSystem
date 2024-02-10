package com.example.lab2.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class HibernateUtils {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction transaction;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null)
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen())
            entityManager = getEntityManagerFactory().createEntityManager();
        return entityManager;
    }

    public static EntityTransaction getTransaction() {
        if (transaction == null || !transaction.isActive())
            transaction = getEntityManager().getTransaction();
        return transaction;
    }
}
