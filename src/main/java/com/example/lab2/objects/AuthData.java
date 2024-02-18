package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="auth_data")
public class AuthData {
    @Id
    @GeneratedValue(generator = "increment") // TODO foreign key by student_id or teacher_id
    @Column(name="id")
    private int id;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
//    @OneToOne
//    @JoinColumn(name = "teacher_id")
//    public Teacher teacher;

    public AuthData() {}

    public AuthData(String email, String password, Student student/*, Teacher teacher*/) {
        this.email = email;
        this.password = password;
        this.student = student;
//        this.teacher = teacher;
    }

    public Student getStudent() { return student; }
    //public Teacher getTeacher() { return teacher; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getTypeOfUser() {
        if (student != null)
            return 2; // student
//        else if (teacher != null)
//            return 1; // teacher
        else
            return 0; // superuser
    }
}
