package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="specializations")
public class Specialization {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="number")
    private String number;
    @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getNumber() + " " + getName();
    }

    public String toStringFields() {
        return toString();
    }
}
