package com.example.lab2.objects.main;

import com.example.lab2.objects.global.CustomObject;
import jakarta.persistence.*;

@Entity
@Table(name="basis_of_education")
public class BasisOfEducation extends CustomObject {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    public BasisOfEducation() {}

    public BasisOfEducation(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String toStringFields() {
        return toString();
    }
}
