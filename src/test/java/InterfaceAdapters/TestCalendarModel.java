package InterfaceAdapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import Entities.Shift;
import Entities.User;
import InterfaceAdapters.CalendarModel;
import UseCases.ShiftFileReader;
import UseCases.ShiftInteractor;
import UseCases.UserFileReader;
import UseCases.UserInteractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestCalendarModel {

    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter("testUsers.ser", false).close();

        UserInteractor userInteractor = new UserInteractor("test");
        userInteractor.writeData(new User("", "", "", "", "",
                3, 123, "2005-01-07", null, "", 1));
        userInteractor.writeData(new User("", "", "", "", "",
                4, 123, "2005-01-07", null, "", 1));
        userInteractor.writeData(new User("", "", "", "", "",
                1, 123, "2005-01-07", null, "HR", 1));

        new FileWriter("testShifts.ser", false).close();
        ShiftInteractor shiftInteractor = new ShiftInteractor("test");
        ArrayList<Integer> workers = new ArrayList<>();
        workers.add(3);
        workers.add(44);
        shiftInteractor.writeData(new Shift(LocalDateTime.of(2023, 8, 10, 1, 2),
                (List<Integer>) workers, 4.5F, 1));
        shiftInteractor.writeData(new Shift(LocalDateTime.of(2023, 8, 10, 21, 2),
                (List<Integer>) workers, 1F, 2));

    }

    @Test
    void testGetShiftsHR() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 1);
        ShiftInteractor shiftInteractor = new ShiftInteractor("test");
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public ArrayList<Integer> getIds(LocalDate date) {
                ArrayList<Integer> ds = new ArrayList<>();
                for (Shift s: shiftInteractor.readData()){
                    if (s.getTime().toLocalDate().isEqual(date)){
                        ds.add(s.getShiftId());
                    }
                }
                return ds;
            }
        };
        calendarModel.shiftDB = mockShiftFileReader;
        calendarModel.userDB = new UserFileReader("test");
        ArrayList<Integer> shifts = calendarModel.getShifts(10);
        assertTrue(shifts.contains(2));
        assertFalse(shifts.contains(3));

    }

    @Test
    void testGetShiftsEmployee() {
        CalendarModel calendarModel = new CalendarModel(2023, 8, 3);
        ShiftInteractor shiftInteractor = new ShiftInteractor("test");
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public ArrayList<Integer> getIds(LocalDate date) {
                ArrayList<Integer> ds = new ArrayList<>();
                for (Shift s: shiftInteractor.readData()){
                    if (s.getTime().toLocalDate().isEqual(date)){
                        ds.add(s.getShiftId());
                    }
                }
                return ds;
            }
            @Override
            public ArrayList<Integer> getIds(int user) {
                ArrayList<Integer> ds = new ArrayList<>();
                for (Shift s: shiftInteractor.readData()){
                    if (s.getCoworkers().contains(user)){
                        ds.add(s.getShiftId());
                    }
                }
                return ds;
            }
        };
        calendarModel.shiftDB = mockShiftFileReader;
        calendarModel.userDB = new UserFileReader("test");
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
        CalendarModel calendarModel = new CalendarModel(2023, 8, 456); // Replace with valid user ID
        assertEquals(456, calendarModel.getUser());
    }
}
