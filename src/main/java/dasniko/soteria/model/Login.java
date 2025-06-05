package dasniko.soteria.model;

import lombok.Data;

import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

@Data
public class Login {
    @Size(min = 1, message = "Please enter a valid username.")
    @FormParam("username")
    private String username;
    @Size(min = 1, message = "Please enter a valid password.")
    @FormParam("password")
    private String password;
    @FormParam("rememberMe")
    private String rememberMe;

    public boolean isRememberMe() {
        return rememberMe != null && rememberMe.equals("rememberMe");
    }
}