package InterfaceAdapters;

import Entities.Shift;
import InterfaceAdapters.DayViewLogic;
import UseCases.ShiftInteractor;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestDayViewLogic {
    private List<Shift> shifts;
    private ArrayList<Integer> shiftIds;
    private DayViewLogic dvl;
    private float width, height;
    private ShiftInteractor interactor;
    private ArrayList<LocalDateTime> times;

    @BeforeEach
    public void setUp(){
        shifts =  Instancio.ofList(Shift.class).size(10).create();
        width = 100;
        height = 10;
        interactor = new ShiftInteractor();
        times = new ArrayList<>();
        shiftIds = new ArrayList<>();
        times.add(LocalDateTime.of(2022, 2, 10, 1, 10));
        times.add(LocalDateTime.of(2022, 2, 10, 1, 11));
        times.add(LocalDateTime.of(2022, 2, 10, 5, 11));
        times.add(LocalDateTime.of(2022, 2, 10, 12, 59));
        times.add(LocalDateTime.of(2022, 2, 10, 12, 59));
        times.add(LocalDateTime.of(2022, 2, 10, 3, 21));
        times.add(LocalDateTime.of(2022, 2, 10, 21, 0));
        times.add(LocalDateTime.of(2022, 2, 10, 22, 0));
        times.add(LocalDateTime.of(2022, 2, 10, 23, 59));
        times.add(LocalDateTime.of(2022, 2, 10, 8, 11));
        for (int i = 0; i < shifts.size(); i++){
            shifts.get(i).setTime(times.get(i));
            shiftIds.add(shifts.get(i).getShiftId());
            interactor.update(shifts.get(i));
        }
        dvl = new DayViewLogic(shiftIds, width, height, 2, LocalDate.now());
    }

    @Test
    public void testGetTimeRange(){
        Assertions.assertTrue(Arrays.equals(new int[]{0, 24}, dvl.getTimeRange()));
    }
    @Test
    public void testGetHours(){
        ArrayList<Integer> exHourRange = new ArrayList<>();
        for (int i = 0; i < 24; i++){
            exHourRange.add(i);
        }
        assertEquals(exHourRange, dvl.getHours(), "The hours from DayViewLogic should" +
                "be consecutive integers from the beginning (inclusive) of time range to end (exclusive).");
    }




}
