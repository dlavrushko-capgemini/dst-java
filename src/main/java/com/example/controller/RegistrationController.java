package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.example.service.AccountService;
import com.example.model.Account;
import com.example.util.ValidationUtil;
import com.example.util.PasswordHasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private AccountService accountService = new AccountService();
    private final Lock lock = new ReentrantLock();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lock.lock();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            if (!ValidationUtil.validate(username, password, email)) {
                request.setAttribute("errorMessages", ValidationUtil.getErrorMessages());
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            Account account = new Account();
            account.setUsername(username);
            account.setPassword(PasswordHasher.hash(password));
            account.setEmail(email);

            try {
                if (accountService.createAccount(account)) {
                    response.sendRedirect("registration_thanks.jsp?username=" + username);
                } else {
                    request.setAttribute("errorMessage", "Account creation failed. Please try again.");
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                }
            } catch (Exception e) {
                logger.error("Error creating account", e);
                request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
        } finally {
            lock.unlock();
        }
    }
}