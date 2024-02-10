package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="semester_perfomance")
public class SemesterPerformance {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="student_id")
    private Student student;
    @Column(name="course")
    private int course;
    @Column(name="semester")
    private int semester;
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="discipline_id")
    private Discipline discipline;
    @ManyToOne (fetch=FetchType.EAGER,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="type_of_marks_id")
    private TypeOfMark typeOfMark;
    @Column(name="mark")
    private int mark;

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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public TypeOfMark getTypeOfMark() {
        return typeOfMark;
    }

    public void setTypeOfMark(TypeOfMark typeOfMark) {
        this.typeOfMark = typeOfMark;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String toStringFields() {
        return student.toStringFields() + " " + getCourse() + " " + getSemester() + " " +
                getDiscipline().toStringFields() + " " + getTypeOfMark().toStringFields() + " " + getMark();
    }
}
