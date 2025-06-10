package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        if (rememberMe != null && !userService.authenticate(username, password)) {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            User user = userService.getUserByUsername(username);
            session.setAttribute("user", user);

            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(30 * 24 * 60 * 60);
                response.addCookie(cookie);
            }

            response.sendRedirect(getLandingPage(user.getRole()));
        }
    }

    private String getLandingPage(String role) {
        switch (role) {
            case "ADMIN":
                return "adminDashboard.jsp";
            case "USER":
                return "userDashboard.jsp";
            default:
                return "home.jsp";
        }
    }
}