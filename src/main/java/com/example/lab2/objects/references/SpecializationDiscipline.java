package com.example.lab2.objects.references;

import com.example.lab2.objects.global.CustomObject;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Specialization;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="specialization_discipline")
public class SpecializationDiscipline extends CustomObject {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="specialization_id")
    private Specialization specialization;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="discipline_id")
    private Discipline discipline;

    public SpecializationDiscipline() {}

    public SpecializationDiscipline(Specialization specialization, Discipline discipline) {
        this.specialization = specialization;
        this.discipline = discipline;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Specialization getSpecialization() {return specialization;}

    public void setSpecialization(Specialization specialization) { this.specialization = specialization; }

    public Discipline getDiscipline() { return discipline; }

    public void setDiscipline(Discipline discipline) { this.discipline = discipline; }

    @Override
    public String toString() {
        return getSpecialization().toString() + " " + getDiscipline().toString();
    }

    @Override
    public String toStringFields() {
        return getSpecialization().toStringFields() + " " + getDiscipline().toStringFields();
    }
}
