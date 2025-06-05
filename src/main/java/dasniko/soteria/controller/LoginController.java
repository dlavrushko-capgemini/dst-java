package dasniko.soteria.controller;

import dasniko.soteria.model.Login;

import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.mvc.annotation.Controller;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ValidationError;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

import java.util.List;
import java.util.stream.Collectors;

@Path("login")
@Controller
public class LoginController {

    @Inject
    private Models models;
    @Inject
    private SecurityContext securityContext;
    @Inject
    private BindingResult bindingResult;
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;

    @GET
    public String index() {
        return "login.jsp";
    }

    @POST
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String login(@Valid @BeanParam Login login) {
        if (bindingResult.isFailed()) {
            var errors = bindingResult.getAllValidationErrors().stream()
                .map(ValidationError::getMessage)
                .collect(Collectors.toList());
            models.put("errors", errors);
            return index();
        }

        Credential credential = new UsernamePasswordCredential(login.getUsername(), new Password(login.getPassword()));

        var authStatus = securityContext.authenticate(request, response,
            AuthenticationParameters.withParams()
                .credential(credential)
                .newAuthentication(true)
                .rememberMe(login.isRememberMe())
        );

        if (authStatus.equals(AuthenticationStatus.SUCCESS)) {
            return "redirect:user";
        } else if (authStatus.equals(AuthenticationStatus.SEND_FAILURE)) {
            models.put("errors", "Error during authentication: username and/or password not correct.");
            return index();
        } else {
            models.put("errors", "Unexpected error during authentication: " + authStatus.name());
            return index();
        }
    }
}