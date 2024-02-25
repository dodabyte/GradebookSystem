package com.example.lab2.objects.main;

import com.example.lab2.objects.global.CustomObject;
import jakarta.persistence.*;

@Entity
@Table (name="addresses")
public class Address extends CustomObject {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="city")
    private String city;
    @Column(name="street")
    private String street;
    @Column(name="house_number")
    private String houseNumber;
    @Column(name="apartment_number")
    private int apartmentNumber;

    public Address() {}

    public Address(String city, String street, String houseNumber, int apartmentNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString() {
        return "г. " + getCity() + " ул. " + getStreet() + " д. " + getHouseNumber() + " кв. " + getApartmentNumber();
    }

    public String toStringFields() {
        return getCity() + " " + getStreet() + " " + getHouseNumber() + " " + getApartmentNumber();
    }
}
