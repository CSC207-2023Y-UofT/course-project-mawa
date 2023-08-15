package Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for User entity class.
 */
public class TestUser {

    private User user;
    private LocalDate dob;
    private char[] password;

    @BeforeEach
    public void setUp() {
        dob = LocalDate.of(5555, 5, 15);
        password = "pwd".toCharArray();
        user = new User("aaaa", "bbbb", "uuuu", "eeee",
                "mmmm", 10000, 123456789, dob.toString(), password,
                "hhhh", 500f);
    }

    @Test
    public void getEmail() {
        assertEquals("eeee", user.getEmail());
    }

    @Test
    public void getSurname() {
        assertEquals("aaaa", user.getSurname());
    }

    @Test
    public void getFirstname() {
        assertEquals("bbbb", user.getFirstname());
    }

    @Test
    public void getUserNum() {
        assertEquals(10000, user.getUserNum());
    }

    @Test
    public void getPhoneNum() {
        assertEquals(123456789, user.getPhoneNum());
    }

    @Test
    public void setPhoneNum() {
        user.setPhoneNum(0L);
        assertEquals(0L, user.getPhoneNum());
    }

    @Test
    public void getEmployeeName() {
        assertArrayEquals(new String[]{"aaaa", "bbbb"}, user.getEmployeeName());
    }

    @Test
    public void getGender() {
        assertEquals("uuuu", user.getGender());
    }

    @Test
    public void setGender() {
        user.setGender("nnnn");
        assertEquals("nnnn", user.getGender());
    }

    @Test
    public void setEmail() {
        user.setEmail("ummmm");
        assertEquals("ummmm", user.getEmail());
    }

    @Test
    public void getRole() {
        assertEquals("mmmm", user.getRole());
    }

    @Test
    public void getDob() {
        assertEquals(dob, user.getDob());
    }

    @Test
    public void setPay() {
        user.setPay(6f);
        assertEquals(6f, user.getPay());
    }

    @Test
    public void getPay() {
        assertEquals(500f, user.getPay());
    }

    @Test
    public void isActive() {
        assertTrue(user.isActive());
    }

    @Test
    public void setActive() {
        user.setActive(false);
        assertFalse(user.isActive());
    }

    @Test
    public void getPassword() {
        assertArrayEquals(password, user.getPassword());
    }

    @Test
    public void setPassword() {
        char[] newPassword = "z".toCharArray();
        user.setPassword(newPassword);
        assertArrayEquals(newPassword, user.getPassword());
    }

    @Test
    public void getType() {
        assertEquals("hhhh", user.getType());
    }

    @Test
    public void testToString() {
        assertEquals("bbbb aaaa", user.toString());
    }
}
