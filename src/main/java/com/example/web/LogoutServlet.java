package com.example.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private static final String SESSION = "session";
    private static final String REDIRECT_URL = "logout.jsp";
    private static final String LOGOUT_MESSAGE = "You have been logged out successfully.";
    private static final long TIMEOUT = 2000;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            request.setAttribute("message", LOGOUT_MESSAGE);
        } else {
            request.setAttribute("message", "No active session found.");
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime < TIMEOUT) {
            try {
                Thread.sleep(TIMEOUT - elapsedTime);
            } catch (InterruptedException e) {
                request.setAttribute("message", "Logout process interrupted.");
            }
        }
        response.sendRedirect(REDIRECT_URL);
    }
}