package com.example.lab2.objects.references;

import com.example.lab2.objects.global.CustomObject;
import com.example.lab2.objects.main.Group;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="teacher_group")
public class TeacherGroup extends CustomObject {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="teacher_discipline_id")
    private TeacherDiscipline teacherDiscipline;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="group_id")
    private Group group;
    @Column(name="semester")
    private int semester;

    public TeacherGroup() {}

    public TeacherGroup(TeacherDiscipline teacherDiscipline, Group group) {
        this.teacherDiscipline = teacherDiscipline;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeacherDiscipline getTeacherDiscipline() {
        return teacherDiscipline;
    }

    public void setTeacherDiscipline(TeacherDiscipline teacherDiscipline) {
        this.teacherDiscipline = teacherDiscipline;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return getTeacherDiscipline().toString() + " " + getGroup().toString();
    }

    @Override
    public String toStringFields() {
        return getTeacherDiscipline().toStringFields() + " " + getGroup().toStringFields();
    }
}
