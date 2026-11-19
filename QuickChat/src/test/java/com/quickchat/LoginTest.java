package com.quickchat;



import com.quickchat.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    Login l = new Login();

    @Test void testUsername() {
        assertTrue(l.checkUserName("kyl_1"));
        assertFalse(l.checkUserName("kyle!!!!!!"));
    }
    @Test void testPassword() {
        assertTrue(l.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(l.checkPasswordComplexity("password"));
    }
    @Test void testCell() {
        assertTrue(l.checkCellPhoneNumber("+27838968976"));
        assertFalse(l.checkCellPhoneNumber("08966553"));
    }
    @Test void testRegisterLogin() {
        l.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(l.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertFalse(l.loginUser("kyl_1", "wrong"));
        assertEquals("Welcome Kyle, Smith it is great to see you again.", l.returnLoginStatus(true));
    }
}