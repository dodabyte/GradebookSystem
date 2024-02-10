package com.example.lab2.objects;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
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
    @Column(name="date_of_birth")
    private Date dateOfBirth;
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="group_id")
    private Group group;
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="learning_conditions_id")
    private LearningCondition learningCondition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LearningCondition getLearningCondition() {
        return learningCondition;
    }

    public void setLearningCondition(LearningCondition learningCondition) {
        this.learningCondition = learningCondition;
    }

    @Override
    public String toString() {
        return getLastName() + " " + getFirstName() + " " + getPatronymic();
    }

    public String toStringFields() {
        return getLastName() + " " + getFirstName() + " " + getPatronymic() + " " +
                getAddress().toStringFields() + " " + getDateOfBirth() + " " + getGroup().toStringFields() + " " +
                getLearningCondition().toStringFields();
    }
}
