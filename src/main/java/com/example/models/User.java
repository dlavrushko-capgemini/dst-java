package com.example.models;

import java.util.regex.Pattern;

public class User {
    private String username;
    private String password;
    private String role;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    private static final String[] VALID_ROLES = {"USER", "ADMIN", "MODERATOR"};

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
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and include at least one digit, one lowercase letter, and one uppercase letter.");
        }
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (!isValidRole(role)) {
            throw new IllegalArgumentException("Role must be one of the following: USER, ADMIN, MODERATOR.");
        }
        this.role = role;
    }

    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    private boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean isValidRole(String role) {
        for (String validRole : VALID_ROLES) {
            if (validRole.equals(role)) {
                return true;
            }
        }
        return false;
    }
}