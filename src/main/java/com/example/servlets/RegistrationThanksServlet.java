package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet("/registration_thanks")
public class RegistrationThanksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            request.setAttribute("account.username", "Guest");
        } else {
            request.setAttribute("account.username", username);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration_thanks.jsp");
        dispatcher.forward(request, response);
    }
}