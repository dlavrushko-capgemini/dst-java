package dasniko.soteria.controller;

import dasniko.soteria.entity.Account;
import dasniko.soteria.entity.AccountService;

import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.mvc.annotation.Controller;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.Optional;

@Path("user")
@Controller
public class UserController {

    @Inject
    private Models models;
    @Inject
    private SecurityContext securityContext;
    @Inject
    private AccountService accountService;

    @GET
    public String index() {
        String name = securityContext.getCallerPrincipal().getName();
        Optional<Account> account = accountService.getByUsername(name);
        account.ifPresent(account1 -> models.put("account", account1));
        return "user.jsp";
    }

    @GET
    @Path("logout")
    public String logout(@Context HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:user";
    }
}