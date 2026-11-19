package com.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // Test 1: Valid username
    @Test
    public void testValidUsername() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyl_1",
                "123456T8@",
                "+27838968976",
                "Ceekay",
                "Test"
        );

        assertTrue(user.checkUserName());
    }

    // Test 2: Invalid username
    @Test
    public void testInvalidUsername() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyle!!!!!!!",
                "123456T8@",
                "+27838968976",
                "Ceekay",
                "Test"
        );

        assertFalse(user.checkUserName());
    }

    // Test 3: Valid password
    @Test
    public void testValidPassword() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyl_1",
                "123456T8@",
                "+27838968976",
                "Ceekay",
                "Test"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    // Test 4: Invalid password
    @Test
    public void testInvalidPassword() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyl_1",
                "123456T8",
                "+27838968976",
                "Ceekay",
                "Test"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    // Test 5: Valid cellphone number
    @Test
    public void testValidCellPhoneNumber() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyl_1",
                "123456T8@",
                "+27838968976",
                "Ceekay",
                "Test"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    // Test 6: Invalid cellphone number
    @Test
    public void testInvalidCellPhoneNumber() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyl_1",
                "123456T8@",
                "08966553",
                "Ceekay",
                "Test"
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    // Test 7: Successful login
    @Test
    public void testSuccessfulLogin() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyl_1",
                "123456T8@",
                "+27838968976",
                "Ceekay",
                "Test"
        );

        user.setLoginDetails(
                "kyl_1",
                "123456T8@"
        );

        assertTrue(user.loginUser());
    }

    // Test 8: Unsuccessful login
    @Test
    public void testUnsuccessfulLogin() {
        Login user = new Login();

        user.setRegistrationDetails(
                "kyl_1",
                "123456T8@",
                "+27838968976",
                "Ceekay",
                "Test"
        );

        user.setLoginDetails(
                "wrong",
                "wrongpassword"
        );

        assertFalse(user.loginUser());
    }
}