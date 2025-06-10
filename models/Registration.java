package models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Length;
import javax.validation.constraints.NotBlank;

public class Registration {

    @NotBlank
    @Length(min = 3, max = 30)
    private String username;

    @NotBlank
    @Length(min = 8, max = 64)
    private String password;

    @NotBlank
    @Email
    @Length(min = 6, max = 255)
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