package UseCases;

import static org.junit.jupiter.api.Assertions.*;

import Entities.Shift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestDayViewModel {
    @BeforeEach
    public void setUp(){
        LocalDateTime start1 = LocalDateTime.of(2023, 8, 11, 9, 0);
        LocalDateTime start2 = LocalDateTime.of(2023, 8, 11, 12, 0);
        LocalDateTime start3 = LocalDateTime.of(2023, 8, 11, 17, 0);

        ShiftInteractor interactor = new ShiftInteractor();
        ShiftFileReader reader = ShiftFileReader.getInstance();
        Shift shift1 = new Shift(start1, new ArrayList<Integer>(), 3, 1);
        Shift shift2 = new Shift(start2, new ArrayList<Integer>(), 7, 2);
        Shift shift3 = new Shift(start3, new ArrayList<Integer>(), 7, 3);
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(shift1);
        shifts.add(shift2);
        shifts.add(shift3);
        for (Shift s:shifts){
            if (reader.getIds().contains(s.getShiftId())){
                interactor.update(s);
            }else{
                interactor.writeData(s);
            }
        }
    }

    @Test
    public void testYCoord() {
        float yCoord = DayViewModel.yCoord(1, 2, 100);
        assertEquals(50, yCoord, 0.001);
    }

    @Test
    public void testMake2DList() {
        ArrayList<Integer> shiftids = new ArrayList<Integer>();
        shiftids.add(1);shiftids.add(2);shiftids.add(3);
        ArrayList<ArrayList<Integer>> shifts2D = DayViewModel.make2DList(shiftids);
        assertEquals(2, shifts2D.size());
        assertEquals(1, shifts2D.get(1).size());
        assertEquals(1, shifts2D.get(0).get(0));
    }

    @Test
    public void testIsOverlapping() {

        boolean overlapping = DayViewModel.isOverlapping(1, 2);
        assertFalse(overlapping);

        boolean overlapping2 = DayViewModel.isOverlapping(2, 3);
        assertTrue(overlapping2);
    }
}
