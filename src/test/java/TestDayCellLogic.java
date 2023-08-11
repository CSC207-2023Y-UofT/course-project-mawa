import Entities.Shift;
import InterfaceAdapters.DayViewLogic;
import UseCases.ShiftInteractor;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestDayCellLogic {
    private List<Shift> shifts;
    private ArrayList<Integer> shiftIds;
    private DayViewLogic dvl;
    private float width, height;
    private ShiftInteractor interactor;
    private ArrayList<LocalDateTime> times;

    @BeforeAll
    public void setUp(){
        shifts =  Instancio.ofList(Shift.class).size(10).create();
        width = 100;
        height = 10;
        interactor = new ShiftInteractor();
        times = new ArrayList<>();
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
        dvl = new DayViewLogic(shiftIds, width, height);
    }

    @Test
    public void testGetTimeRange(){
        assertEquals(new int[] {0, 24}, dvl.getTimeRange(),
                "The maximum time range is from 00:00 to 23:59(24)");
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

    @Test
    public void testIsOverlapping(){
        ArrayList<Float> durations = new ArrayList<>();
        durations.add(0.5F);
        durations.add(4F);
        durations.add(1.3f);
        durations.add(1f);
        durations.add(0.5F);
        durations.add(1f);
        durations.add(1f);
        durations.add(2f);
        durations.add(3f);
        durations.add(1f);
        for (int i = 0; i < shifts.size(); i++){
            shifts.get(i).setDuration(durations.get(i));
            interactor.update(shifts.get(i));
        }
        assertTrue(dvl.isOverlapping(shifts.get(7).getShiftId(), shifts.get(8).getShiftId()));
        assertFalse(dvl.isOverlapping(shifts.get(7).getShiftId(), shifts.get(6).getShiftId()));
        assertFalse(dvl.isOverlapping(shifts.get(2).getShiftId(), shifts.get(4).getShiftId()));
    }
    @Test
    public void testMake2DList(){
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(shifts.get(0).getShiftId());
        list1.add(shifts.get(1).getShiftId());
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(shifts.get(5).getShiftId());
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(shifts.get(2).getShiftId());
        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(shifts.get(9).getShiftId());
        ArrayList<Integer> list5 = new ArrayList<>();
        list5.add(shifts.get(3).getShiftId());
        list5.add(shifts.get(4).getShiftId());
        ArrayList<Integer> list6 = new ArrayList<>();
        list6.add(shifts.get(6).getShiftId());
        ArrayList<Integer> list7 = new ArrayList<>();
        list7.add(shifts.get(7).getShiftId());
        list7.add(shifts.get(8).getShiftId());
        ArrayList<ArrayList<Integer>> exLst = new ArrayList<>();
        exLst.add(list1);exLst.add(list2);exLst.add(list3);exLst.add(list4);
        exLst.add(list5);exLst.add(list6);exLst.add(list7);
        assertEquals(exLst, dvl.make2DList());
    }


}
