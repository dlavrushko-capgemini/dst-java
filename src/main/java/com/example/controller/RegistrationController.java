package com.example.controller;

import com.example.service.AccountService;
import com.example.model.Registration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    @Inject
    private AccountService accountService;

    @Inject
    private Validator validator;

    private BindingResult bindingResult;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Registration registration = new Registration();
        registration.setUsername(request.getParameter("username"));
        registration.setPassword(request.getParameter("password"));
        registration.setEmail(request.getParameter("email"));

        bindingResult = new org.springframework.validation.BeanPropertyBindingResult(registration, "registration");
        validator.validate(registration, bindingResult);

        if (bindingResult.hasErrors()) {
            request.setAttribute("errors", bindingResult.getAllErrors());
            doGet(request, response);
            return;
        }

        try {
            accountService.save(registration);
            request.setAttribute("account.username", registration.getUsername());
            response.sendRedirect("registration_thanks.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMessage", getRootCause(e).getMessage());
            doGet(request, response);
        }
    }

    private Throwable getRootCause(Throwable e) {
        Throwable cause = e.getCause();
        return (cause != null) ? getRootCause(cause) : e;
    }
}