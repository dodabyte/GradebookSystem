package com.example.lab2.objects.main;

import com.example.lab2.objects.global.CustomObject;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="groups")
public class Group extends CustomObject {
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
    @Column(name="date_formation")
    private Date dateFormation;
    @Column(name="date_graduation")
    private Date dateGraduation;

    public Group() {}

    public Group(String name, int course, int semester,
                 Specialization specialization, Date dateFormation, Date dateGraduation) {
        this.name = name;
        this.course = course;
        this.semester = semester;
        this.specialization = specialization;
        this.dateFormation = dateFormation;
        this.dateGraduation = dateGraduation;
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

    public Date getDateFormation() {
        return dateFormation;
    }

    public void setDateFormation(Date dateFormation) { this.dateFormation = dateFormation; }

    public Date getDateGraduation() {
        return dateGraduation;
    }

    public void setDateGraduation(Date dateGraduation) { this.dateGraduation = dateGraduation; }

    @Override
    public String toString() {
        return getName();
    }

    public String toStringFields() {
        return getName() + " " + getCourse() + " " + getSemester() + " " + getSpecialization().toStringFields()
                + " " + getDateFormation() + " " + getDateGraduation();
    }
}
