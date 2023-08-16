package UseCases;

import Entities.Shift;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for ShiftInteractor class.
 */
public class ShiftInteractorTest {
    private ShiftInteractor interactor;
    private Shift shift;
    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter("testShifts.ser", false).close();
        interactor = new ShiftInteractor("test");
        shift = Instancio.of(Shift.class).create();
    }

    @Test
    public void testReadWriting(){
        interactor.writeData(shift);
        Shift testShift = interactor.readData().get(0);
        assertEquals(shift.getShiftId(), testShift.getShiftId());
        assertEquals(shift.getDuration(), testShift.getDuration());
    }
    @Test
    public void testUpdate(){
        Shift newShift = Instancio.of(Shift.class).create();
        newShift.setShiftId(shift.getShiftId());
        interactor.update(newShift);
        Shift testShift = interactor.readData().get(0);
        assertEquals(newShift.getCoworkers(),testShift.getCoworkers());
        assertEquals(newShift.getShiftId(),testShift.getShiftId());
    }
}
