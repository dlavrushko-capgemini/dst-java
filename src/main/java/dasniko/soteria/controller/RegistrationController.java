package dasniko.soteria.controller;

import dasniko.soteria.entity.Account;
import dasniko.soteria.entity.AccountService;
import dasniko.soteria.model.Registration;

import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.mvc.annotation.Controller;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ValidationError;
import jakarta.validation.Valid;
import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@Path("registration")
@Controller
public class RegistrationController {

    @Inject
    private Models models;
    @Inject
    private AccountService accountService;
    @Inject
    private BindingResult bindingResult;

    @GET
    public String index() {
        return "registration.jsp";
    }

    @POST
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String register(@Valid @BeanParam Registration registration) {
        if (bindingResult.isFailed()) {
            List<String> errors = bindingResult.getAllValidationErrors().stream()
                .map(ValidationError::getMessage)
                .collect(Collectors.toList());
            models.put("errors", errors);
            return index();
        }

        try {
            Account account = accountService.save(registration.getUsername(),
                registration.getPassword(), registration.getEmail());
            models.put("account", account);
            return "registration_thanks.jsp";
        } catch (Exception e) {
            models.put("errors", getRootCause(e).getMessage());
            return index();
        }
    }

    private Throwable getRootCause(Throwable e) {
        Throwable cause;
        Throwable result = e;
        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}