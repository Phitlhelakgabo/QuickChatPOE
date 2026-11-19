package com.quickchat;

import java.util.regex.Pattern;

public class Login {
    private String storedUsername;
    private String storedPassword;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        return hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) return false;
        return Pattern.matches("^\\+27\\d{1,10}$", cell);
    }

    public String registerUser(String username, String password, String cell, String fn, String ln) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        this.storedUsername = username;
        this.storedPassword = password;
        this.firstName = fn;
        this.lastName = ln;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return storedUsername != null && storedUsername.equals(username) && storedPassword.equals(password);
    }

    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}