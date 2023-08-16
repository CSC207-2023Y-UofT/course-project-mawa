package Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Unit test for Shift entity class.
 */
public class ShiftTest {
    private Shift shift;
    private LocalDateTime time;
    private List<Integer> coworkers;
    @BeforeEach
    public void setUp() {
        time = LocalDateTime.of(2023, 8, 15, 9, 0);
        coworkers = new ArrayList<>();
        coworkers.add(1);
        coworkers.add(2);
        coworkers.add(3);
        shift = new Shift(time, coworkers, 8.5f, 1);
    }

    @Test
    public void getTime() {
        assertEquals(time, shift.getTime());
    }

    @Test
    public void getShiftId() {
        assertEquals(1, shift.getShiftId());
    }

    @Test
    public void getCoworkers() {
        assertEquals(coworkers, shift.getCoworkers());
    }

    @Test
    public void addCoworker() {
        shift.addCoworker(4);
        assertTrue(shift.getCoworkers().contains(4));
    }

    @Test
    public void addCoworkers() {
        List<Integer> newCoworkers = new ArrayList<>();
        newCoworkers.add(5);
        newCoworkers.add(6);
        shift.addCoworkers(newCoworkers);
        assertTrue(shift.getCoworkers().containsAll(newCoworkers));
    }

    @Test
    public void removeCoworker() {
        shift.removeCoworker(2);
        assertTrue(!shift.getCoworkers().contains(2));
    }

    @Test
    public void setCoworkers() {
        List<Integer> newCoworkers = new ArrayList<>();
        newCoworkers.add(7);
        newCoworkers.add(8);
        shift.setCoworkers(newCoworkers);
        assertEquals(newCoworkers, shift.getCoworkers());
    }

    @Test
    public void setTime() {
        LocalDateTime newTime = LocalDateTime.of(2023, 8, 15, 12, 0);
        shift.setTime(newTime);
        assertEquals(newTime, shift.getTime());
    }

    @Test
    public void getDuration() {
        assertEquals(8.5f, shift.getDuration());
    }

    @Test
    public void setDuration() {
        float newDuration = 10.0f;
        shift.setDuration(newDuration);
        assertEquals(newDuration, shift.getDuration());
    }

    @Test
    public void setShiftId() {
        int newShiftId = 2;
        shift.setShiftId(newShiftId);
        assertEquals(newShiftId, shift.getShiftId());
    }
}
