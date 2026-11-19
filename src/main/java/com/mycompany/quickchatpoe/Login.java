package com.quickchat;

public class Login {

    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    private String enteredUsername;
    private String enteredPassword;

    private boolean loginSuccessful;

    // Constructor
    public Login() {
        username = "";
        password = "";
        cellPhoneNumber = "";
        firstName = "";
        lastName = "";
        enteredUsername = "";
        enteredPassword = "";
        loginSuccessful = false;
    }

    // Store registration details
    public void setRegistrationDetails(
            String username,
            String password,
            String cellPhoneNumber,
            String firstName,
            String lastName) {

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Check username
    public boolean checkUserName() {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

 
public boolean checkPasswordComplexity() {

    if (password == null) {
        return false;
    }

    boolean hasEightCharacters = password.length() >= 8;
    boolean hasUppercase = password.matches(".*[A-Z].*");
    boolean hasNumber = password.matches(".*[0-9].*");
    boolean hasSpecialCharacter = password.matches(".*[^a-zA-Z0-9].*");

    return hasEightCharacters
            && hasUppercase
            && hasNumber
            && hasSpecialCharacter;
}

    // Check cellphone number
    public boolean checkCellPhoneNumber() {

        return cellPhoneNumber != null
                && cellPhoneNumber.matches("^\\+27[0-9]{9}$");
    }

    // Register user
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }

        return "Registration successful.";
    }

    // Store login details
    public void setLoginDetails(
            String enteredUsername,
            String enteredPassword) {

        this.enteredUsername = enteredUsername;
        this.enteredPassword = enteredPassword;
    }

    // Verify login details
    public boolean loginUser() {

        loginSuccessful = username != null
                && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);

        return loginSuccessful;
    }

    // Return login status
    public String returnLoginStatus() {

        if (loginSuccessful) {
            return "Welcome " + firstName + " " + lastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}