package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private AccountService accountService;

    public UserServlet() {
        this.accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("getUserInfo".equals(action)) {
            String userId = request.getParameter("userId");
            if (userId == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing userId parameter");
                return;
            }
            User user = accountService.getUserInfo(userId);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }
            response.getWriter().write(user.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username == null || password == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing username or password");
                return;
            }
            accountService.register(username, password);
            response.getWriter().write("User registered successfully");
        } else if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username == null || password == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing username or password");
                return;
            }
            boolean success = accountService.login(username, password);
            if (success) {
                response.getWriter().write("Login successful");
            } else {
                response.getWriter().write("Login failed");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}