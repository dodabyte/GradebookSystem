package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="disciplines")
public class Discipline {
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

    public String toStringFields() {
        return toString();
    }
}
