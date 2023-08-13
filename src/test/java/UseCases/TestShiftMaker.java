package UseCases;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Entities.Shift;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestShiftMaker {

    @Test
    void testMakeShiftValidTime() {
        // Create a mock ShiftInteractor and ShiftFileReader
        ShiftInteractor mockShiftInteractor = new ShiftInteractor() {
            @Override
            public void writeData(Shift shift) {
                // Mock writeData method
            }
        };
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public ArrayList<Integer> getIds() {
                // Mock getIdsSize method
                return new ArrayList<Integer>(5);
            }
        };

        ShiftMaker shiftMaker = new ShiftMaker(LocalDateTime.of(2023, 8, 11, 9, 0), 8.5F);
        shiftMaker.interactor = mockShiftInteractor;
        shiftMaker.reader = mockShiftFileReader;

        assertDoesNotThrow(shiftMaker::makeShift);
    }

    @Test
    void testMakeShiftInvalidTime() {
        // Create a mock ShiftInteractor and ShiftFileReader
        ShiftInteractor mockShiftInteractor = new ShiftInteractor() {
            @Override
            public void writeData(Shift shift) {
                // Mock writeData method
            }
        };
        ShiftFileReader mockShiftFileReader = new ShiftFileReader("test") {
            @Override
            public ArrayList<Integer> getIds() {
                // Mock getIdsSize method
                return new ArrayList<Integer>(5);
            }
        };

        ShiftMaker shiftMaker = new ShiftMaker(LocalDateTime.of(2023, 8, 11, 20, 0), 8.5F);
        shiftMaker.interactor = mockShiftInteractor;
        shiftMaker.reader = mockShiftFileReader;

        assertThrows(InvalidTimeException.class, shiftMaker::makeShift);
    }
}
