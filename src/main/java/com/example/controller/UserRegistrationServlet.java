package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.service.UserService;
import com.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        UserService userService = new UserService();
        try {
            if (userService.isUsernameTaken(username)) {
                request.setAttribute("errorMessage", "Username is already taken.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
            
            if (!userService.isValidPassword(password)) {
                request.setAttribute("errorMessage", "Password does not meet criteria.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
            
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            
            userService.saveUser(user);
            response.sendRedirect("registration_thanks.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}