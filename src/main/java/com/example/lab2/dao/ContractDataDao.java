package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.ContractData;
import jakarta.persistence.TypedQuery;

public class ContractDataDao extends DataAccessObject<ContractData> {
    @Override
    public boolean contains(ContractData entity) {
        ContractData contractData = null;
        try {
            TypedQuery<ContractData> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "student = " + entity.getStudent() +
                            " AND contractDate = '" + entity.getContractDate() +
                            "' AND paymentAmount = " + entity.getPaymentAmount() +
                            " AND currentPayments = " + entity.getCurrentPayments() +
                            " AND statuses = " + entity.getStatuses(),
                    getType());
            contractData = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return contractData != null;
    }

    @Override
    protected Class<ContractData> getType() {
        return ContractData.class;
    }
}
