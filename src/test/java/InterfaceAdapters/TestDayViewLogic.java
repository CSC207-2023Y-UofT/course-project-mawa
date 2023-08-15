package InterfaceAdapters;

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

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDayViewLogic {
    private ArrayList<Integer> shiftIds;
    private ArrayList<Float> durations;
    private DayViewLogic dvl;
    private float width, height;
    private ArrayList<LocalDateTime> times;
    private ShiftFileReader mockShiftFileReader;
    private ShiftSorter sorter;

    @BeforeEach
    public void setUp() throws IOException {
        shiftIds = (ArrayList<Integer>) Instancio.ofList(Integer.class).size(10).create();
        durations = (ArrayList<Float>) Instancio.ofList(Float.class).size(10).create();
        width = 100;
        height = 10;
        times = new ArrayList<>();
        times.add(LocalDateTime.of(2022, 2, 10, 1, 10));
        times.add(LocalDateTime.of(2022, 2, 10, 1, 11));
        times.add(LocalDateTime.of(2022, 2, 10, 3, 21));
        times.add(LocalDateTime.of(2022, 2, 10, 5, 11));
        times.add(LocalDateTime.of(2022, 2, 10, 8, 11));
        times.add(LocalDateTime.of(2022, 2, 10, 12, 59));
        times.add(LocalDateTime.of(2022, 2, 10, 12, 59));
        times.add(LocalDateTime.of(2022, 2, 10, 21, 0));
        times.add(LocalDateTime.of(2022, 2, 10, 22, 0));
        times.add(LocalDateTime.of(2022, 2, 10, 23, 59));
        mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public LocalDateTime getDate(int id) {
                return times.get(shiftIds.indexOf(id));
            }
            @Override
            public float getDuration(int id) {
                return durations.get(shiftIds.indexOf(id));
            }
        };
        sorter = new ShiftSorter(){
            @Override
            public ArrayList<Integer> sortShiftsByDate(ArrayList<Integer> shifts){
                return shiftIds;
            }
        };
        dvl = new DayViewLogic(shiftIds, width, height, 2, LocalDate.now());
        dvl.reader = mockShiftFileReader;
        dvl.sorter = sorter;
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
