package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username and password are required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (!UserService.authenticate(username, password)) {
            if (!UserService.accountExists(username)) {
                request.setAttribute("errorMessage", "Account not found.");
            } else {
                ErrorHandler.logError("Authentication failed for user: " + username);
                request.setAttribute("errorMessage", "Invalid username or password.");
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if ("on".equals(rememberMe)) {
            Cookie cookie = new Cookie("session", username);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        }

        response.sendRedirect("/user/home");
    }
}