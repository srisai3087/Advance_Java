package com.example.LMS.model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

public class User {
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum 3 characters")
    private String name;

    @Email(message = "Enter valid email")
    private String email;

    @Size(min = 6, message = "Password must be 6 characters")
    private String password;


    public User(){

    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
