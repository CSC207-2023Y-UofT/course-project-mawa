package InterfaceAdapters;

import Entities.Shift;
import InterfaceAdapters.DayViewLogic;
import UseCases.ShiftFileReader;
import UseCases.ShiftInteractor;
import UseCases.ShiftSorter;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
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
    public void setUp() throws IOException {
        new FileWriter("testShifts.ser", false).close();
        shifts =  Instancio.ofList(Shift.class).size(10).create();
        width = 100;
        height = 10;
        interactor = new ShiftInteractor("test");
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
            shifts.get(i).setShiftId(i + 2);
            shiftIds.add(shifts.get(i).getShiftId());
            interactor.update(shifts.get(i));
        }
        dvl = new DayViewLogic(shiftIds, width, height, 2, LocalDate.now());

    }

    @Test
    public void testGetTimeRange(){
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public LocalDateTime getDate(int id) {
                ArrayList<Shift> data = interactor.readData();
                for (Shift s: data){
                    if (s.getShiftId() == id){
                        return s.getTime();
                    }
                }
                return null;
            }
            @Override
            public float getDuration(int id) {
                for (Shift s: interactor.readData()){
                    if (s.getShiftId() == id){
                        return s.getDuration();
                    }
                }
                return (float) -0.1;
            }
        };
        dvl.reader = mockShiftFileReader;
        ShiftSorter sorter = new ShiftSorter();
        sorter.reader = mockShiftFileReader;
        dvl.sorter = sorter;

        Assertions.assertTrue(Arrays.equals(new int[]{0, 24}, dvl.getTimeRange()));
    }
    @Test
    public void testGetHours(){
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public LocalDateTime getDate(int id) {
                for (Shift s: interactor.readData()){
                    if (s.getShiftId() == id){
                        return s.getTime();
                    }
                }
                return null;
            }
            @Override
            public float getDuration(int id) {
                for (Shift s: interactor.readData()){
                    if (s.getShiftId() == id){
                        return s.getDuration();
                    }
                }
                return (float) -0.1;
            }
        };
        dvl.reader = mockShiftFileReader;
        ShiftSorter sorter = new ShiftSorter();
        sorter.reader = mockShiftFileReader;
        dvl.sorter = sorter;
        ArrayList<Integer> exHourRange = new ArrayList<>();
        for (int i = 0; i < 24; i++){
            exHourRange.add(i);
        }
        assertEquals(exHourRange, dvl.getHours(), "The hours from DayViewLogic should" +
                "be consecutive integers from the beginning (inclusive) of time range to end (exclusive).");
    }




}
