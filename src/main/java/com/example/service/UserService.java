package com.example.service;

import com.example.dao.UserDAO;
import com.example.model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isUsernameTaken(String username) {
        return userDAO.findByUsername(username) != null;
    }

    public boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*");
    }

    public void saveUser(User user) {
        if (isValidUser(user)) {
            userDAO.save(user);
        }
    }

    private boolean isValidUser(User user) {
        return user != null && 
               user.getUsername() != null && 
               !user.getUsername().trim().isEmpty() && 
               user.getPassword() != null && 
               !user.getPassword().trim().isEmpty() && 
               !isUsernameTaken(user.getUsername()) && 
               isValidPassword(user.getPassword());
    }
}