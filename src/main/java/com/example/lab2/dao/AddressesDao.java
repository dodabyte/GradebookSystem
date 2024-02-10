package com.example.lab2.dao;

import com.example.lab2.dao.global.DataAccessObject;
import com.example.lab2.hibernate.HibernateUtils;
import com.example.lab2.objects.Address;
import jakarta.persistence.TypedQuery;

public class AddressesDao extends DataAccessObject<Address> {
    @Override
    public boolean contains(Address entity) {
        Address address = null;
        try {
            TypedQuery<Address> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "city = '" + entity.getCity() +
                            "' AND street = '" + entity.getStreet() +
                            "' AND houseNumber = '" + entity.getHouseNumber() +
                            "' AND apartmentNumber = " + entity.getApartmentNumber(),
                    getType());
            address = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {
            HibernateUtils.getEntityManager().close();
        }
        return address != null;
    }

    public Address findByFields(String city, String street, String houseNumber, int apartmentNumber) {
        Address address = null;
        try {
            TypedQuery<Address> typedQuery = HibernateUtils.getEntityManager().createQuery("FROM " + getTableName() +
                            " WHERE " +
                            "city = '" + city +
                            "' AND street = '" + street +
                            "' AND houseNumber = '" + houseNumber +
                            "' AND apartmentNumber = " + apartmentNumber,
                    getType());
            address = typedQuery.getSingleResult();
            HibernateUtils.getEntityManager().close();
        }
        catch (Exception ignored) {}
        return address;
    }

    @Override
    protected Class<Address> getType() {
        return Address.class;
    }
}
