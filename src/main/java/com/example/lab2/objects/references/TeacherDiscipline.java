package com.example.lab2.objects.references;

import com.example.lab2.objects.global.CustomObject;
import com.example.lab2.objects.main.Discipline;
import com.example.lab2.objects.main.Teacher;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="teacher_discipline")
public class TeacherDiscipline extends CustomObject {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="discipline_id")
    private Discipline discipline;

    public TeacherDiscipline() {}

    public TeacherDiscipline(Teacher teacher, Discipline discipline) {
        this.teacher = teacher;
        this.discipline = discipline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return getTeacher().getLastName() + " " + getTeacher().getFirstName().charAt(0) + ". " +
                getTeacher().getPatronymic().charAt(0) + ". - " + getDiscipline().toString();
    }

    public String toStringFields() {
        return getTeacher().toStringFields() + " " + getDiscipline().toStringFields();
    }
}
