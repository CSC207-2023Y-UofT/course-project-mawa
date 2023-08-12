package InterfaceAdapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import Entities.Shift;
import Entities.User;
import InterfaceAdapters.CalendarModel;
import UseCases.ShiftInteractor;
import UseCases.UserInteractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestCalendarModel {

    @BeforeAll
    public static void setUp() throws IOException {
        new FileWriter("users.ser", false).close();

        UserInteractor userInteractor = new UserInteractor();
        userInteractor.writeData(new User("", "", "", "", "",
                3, 123, "2005-01-07", null, "", 1));
        userInteractor.writeData(new User("", "", "", "", "",
                4, 123, "2005-01-07", null, "", 1));

        new FileWriter("shifts.ser", false).close();
        ShiftInteractor shiftInteractor = new ShiftInteractor();
        ArrayList<Integer> workers = new ArrayList<>();
        workers.add(3);
        workers.add(44);
        shiftInteractor.writeData(new Shift(LocalDateTime.of(2023, 8, 10, 1, 2),
                (List<Integer>) workers, 4.5F, 1));
        shiftInteractor.writeData(new Shift(LocalDateTime.of(2023, 8, 10, 21, 2),
                (List<Integer>) workers, 4.5F, 2));

    }

    @Test
    void testGetShiftsHR() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 3); // Replace with valid user ID

        ArrayList<Integer> shifts = calendarModel.getShifts(10); // Replace with valid day number

        // Test HR shift retrieval logic here
        // You can use assertions to verify the correctness of the result
        // For example:
        assertTrue(shifts.contains(1)); // Replace with valid shift ID
        assertTrue(shifts.contains(2)); // Replace with valid shift ID
        // ...

    }

    @Test
    void testGetShiftsEmployee() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 3);

        ArrayList<Integer> shifts = calendarModel.getShifts(10);

        assertTrue(shifts.contains(1));
        assertFalse(shifts.contains(4));

    }

    @Test
    void testIsPayDay() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 789); // Replace with valid user ID

        assertTrue(calendarModel.isPayDay(25)); // valid day number for payday
        assertFalse(calendarModel.isPayDay(15)); // invalid day number for payday
    }

    @Test
    void testSetAndGetYear() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 123); // Replace with valid user ID
        calendarModel.setYear(2024);
        assertEquals(2024, calendarModel.getYear());
    }

    @Test
    void testSetAndGetMonth() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 123); // Replace with valid user ID
        calendarModel.setMonth(9);
        assertEquals(9, calendarModel.getMonth());
    }

    @Test
    void testGetUser() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 456); // Replace with valid user ID
        assertEquals(456, calendarModel.getUser());
    }
}
