package com.example.lab2.objects.main;

import com.example.lab2.objects.global.CustomObject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="disciplines")
public class Discipline extends CustomObject {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @ManyToOne (fetch=FetchType.EAGER,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="type_of_marks_id")
    private TypeOfMark typeOfMark;

    public Discipline() {}

    public Discipline(String name, TypeOfMark typeOfMark) {
        this.name = name;
        this.typeOfMark = typeOfMark;
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

    public TypeOfMark getTypeOfMark() { return typeOfMark; }

    public void setTypeOfMark(TypeOfMark typeOfMark) { this.typeOfMark = typeOfMark; }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String toStringFields() {
        return toString();
    }
}
