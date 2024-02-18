package com.example.lab2.objects.old;

import jakarta.persistence.*;

@Entity
@Table(name="statuses")
public class Status {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

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
