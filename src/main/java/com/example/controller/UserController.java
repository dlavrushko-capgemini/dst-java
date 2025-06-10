package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            clearAuthenticationTokens(request);
            response.setStatus(HttpServletResponse.SC_OK);
            return "redirect:/logout.jsp?message=Logout successful";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return "No active session found.";
    }

    private void clearAuthenticationTokens(HttpServletRequest request) {
        // Implementation to clear authentication tokens or cookies
        // Example: request.getCookies() and clear them
    }
}