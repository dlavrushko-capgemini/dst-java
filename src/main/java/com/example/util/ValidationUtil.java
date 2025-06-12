package com.example.util;

import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationUtil {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;

    public static String isValidEmail(String email) {
        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            return "Invalid email format.";
        }
        return null;
    }

    public static String isUniqueUsername(String username, Connection connection) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return "Username already exists.";
            }
        } catch (SQLException e) {
            return "Database error occurred.";
        }
        return null;
    }

    public static String isValidPassword(String password) {
        if (password == null) {
            return "Password cannot be null.";
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return "Password must be at least " + MIN_PASSWORD_LENGTH + " characters long.";
        }
        if (!password.chars().anyMatch(Character::isDigit)) {
            return "Password must contain at least one digit.";
        }
        if (!password.chars().anyMatch(Character::isLowerCase)) {
            return "Password must contain at least one lowercase letter.";
        }
        if (!password.chars().anyMatch(Character::isUpperCase)) {
            return "Password must contain at least one uppercase letter.";
        }
        return null;
    }
}