package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="course")
    private int course;
    @Column(name="semester")
    private int semester;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @JoinColumn(name="specialization_id")
    private Specialization specialization;

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

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String toStringFields() {
        return getName() + " " + getCourse() + " " + getSemester() + " " + getSpecialization().toStringFields();
    }
}