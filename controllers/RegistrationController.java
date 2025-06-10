package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.validation.Valid;
import services.UserService;
import models.Registration;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String index(Model model) {
        model.addAttribute("registration", new Registration());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registration") Registration registration, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userService.saveUser(registration);
            model.addAttribute("account", registration);
            return "registration_thanks";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            return "registration";
        }
    }
}