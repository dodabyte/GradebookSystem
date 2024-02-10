package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="learning_conditions")
public class LearningCondition {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name="form_id")
    private FormOfEducation formOfEducation;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name="basis_id")
    private BasisOfEducation basisOfEducation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public BasisOfEducation getBasisOfEducation() {
        return basisOfEducation;
    }

    public void setBasisOfEducation(BasisOfEducation basisOfEducation) {
        this.basisOfEducation = basisOfEducation;
    }

    @Override
    public String toString() {
        return getFormOfEducation().toString() + " " + getBasisOfEducation().toString();
    }

    public String toStringFields() {
        return toString();
    }
}
