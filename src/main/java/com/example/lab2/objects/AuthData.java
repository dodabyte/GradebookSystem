package com.example.lab2.objects;

import jakarta.persistence.*;

@Entity
@Table(name="auth_data")
public class AuthData {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="type_of_user")
    private int type_of_user;

    public AuthData() {}

    public AuthData(String email, String password, int typeOfUser) {
        this.email = email;
        this.password = password;
        this.type_of_user = typeOfUser;
    }

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public int getTypeOfUser() { return type_of_user; }

    public void setTypeOfUser(int type_of_user) { this.type_of_user = type_of_user; }
}
