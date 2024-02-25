package com.example.lab2.objects.main;

import com.example.lab2.objects.global.CustomObject;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="students")
public class Student extends CustomObject {
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
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name="form_of_education_id")
    private FormOfEducation formOfEducation;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name="basis_of_education_id")
    private BasisOfEducation basisOfEducation;
    @Column(name="date_admission")
    private Date dateAdmission;
    @OneToOne(mappedBy = "student")
    private AuthData authData;

    public Student() {}

    public Student(String lastName, String firstName, String patronymic, Address address,
                   Date dateOfBirth, Group group, FormOfEducation formOfEducation,
                   BasisOfEducation basisOfEducation, Date dateAdmission) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.group = group;
        this.formOfEducation = formOfEducation;
        this.basisOfEducation = basisOfEducation;
        this.dateAdmission = dateAdmission;
    }

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

    public FormOfEducation getFormOfEducation() { return formOfEducation; }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public BasisOfEducation getBasisOfEducation() {
        return basisOfEducation;
    }

    public void setBasisOfEducation(BasisOfEducation basisOfEducation) {
        this.basisOfEducation = basisOfEducation;
    }

    public Date getDateAdmission() { return dateAdmission; }

    public void setDateAdmission(Date dateAdmission) { this.dateAdmission = dateAdmission; }

    public AuthData getAuthData() { return authData; }

    public void setAuthData(AuthData authData) { this.authData = authData; }

    @Override
    public String toString() {
        return getLastName() + " " + getFirstName() + " " + getPatronymic();
    }

    public String toStringFields() {
        return getLastName() + " " + getFirstName() + " " + getPatronymic() + " " +
                getAddress().toStringFields() + " " + getDateOfBirth() + " " + getGroup().toStringFields() + " " +
                getFormOfEducation() + " " + getBasisOfEducation() + " " + getDateAdmission();
    }
}
