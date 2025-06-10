package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!"GET".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            try {
                session.invalidate();
                logger.info("User logged out successfully.");
            } catch (Exception e) {
                logger.severe("Logout process failed: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Logout failed. Please try again.");
                return;
            }
        } else {
            logger.warning("Logout attempted with no active session.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No active session found.");
            return;
        }

        response.sendRedirect("login.jsp");
    }
}