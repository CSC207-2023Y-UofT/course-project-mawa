package InterfaceAdapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import UseCases.ShiftFileReader;
import UseCases.UserFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Unit test for CalendarModel class.
 */
public class CalendarModelTest {
    ShiftFileReader mockShiftFileReader;
    @BeforeEach
    public void setUp() throws IOException {
        ArrayList<Integer> workers = new ArrayList<>();
        workers.add(3);
        workers.add(44);
        mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public ArrayList<Integer> getIds(LocalDate date) {
                ArrayList<Integer> ds = new ArrayList<>();
                ds.add(1);ds.add(2);
                return ds;
            }
            @Override
            public ArrayList<Integer> getIds(int user) {
                ArrayList<Integer> ds = new ArrayList<>();
                if (workers.contains(user)){
                    ds.add(1);ds.add(2);
                }
                return ds;
            }
        };//make shift reader have an expected output

    }

    @Test
    void testGetShiftsHR() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 1);
        calendarModel.shiftDB = mockShiftFileReader;
        calendarModel.userDB = new UserFileReader("test"){
            @Override
            public String getType(int user) {
                if (user == 1){return "HR";}
                return "";
            }
        };
        ArrayList<Integer> shifts = calendarModel.getShifts(10);
        assertTrue(shifts.contains(2));
        assertFalse(shifts.contains(3));//make sure HR gets all shifts on the day and not any others

    }

    @Test
    void testGetShiftsEmployee() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 3);
        calendarModel.shiftDB = mockShiftFileReader;
        ArrayList<Integer> shifts = calendarModel.getShifts(10);

        assertTrue(shifts.contains(2));
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
        CalendarModel calendarModel = new CalendarModel(2023, 8, 456);
        assertEquals(456, calendarModel.getUser());
    }
}
