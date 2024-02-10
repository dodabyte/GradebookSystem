package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.CurrentPayment;
import jakarta.persistence.TypedQuery;

public class CurrentPaymentsDao extends DataAccessObject<CurrentPayment> {
    @Override
    public boolean contains(CurrentPayment entity) {
        CurrentPayment currentPayment = null;
        try {
            TypedQuery<CurrentPayment> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                    " WHERE amount = " + entity.getAmount() +
                    " AND paymentDate = '" + entity.getPaymentDate() +
                    "' AND docNumber = " + entity.getDocNumber(),
                    getType());
            currentPayment = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return currentPayment != null;
    }
    @Override
    protected Class<CurrentPayment> getType() {
        return CurrentPayment.class;
    }
}
