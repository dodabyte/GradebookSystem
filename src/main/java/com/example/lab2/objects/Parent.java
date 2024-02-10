package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="parents")
public class Parent {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="student_id")
    private Student student;
    @Column(name="last_name")
    private String lastName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="patronymic")
    private String patronymic;
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="address_id")
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return getLastName() + " " + getFirstName() + " " + getPatronymic();
    }

    public String toStringFields() {
        return getStudent().toStringFields() + " " + toString() + " " + getAddress().toStringFields();
    }
}
