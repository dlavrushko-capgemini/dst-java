package com.example.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Registration {

    @NotNull
    @Size(min = 3, max = 30)
    private String username;

    @NotNull
    @Size(min = 8, max = 64)
    private String password;

    @NotNull
    @Email
    @Size(min = 6, max = 255)
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}