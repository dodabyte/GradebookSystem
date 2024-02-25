package com.example.lab2.objects.main;

import com.example.lab2.objects.global.CustomObject;
import jakarta.persistence.*;

@Entity
@Table(name="types_of_marks")
public class TypeOfMark extends CustomObject {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    public TypeOfMark() {}

    public TypeOfMark(String name) {
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

    public String toStringFields() {
        return toString();
    }
}
