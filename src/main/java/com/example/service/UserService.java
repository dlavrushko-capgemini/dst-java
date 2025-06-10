package com.example.service;

import com.example.model.User;
import com.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public User findUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM users WHERE username = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                // Set other user properties as needed
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return user;
    }

    public boolean validateUserCredentials(String username, String password) {
        User user = findUserByUsername(username);
        return user != null && user.verifyPassword(password);
    }
}