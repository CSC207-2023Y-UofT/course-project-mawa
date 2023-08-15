package UseCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entities.Shift;
import org.junit.jupiter.api.Test;
/**
 * Unit test for ShiftSorter class.
 */
public class TestShiftSorter {

    @Test
    void testSortShiftsByDate() {
        // Create a mock ShiftFileReader with sample data
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public Shift getShift(int id) {
                return new Shift(LocalDateTime.now().plusHours(id), new ArrayList<Integer>(), 0, id);
            }
        };

        ShiftSorter sorter = new ShiftSorter();
        sorter.reader = mockShiftFileReader;

        // Create a list of shift IDs in random order
        List<Integer> shifts = Arrays.asList(3, 1, 5, 2, 4);

        ArrayList<Integer> sortedShifts = sorter.sortShiftsByDate(new ArrayList<>(shifts));

        // Check if the sorted list is in ascending order based on shift date
        for (int i = 1; i < sortedShifts.size(); i++) {
            LocalDateTime prevShiftDate = mockShiftFileReader.getShift(sortedShifts.get(i - 1)).getTime();
            LocalDateTime currentShiftDate = mockShiftFileReader.getShift(sortedShifts.get(i)).getTime();
            assertEquals(-1, prevShiftDate.compareTo(currentShiftDate));
        }
    }
}
