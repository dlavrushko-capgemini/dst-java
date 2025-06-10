package dasniko.soteria.controller;

import dasniko.soteria.service.UserService;
import dasniko.soteria.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.inject.Inject;

@Controller
@Path("/login")
public class LoginController {

    @Inject
    Models models;

    @Inject
    UserService userService;

    @Inject
    HttpServletRequest request;

    @Inject
    HttpServletResponse response;

    @POST
    public String login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            models.put("error", "Username and password must not be empty.");
            return "login";
        }

        if (!userService.authenticate(username, password)) {
            models.put("error", "Invalid username or password.");
            return "login";
        }

        User user = userService.findByUsername(username);
        request.getSession(true).setAttribute("user", user);
        return "redirect:/dashboard";
    }

    @GET
    @Path("/logout")
    public String logout() {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}