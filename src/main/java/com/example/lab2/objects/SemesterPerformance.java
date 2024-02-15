package com.example.lab2.objects;

import com.example.lab2.utils.MarksUtils;
import jakarta.persistence.*;

@Entity
@Table(name="semester_performance")
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
    @Column(name="mark")
    private int mark;
    private String ectsMark;

    private int traditionalMark;

    private String traditionalWordMark;

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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getEctsMark() {
        return ectsMark;
    }

    public void setEctsMark(String ectsMark) {
        this.ectsMark = ectsMark;
    }

    public int getTraditionalMark() {
        return traditionalMark;
    }

    public void setTraditionalMark(int traditionalMark) {
        this.traditionalMark = traditionalMark;
    }

    public String getTraditionalWordMark() {
        return traditionalWordMark;
    }

    public void setTraditionalWordMark(String traditionalWordMark) {
        this.traditionalWordMark = traditionalWordMark;
    }

    public String toStringFields() {
        return student.toStringFields() + " " + getCourse() + " " + getSemester() + " " +
                getDiscipline().toStringFields() + " " + getMark() + " " + MarksUtils.getEctsMark(getMark()) + " " +
                MarksUtils.getTraditionalMark(getMark()) + " " +
                MarksUtils.getTraditionalWordMark(getMark(), getDiscipline().getTypeOfMark().getName());
    }
}
