package UseCases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestDayViewModel {

    @Test
    public void testYCoord() {
        float yCoord = DayViewModel.yCoord(1, 2, 100);
        assertEquals(51.666668, yCoord, 0.001);
    }

    @Test
    public void testMake2DList() {
        ArrayList<Integer> shifts = new ArrayList<>();
        shifts.add(1);
        shifts.add(2);
        shifts.add(3);

        ArrayList<ArrayList<Integer>> shifts2D = DayViewModel.make2DList(shifts);
        assertEquals(3, shifts2D.size());
        assertEquals(1, shifts2D.get(0).size());
        assertEquals(1, shifts2D.get(0).get(0));
    }

    @Test
    public void testIsOverlapping() {
        ShiftFileReader reader = ShiftFileReader.getInstance();

        LocalDateTime start1 = LocalDateTime.of(2023, 8, 11, 9, 0);
        LocalDateTime end1 = LocalDateTime.of(2023, 8, 11, 13, 0);
        LocalDateTime start2 = LocalDateTime.of(2023, 8, 11, 12, 0);
        LocalDateTime end2 = LocalDateTime.of(2023, 8, 11, 17, 0);

        boolean overlapping = DayViewModel.isOverlapping(1, 2);
        assertFalse(overlapping);

        boolean overlapping2 = DayViewModel.isOverlapping(1, 3);
        assertTrue(overlapping2);
    }
}
