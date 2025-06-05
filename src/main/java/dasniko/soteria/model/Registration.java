package dasniko.soteria.model;

import lombok.Data;

import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

@Data
public class Registration {
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 chars.")
    @FormParam("username")
    private String username;
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 chars.")
    @FormParam("password")
    private String password;
    @Size(min = 6, max = 255, message = "Please enter a valid email address.")
    @FormParam("email")
    private String email;
}