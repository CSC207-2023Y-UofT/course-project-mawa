package UseCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import UseCases.LoginValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import UseCases.UserFileReader;

import java.util.ArrayList;

public class TestLoginValidator {

    private UserFileReader mockUserFileReader;

    private class MockUserFileReader extends UserFileReader {

        public MockUserFileReader() {
            super("test");
        }

        @Override
        public char[] getPassword(int id) {
            return "password".toCharArray();
        }

        @Override
        public ArrayList<Integer> getIds(boolean active) {
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(123);
            return ids;
        }
    }
    @BeforeEach
    void setUp() {
        // Create a mock instance of UserFileReader for testing
        mockUserFileReader = new MockUserFileReader();
    }

    @Test
    void testValidCredentials() {
        LoginValidator loginValidator = new LoginValidator();
        loginValidator.empDB = mockUserFileReader;

        int empID = 123;
        char[] pwd = "password".toCharArray();

        int result = loginValidator.validateCredentials(empID, pwd);

        assertEquals(empID, result);
    }

    @Test
    void testInvalidCredentials() {
        LoginValidator loginValidator = new LoginValidator();
        loginValidator.empDB = mockUserFileReader;

        int empID = 123;
        char[] pwd = "wrong_password".toCharArray();

        int result = loginValidator.validateCredentials(empID, pwd);

        assertNotEquals(empID, result);
        assertEquals(-1, result);
    }
}
